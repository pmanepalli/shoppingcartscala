package com.hm.db

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

/**
  * Created by purna on 11/10/2016.
  */
class OffersDBSpec extends FeatureSpec with GivenWhenThen with Matchers {
  feature("OffersDB acts as offers database") {

    scenario("Test default offers in DB") {
      Given("a offer DB")
      val offerDB = OfferDB()
      When("check for apple discount")
      val appleOffer = offerDB.get("apple")
      Then("apple offer should be available")
      appleOffer.get.name shouldBe "apple"
    }
  }
}
