package com.hm.db

// Represents products DB

object OfferDB {
  def apply(): OfferDB = {
    // Create offer db with default offers
    OfferDB(Map[String, CIDiscount]())
      .withOffer(CIDiscount("apple", 1, 1))
      .withOffer(CIDiscount("orange", 2, 1))
  }
}

case class OfferDB(offers: Map[String, CIDiscount]) {
  def withOffer(offer: CIDiscount): OfferDB = this.copy(offers = offers + (offer.name -> offer))
  def get(name: String): Option[CIDiscount] = offers.get(name)
}

trait Discount

case class CIDiscount(name: String, minItems: Int, freeItems: Int) extends Discount
