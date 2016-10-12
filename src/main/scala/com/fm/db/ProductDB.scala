package com.hm.db

// Represents products DB

object ProductDB {
  def apply(): ProductDB = {
    // Create product db with default products
    ProductDB(Map[String, Product]())
      .withProduct(Product("apple", BigDecimal(0.60)))
      .withProduct(Product("orange", BigDecimal(0.25)))
  }
}

case class ProductDB(products: Map[String, Product]) {
  def withProduct(product: Product): ProductDB = this.copy(products = products + (product.name -> product))
  def get(name: String): Option[Product] = products.get(name)
  def totalProducts: Int = products.size
}

case class Product(name: String, price: BigDecimal)