package com.hm.pos

import com.hm.cart.{CartItem, ShoppingCart}
import com.hm.db.ProductDB


case class CartPage(list: List[String], cart: ShoppingCart){
  def displayCart: String ={
    val sb = new StringBuilder()

    sb.append("[")
    sb.append(list.mkString(","))
    sb.append("]")
    sb.append(" => ")
    sb.append("£")
    sb.append(cart.price)


    sb.toString
  }
}

object Scanner extends App{
  def scanItems(list: List[String]): CartPage = {
    val defaultDB = ProductDB()
    val shoppingCart = list.flatMap(defaultDB.get)
      .map(CartItem(_, 1))
      .foldRight(ShoppingCart())((ci, sc) => sc.addItem(ci))

    CartPage(list, shoppingCart)
  }
}
