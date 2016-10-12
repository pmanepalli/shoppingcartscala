package com.hm.fintools

import com.hm.cart.{CartItem, ShoppingCart}
import com.hm.db.OfferDB

object Calculator {
  val offerDB = OfferDB()

  def getPrice[T](entity: T): BigDecimal = {
    entity match {
      case cartItem: CartItem => getCIPrice(cartItem)
      case shoppingCart: ShoppingCart => getCartPrice(shoppingCart)
      case other => -1 // Log unknown entity or return error result
    }
  }

  def getCartPrice(cart: ShoppingCart): BigDecimal = {
    cart.cartItems.map(_.price).fold(BigDecimal(0))(_ + _)
  }

  def getCIPrice(cartItem: CartItem): BigDecimal = {
    val unitPrice = cartItem.product.price
    val listPrice = unitPrice * cartItem.quantity

    offerDB.get(cartItem.product.name).map { cd =>
      val totalItems = cartItem.quantity
      def applyDiscount(quantity: Int, block: Int, acc: BigDecimal): BigDecimal = {
        quantity match {
          case q: Int if (q > totalItems) => acc
          case other =>
            val discountedItem = if (block <= cd.minItems) false else true
            val updatedAcc = acc + (if (discountedItem) 0 else unitPrice)
            val updatedBlock = if (block < cd.minItems + cd.freeItems) block + 1 else 1
            applyDiscount(quantity + 1, updatedBlock, updatedAcc)
        }
      }

      applyDiscount(1, 1, 0)
    }.getOrElse(listPrice)
  }
}
