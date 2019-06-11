package sandbox

import c1.show._
import cats.syntax.show._

object Main extends App {
  println(Cat("Tom", 12, "Brown").show)
}
