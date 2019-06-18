package sandbox

import c4.writer._

object Main extends App {
  val result1 = WriterTest.factorialParallel1
  println("\nWith writer: ")
  val Vector((logA, ansA), (logB, ansB)) = WriterTest.factorialParallel2
  println(s"logA: $logA, ansA: $ansA")
  println(s"logB: $logB, ansB: $ansB")
}
