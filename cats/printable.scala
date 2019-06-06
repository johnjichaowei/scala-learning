trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = 
    new Printable[String] {
      def format(value: String): String = value
    }

  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String = value.toString
    }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = 
    p.format(value)

  def accepts[A](value: A)(implicit p: Printable[A]): Unit = 
    println(format(value)(p))
}

import PrintableInstances._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat) = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String =
      printable.format(value)

   def accepts(implicit printable: Printable[A]): Unit =
    println(printable.format(value))
  }
}

import Printable._
import Cat._
import PrintableSyntax._

//val cat = new Cat("JJ", 13, "Brown")
//cat.format
