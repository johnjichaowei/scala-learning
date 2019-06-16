package sandbox

import c4.eval._

object Main extends App {
  val result = SaferFolding.foldRight((1 to 100000).toList, 0L)(_ + _)
  println(s"Result: $result")
}
