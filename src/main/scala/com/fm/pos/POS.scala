package com.hm.pos

object POS {
  def scan(list: List[String]): CartPage = Scanner.scanItems(list)

  def checkout(cartPage: CartPage): String = cartPage.displayCart
}
