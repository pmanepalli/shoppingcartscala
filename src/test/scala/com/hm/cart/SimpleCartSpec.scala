package com.hm.cart

import com.hm.db.Product
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class SimpleCartSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("Cart contains user selected items") {

    scenario("Test cart has all added products") {
      Given("cart items")
      val ci1 = CartItem(Product("papaya", 1.2), 5)
      val ci2 = CartItem(Product("watermelon", 2.5), 1)

      When("add cart items to cart")
      val cart = ShoppingCart()
        .addItem(ci1)
        .addItem(ci2)

      Then("cart should have added items")
      cart.totalCartItems shouldBe 2
    }

    scenario("cart item quanity should be updated if item is already exist"){
      Given("cart item")
      val ci = CartItem(Product("papaya", 1.2), 5)

      When("add duplicate items to cart")
      val cart = ShoppingCart()
        .addItem(ci)
        .addItem(ci)
        .addItem(ci)
      Then("cart should have unique cart items")
      cart.totalCartItems shouldBe 1
    }
  }

  feature("Able find price of cart or cart items") {

    scenario("Test total price of cart item") {
      Given("cart item")
      val ci1 = CartItem(Product("papaya", 1.2), 5)

      When("get price of cart item")
      val ciPrice = ci1.price

      Then("cart item price should be calculated")
      ciPrice shouldBe 6
    }

    scenario("Test total price of cart") {
      Given("cart with cart items")
      val cart = ShoppingCart()
        .addItem(CartItem(Product("papaya", 1.2), 5))
        .addItem(CartItem(Product("watermelon", 2.5), 1))

      When("get price of cart")
      val cartPrice = cart.price

      Then("cart price should be calculated")
      cartPrice shouldBe 8.5
    }
  }
}
