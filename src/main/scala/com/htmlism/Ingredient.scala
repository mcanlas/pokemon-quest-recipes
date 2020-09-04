package com.htmlism

import scalaz.Scalaz.intInstance
import scalaz._

object Ingredient {
  val all: Seq[Ingredient] =
    Seq(TinyMushroom, BlukBerry, Apricorn, Fossil, BigRoot, IcyRock, Honey, BalmMushroom, RainbowMatter, MysticalShell)

  // for some reason, providing `Order[Ingredient]` is not enough to power `TinyMushroom === TinyMushroom`
  // but this does work `(TinyMushroom: Ingredient) === TinyMushroom`
  // this provides ordering at every inferred level of the hierarchy, not just `Ingredient`
  implicit def order[A <: Ingredient]: Order[A] =
    Order.orderBy(_.order)
}

sealed trait Ingredient {
  def quality: Int

  def order: Int
}

case object RainbowMatter extends Ingredient {
  val quality = 3
  val order   = 9
}

case object MysticalShell extends Ingredient {
  val quality = 4
  val order   = 10
}

abstract class CommonIngredient(
    val order: Int,
    val color: Color,
    val hardness: Hardness,
    val size: Size,
    val kind: Kind
) extends Ingredient {
  def quality: Int =
    size match {
      case Small    => 1
      case Precious => 2
    }
}

sealed trait Color

case object Red    extends Color
case object Blue   extends Color
case object Yellow extends Color
case object Grey   extends Color

sealed trait Hardness

case object Soft extends Hardness
case object Hard extends Hardness

sealed trait Size

case object Small    extends Size
case object Precious extends Size

sealed trait Kind

case object Sweet    extends Kind
case object Mineral  extends Kind
case object Mushroom extends Kind
case object Plant    extends Kind

// format: off
case object TinyMushroom extends CommonIngredient(1, Red,    Soft, Small,    Mushroom)
case object BigRoot      extends CommonIngredient(5, Red,    Soft, Precious, Plant)
case object BlukBerry    extends CommonIngredient(2, Blue,   Soft, Small,    Sweet)
case object IcyRock      extends CommonIngredient(6, Blue,   Hard, Precious, Mineral)
case object Apricorn     extends CommonIngredient(3, Yellow, Hard, Small,    Plant)
case object Honey        extends CommonIngredient(7, Yellow, Soft, Precious, Sweet)
case object Fossil       extends CommonIngredient(4, Grey,   Hard, Small,    Mineral)
case object BalmMushroom extends CommonIngredient(8, Grey,   Soft, Precious, Mushroom)
// format: on
