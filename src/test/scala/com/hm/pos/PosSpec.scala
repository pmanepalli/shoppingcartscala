package com.hm.pos

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class PosSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("Scan list of items") {
    scenario("POS could scan all valid items") {
      Given("list of items")
      val validItems = List("apple", "orange", "apple", "apple")

      When("scan items")
      val cartPage = POS.scan(validItems)

      Then("POS should have scanned items")
      cartPage.cart.totalProducts shouldBe 4
    }

    scenario("POS should not scan invalid items") {
      Given("list of items")
      val validItems = List("invalidFruit1", "invalidFruit2")

      When("scan items")
      val cartPage = POS.scan(validItems)

      Then("POS should not scan items")
      cartPage.cart.totalProducts shouldBe 0
    }

    scenario("POS should give total price of purchased items") {
      Given("list of items")
      val items1 = List("apple", "apple", "orange", "apple")
      val items2 = List("apple", "apple", "orange", "orange", "orange")
      val items3 = List("apple", "apple", "apple", "orange", "orange", "orange", "orange")

      When("scan items")
      val cartPage1 = POS.scan(items1)
      val cartPage2 = POS.scan(items2)
      val cartPage3 = POS.scan(items3)

      Then("POS should give price of purchased items")
      //      cartPage1.cart.price shouldBe 2.05
      //      cartPage2.cart.price shouldBe 1.95
      //      cartPage3.cart.price shouldBe 2.80

      cartPage1.cart.price shouldBe 1.45
      cartPage2.cart.price shouldBe 1.10
      cartPage3.cart.price shouldBe 1.95
    }
  }


  feature("check out for purchased items") {
    scenario("POS checkout with list of items purchased") {
      Given("list of items")
      val validItems = List("apple", "apple", "orange", "apple")

      When("scan items")
      val cartPage = POS.scan(validItems)

      Then("POS should display items purchased")
      //val resultPage = "[apple,apple,orange,apple] => £2.05"
      val resultPage = "[apple,apple,orange,apple] => £1.45"
      POS.checkout(cartPage) shouldBe resultPage
    }
  }
}
