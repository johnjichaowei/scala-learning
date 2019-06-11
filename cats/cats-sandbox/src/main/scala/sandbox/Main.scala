package sandbox

import c1.show._
import cats.instances.option._
import cats.syntax.all._

object Main extends App {
  val cat1 = Cat("Tom", 12, "brown")
  val cat2 = Cat("Jack", 11, "black")
  println(s"Test: ${cat1 === cat1}")
  println(s"Test: ${cat1 === cat2}")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(s"Test: ${optionCat1 === optionCat1}")
  println(s"Test: ${optionCat1 === optionCat2}")
}
