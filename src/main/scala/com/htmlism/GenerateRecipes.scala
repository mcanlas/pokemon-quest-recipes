package com.htmlism

object GenerateRecipes {
  val tups =
    Stew.generate.map { s =>
      val recipe = Classifier.classify(s, Recipe.all).headOption.getOrElse("mulligan")
      val quality = Quality(s.quality)

      (s, recipe, quality)
    }


  def main(args: Array[String]): Unit = {
    for (s <- Stew.generate) {
      println(s.ingredients.mkString(", "))
      Classifier.printTable(s)
      println(" - " + Classifier.classify(s, Recipe.all).mkString(", "))
      println()
    }
  }
}

sealed trait Quality

case object Basic    extends Quality
case object Good     extends Quality
case object VeryGood extends Quality
case object Special  extends Quality

object Quality {
  val all: List[Quality] =
    List(Basic, Good, VeryGood, Special)

  def apply(n: Int): Quality =
    if (n < 6)
      Basic
    else if (n == 6 || n == 7)
      Good
    else if (n == 8 || n == 9)
      VeryGood
    else
      Special
}