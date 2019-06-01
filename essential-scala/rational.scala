final case class Rational(numerator: Int, denominator: Int)

object Rational {
    implicit val ordering = Ordering.fromLessThan[Rational]((x, y) => x.numerator * y.denominator < y.numerator * x.denominator)
}
object Example { 
    implicit val higherPriorityImplicit = Ordering.fromLessThan[Rational]((x, y) => 
        x.numerator * y.denominator > y.numerator * x.denominator)

  def example() = {
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted == List(Rational(3, 4), Rational(1, 2), Rational(1, 3)))
  }
}
