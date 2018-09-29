package com.htmlism

import scala.annotation.tailrec

object Stew {
  def generate: Seq[Stew] =
    generate(5, 10)
      .map(xs => Stew(xs.map(Ingredient.all).toList))

  def generate(length: Int, maxCount: Int): Seq[Array[Int]] =
    generate(length, maxCount, Seq(Array[Int]()))

  @tailrec
  def generate(length: Int, maxCount: Int, many: Seq[Array[Int]]): Seq[Array[Int]] =
    if (length == 0)
      many
    else {
      val newMany = many.flatMap { xs =>
        if (xs.isEmpty)
          (0 until maxCount)
            .map(Array.apply(_))
        else {
          val last        = xs.last
          val newElements = (0 until maxCount).filter(_ >= last)

          newElements
            .map(x => xs :+ x)
        }
      }

      generate(length - 1, maxCount, newMany)
    }
}

case class Stew(ingredients: List[Ingredient]) {
  private def rainbows =
    ingredients.collect { case RainbowMatter => () }.size

  private def common =
    ingredients.collect { case x: CommonIngredient => x }

  def count(color: Color): Int =
    common.count(_.color == color) + rainbows

  def count(size: Size): Int =
    common.count(_.size == size) + rainbows

  def count(hardness: Hardness): Int =
    common.count(_.hardness == hardness) + rainbows

  def count(kind: Kind): Int =
    common.count(_.kind == kind) + rainbows

  def count(shell: MysticalShell.type): Int =
    ingredients.count(_ == MysticalShell)

  def quality: Int = ingredients.map(_.quality).sum

  def gravity: String =
    ingredients
      .groupBy(identity)
      .mapValues(_.size)
      .toSeq
      .sortBy(_._2)
      .reverse
      .map { case (i, n) => i + " x" + n }
      .mkString(", ")
}
