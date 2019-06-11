package sandbox

// import cats.Eq
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.eq._
import cats.syntax.option._

object Main extends App {
  println(s"Test: ${123 === 123}")
  println(s"Test: ${"123" =!= "1243"}")
  println(s"Test: ${ (Some(1): Option[Int]) === (None: Option[Int]) }")
  println(s"Test: ${ Option(1) === Option.empty[Int]}")
  println(s"Test: ${ 1.some === 1.some}")
  println(s"Test: ${ 1.some =!= none[Int]}")
}
