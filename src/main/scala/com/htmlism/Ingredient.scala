package com.htmlism

object Ingredient {
  val all: Seq[Ingredient] =
    Seq(
      TinyMushroom,
      BlukBerry,
      Apricorn,
      Fossil,
      BigRoot,
      IcyRock,
      Honey,
      BalmMushroom,
      RainbowMatter,
      MysticalShell)
}

sealed trait Ingredient {
  def quality: Int
}

case object RainbowMatter extends Ingredient {
  val quality = 3
}

case object MysticalShell extends Ingredient {
  val quality = 4
}

abstract class CommonIngredient(
  val color: Color,
  val hardness: Hardness,
  val size: Size,
  val kind: Kind) extends Ingredient {
  def quality =
    size match {
      case Small => 1
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

case object TinyMushroom extends CommonIngredient(Red,    Soft, Small,    Mushroom)
case object BigRoot      extends CommonIngredient(Red,    Soft, Precious, Plant)
case object BlukBerry    extends CommonIngredient(Blue,   Soft, Small,    Sweet)
case object IcyRock      extends CommonIngredient(Blue,   Hard, Precious, Mineral)
case object Apricorn     extends CommonIngredient(Yellow, Hard, Small,    Plant)
case object Honey        extends CommonIngredient(Yellow, Soft, Precious, Sweet)
case object Fossil       extends CommonIngredient(Grey,   Hard, Small,    Mineral)
case object BalmMushroom extends CommonIngredient(Grey,   Soft, Precious, Mushroom)