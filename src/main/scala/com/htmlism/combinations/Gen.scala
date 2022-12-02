package com.htmlism.combinations

object Gen {
  def alt[A](xs: A*): Gen[A] =
    Alt(xs.toList.map(Atom.apply))

  def cat[A](x: A, xs: A*): Gen[A] =
    xs.foldLeft(Atom(x): Gen[A])((acc, z) => Product(acc, Atom(z)))
}

sealed trait Gen[A] {
  def *(that: Gen[A]): Product[A] =
    Product(this, that)

  def gen: List[List[A]]
}

case class Atom[A](x: A)            extends Gen[A] {
  def gen: List[List[A]] =
    List(List(x))
}
case class Alt[A](xs: List[Gen[A]]) extends Gen[A] {
  def gen: List[List[A]] =
    xs.flatMap(_.gen)
}

case class Product[A](left: Gen[A], right: Gen[A]) extends Gen[A] {
  def gen: List[List[A]] =
    for {
      xs <- left.gen
      ys <- right.gen
    } yield xs ::: ys
}
