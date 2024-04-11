package com.htmlism

object Bag {
  def apply[A](x: A*) =
    new Bag(x)

  def countMap[A](b: Bag[A]): Map[A, Int] =
    b.xs.foldLeft(Map.empty[A, Int]) { (acc, a) =>
      if (acc.contains(a))
        acc.updated(a, acc(a) + 1)
      else
        acc.updated(a, 1)
    }
}

class Bag[A](protected val xs: Seq[A]) {

  /**
    * For console output only.
    */
  override def toString: String =
    "Bag(" + xs.mkString(", ") + ")"
}
