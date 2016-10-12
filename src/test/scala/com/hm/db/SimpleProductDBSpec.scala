package com.hm.db

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class SimpleProductDBSpec extends FeatureSpec with GivenWhenThen with Matchers{

  feature("ProductDB acts as product database"){

    scenario("Test default products in DB") {
      Given("a products DB")
      val pdb = ProductDB()

      When("check for apple")
      val apple = pdb.get("apple")

      Then("apple should be available and costs 0.6")
      apple.get.name shouldBe "apple"
      apple.get.price shouldBe BigDecimal(0.6)
    }

    scenario("Add a products to ProductDB") {
      Given("a products DB")
      val pdb = ProductDB()

      When("add new products")
      val updatedPD = pdb
        .withProduct(Product("banana", BigDecimal(0.2)))
        .withProduct(Product("mango", BigDecimal(1.0)))

      Then("added products should be visible")
      updatedPD.totalProducts shouldBe 4
    }

    scenario("Test for invalid product"){
      Given("a products DB")
      val pdb = ProductDB()

      When("check for invalid product")
      val uf = pdb.get("unknownFruit")

      Then("unknownFruit should not be available")
      uf shouldBe None
    }
  }

}
