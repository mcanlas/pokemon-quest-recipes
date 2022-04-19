package com.htmlism

import java.io.PrintWriter

object GenerateWebPage {
  private def recipe(s: Stew) =
    Classifier.classify(s, Recipe.all).headOption.getOrElse("mulligan")

  private def quality(s: Stew) =
    Quality(s.quality)

  def main(args: Array[String]): Unit = {
    val out = new PrintWriter(args(0))

    print(out)

    out.close()
  }

  def print(out: PrintWriter): Unit = {
    val stewsByRecipe =
      Stew
        .generate
        .groupBy(recipe)

    for (r <- Recipe.all) {
      for (ss <- stewsByRecipe.get(r.name)) {
        val stewsByQuality =
          ss.groupBy(quality)

        for (q <- Quality.all) {
          out.println
          out.println(s"<h1>${r.name} : $q</h1>")

          stewsByQuality.get(q) match {
            case Some(xs) =>
              out.println("<ul>")
              xs.foreach(x => out.println(s"<li>${x.gravity}</li>"))
              out.println("</ul>")

            case None =>
              out.println("(no recipes possible)")
          }
        }

        out.println("<hr>")
      }
    }
  }
}
