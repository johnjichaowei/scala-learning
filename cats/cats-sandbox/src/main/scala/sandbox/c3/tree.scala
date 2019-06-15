package sandbox.c3.tree

import cats.Functor
import cats.Show
import cats.instances.int._
import cats.syntax.show._

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)

  implicit val treeFunctor: Functor[Tree] =
    new Functor[Tree] {
      def map[A, B](tree: Tree[A])(func: A => B): Tree[B] =
        tree match {
          case Branch(left, right) => Branch(map(left)(func), map(right)(func))
          case Leaf(value) => Leaf(func(value))
        }
    }

  implicit val treeShow: Show[Tree[Int]] =
    new Show[Tree[Int]] {
      def show(tree: Tree[Int]) =
        tree match {
          case Branch(left, right) => s"Branch(left: ${show(left)}, right: ${show(right)})"
          case Leaf(value) => s"Leaf(${value.show})"
        }

    }
}
