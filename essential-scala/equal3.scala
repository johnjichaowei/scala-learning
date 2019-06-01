sealed trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object Equal {
  def apply[A](implicit eq: Equal[A]): Equal[A] = 
    eq

  implicit class ToEqual[A](in: A) {
    def ===(other: A)(implicit equal: Equal[A]): Boolean = 
      equal.equal(in, other)
  }
}

implicit val caseInsensitiveEqual = new Equal[String] {
  def equal(s1: String, s2: String): Boolean = 
    s1.toLowerCase == s2.toLowerCase
}

import Equal._

"foo".===("Foo")



