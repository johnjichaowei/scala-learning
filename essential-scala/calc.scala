sealed trait Calculation

final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

case object Calculator {
  def +(left: Calculation, right: Int): Calculation = left match {
    case Success(result) => Success(result + right)
    case Failure(reason) => Failure(reason)
  }

  def -(left: Calculation, right: Int): Calculation = left match {
    case Success(result) => Success(result - right)
    case Failure(reason) => Failure(reason)
  }

  def /(left: Calculation, right: Int): Calculation = left match {
    case Success(result) => right match {
      case 0 => Failure("Division by zero")
      case _ => Success(result / right)
    }
    case Failure(reason) => Failure(reason)
  }
}
