package com.hm.cart

import com.hm.fintools.Calculator
import com.hm.db.Product

case class CartItem(product: Product, quantity: Int){
  def price: BigDecimal = Calculator.getPrice(this)
}

case class ShoppingCart(cartItems: List[CartItem] = Nil) {

  def addItem(cartItem: CartItem): ShoppingCart = {

    def upsertItem = {
      cartItems.find(_.product.name == cartItem.product.name)
        .map(updateList)
        .getOrElse(cartItem +: cartItems)
    }

    def updateList(oldItem: CartItem) = {
      val updatedItem = oldItem.copy(quantity = oldItem.quantity + cartItem.quantity)
      updatedItem +: cartItems.filterNot(_.product.name == cartItem.product.name)
    }

    ShoppingCart(upsertItem)
  }

  def totalCartItems = cartItems.size

  def totalProducts = cartItems.foldRight(0)((ci, acc) => ci.quantity + acc)

  def price: BigDecimal = {
    Calculator.getPrice(this)
  }
}
