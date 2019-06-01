sealed trait Expression {
  def eval: Sum[String, Double] = this match {
    case Number(v) => Success(v)
    case Addition(l, r) => lift2(l, r, (left, right) => Success(left + right))
    case Subtraction(l, r) => lift2(l, r, (left, right) => Success(left - right))
    case Division(l, r) => lift2(l, r, (left, right) =>
      right match {
        case 0 => Failure("Division by zero")
        case _ => Success(left / right)
      }
    )
    case SquareRoot(v) => 
      v.eval.flatMap{ x => 
        if(x < 0)
          Failure("Square root of negative number") 
        else
          Success(Math.sqrt(x))
      }
  }

  def lift2(l: Expression, r: Expression, f: (Double, Double) => Sum[String, Double]) =
    l.eval.flatMap { left => 
      r.eval.flatMap { right => 
        f(left, right)
      }
    }
}

final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Division(left: Expression, right: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
final case class Number(value: Double) extends Expression 

