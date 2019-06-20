package sandbox

import c4.state._

object Main extends App {
  val (state, result) = StateDemo.demo.run(1).value

  println(s"State demo result: state - $state, result - $result")
}
