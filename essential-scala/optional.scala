sealed trait Maybe[+A] {
  def fold[B](full: A => B, empty: B): B = {
    this match {
      case Full(value) => full(value)
      case Empty => empty
    }
  }

  def map[B](fn: A => B): Maybe[B] = this match {
    case Full(value) => Full(fn(value))
    case Empty => Empty
  }

  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =  this match {
    case Full(value) => fn(value)
    case Empty => Empty
  }
}

final case class Full[A](value: A) extends Maybe[A]
case object Empty extends Maybe[Nothing]
