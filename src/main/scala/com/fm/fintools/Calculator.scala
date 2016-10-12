package com.hm.fintools

import com.hm.cart.{CartItem, ShoppingCart}

object Calculator {

  def getPrice[T](entity: T): BigDecimal = {
    entity match {
      case cartItem: CartItem => getCIPrice(cartItem)
      case shoppingCart: ShoppingCart => getCartPrice(shoppingCart)
      case other => -1 // Log unknown entity or return error result
    }
  }

  def getCartPrice(cart: ShoppingCart): BigDecimal = {
    cart.cartItems.map(_.price).fold(BigDecimal(0))(_+_)
  }

  def getCIPrice(cartItem: CartItem): BigDecimal = {
    val unitPrice = cartItem.product.price
    unitPrice * cartItem.quantity
  }
}
