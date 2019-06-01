sealed trait Sum[+A, +B] {
  def fold[C](failure: A => C, success: B => C): C = this match {
    case Success(v) => success(v)
    case Failure(v) => failure(v)
  }

  def map[C](f: B => C): Sum[A, C] = this match {
    case Success(v) => Success(f(v))
    case Failure(v) => Failure(v)
  }

  def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] = this match {
    case Success(v) => f(v)
    case Failure(v) => Failure(v)
  }

//  def map1[C](f: B => C): Sum[A, C] = flatMap(x => Success(f(x)))
}

final case class Success[B](value: B) extends Sum[Nothing, B]
final case class Failure[A](value: A) extends Sum[A, Nothing]
