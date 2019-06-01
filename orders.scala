final case class Order(units: Int, unitPrice: Double)

object Order {
  implicit val ordering = Ordering.fromLessThan[Order]((x, y) => x.units * x.unitPrice < y.units * y.unitPrice)
}

object orderByUnits {
  implicit val ordering = Ordering.fromLessThan[Order]((x, y) => x.units < y.units)
}

object orderByUnitPrice {
  implicit val ordering = Ordering.fromLessThan[Order]((x, y) => x.unitPrice < y.unitPrice)
}
