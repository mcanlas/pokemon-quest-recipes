package com.htmlism

import scalaz._

object Bag {
  def apply[A](x: A*) =
    new Bag(x)

  /**
   * Also provides `Equal` over `Bag[A]`.
   */
  implicit def order[A](implicit set: Order[List[A]]): Order[Bag[A]] =
    set.contramap(b => b.xs.toList)
}

class Bag[A](protected val xs: Seq[A])
