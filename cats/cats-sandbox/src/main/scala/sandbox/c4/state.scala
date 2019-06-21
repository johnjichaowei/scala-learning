package sandbox.c4.state

import cats.data.State
import cats.syntax.applicative._

object StateDemo {
  val demo: State[Int, (Int, Int, Int)] = for {
    a <- State.get[Int]
    _ <- State.set[Int](a + 1)
    b <- State.get[Int]
    _ <- State.modify[Int](_ + 1)
    c <- State.inspect[Int, Int](_ * 1000)
  } yield (a, b, c)
}

object PostOrderCalculator {
  type CalcState[A] = State[List[Int], A]

  def evalInput(input: String): Int =
    evalAll(input.split(" ").toList).runA(Nil).value

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (a, b) =>
      a.flatMap(_ => evalOne(b))
    }

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def operand(num: Int): CalcState[Int] =
    State[List[Int], Int] { stack =>
      (num :: stack, num)
    }

  def operator(func: (Int, Int) => Int): CalcState[Int] =
    State[List[Int], Int] {
      case b :: a :: tail =>
        val ans = func(a, b)
        (ans :: tail, ans)
      case _ =>
        sys.error("Fail!")
    }

  val demoEvalOne = for {
    _ <- evalOne("12")
    _ <- evalOne("24")
    _ <- evalOne("+")
    _ <- evalOne("3")
    ans <- evalOne("*")
  } yield ans

  val demoEvalAll = for {
    _ <- evalAll(List("12", "20", "+", "12", "-"))
    _ <- evalAll(List("20", "2", "/", "3", "*"))
    ans <- evalOne("+")
  } yield ans
}
