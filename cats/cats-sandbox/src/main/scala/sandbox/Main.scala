package sandbox

import c4.state._

object Main extends App {
  val (state, result) = StateDemo.demo.run(1).value

  println(s"State demo result: state - $state, result - $result")

  val (state1, result1) = PostOrderCalculator.evalOne("42").run(Nil).value
  println(s"evalOne1 result: ($state1, $result1)")

  val demoEvalOne = PostOrderCalculator.demoEvalOne.run(Nil).value
  println(s"demoEvalOne: $demoEvalOne")

  val demoEvalAll = PostOrderCalculator.demoEvalAll.run(Nil).value
  println(s"demoEvalAll: $demoEvalAll")

  val calcResult = PostOrderCalculator.evalInput("1 3 + 5 4 + * 1 3 + *")
  println(s"calcResult: $calcResult")
}
