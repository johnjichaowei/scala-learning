package sandbox.c1.show

import cats.Show
import cats.syntax.show._
import cats.Eq
import cats.syntax.eq._
import cats.instances.int._
import cats.instances.string._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catShow = Show.show[Cat] { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show

    s"$name is a $age year-old $color cat."
  }

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name === cat2.name && cat1.age === cat2.age &&
      cat1.color === cat2.color
  }
}
