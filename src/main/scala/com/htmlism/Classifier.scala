package com.htmlism

object Classifier {
  def classify(stew: Stew, config: List[Recipe]): List[String] = {
    val qualified = config.flatMap { r =>
      if (r.criteria(stew))
        Some(r.name)
      else
        None
    }

    qualified
  }

  def header(s: String) = print(f"$s%-9s")

  def printTable(stew: Stew) = {
    header("Red")
    header("Blue")
    header("Yellow")
    header("Grey")
    header("Soft")
    header("Hard")
    header("Small")
    header("Precious")
    header("Sweet")
    header("Mineral")
    header("Mushroom")
    header("Plant")
    println

    print(f"${stew.count(Red)}%-9s")
    print(f"${stew.count(Blue)}%-9s")
    print(f"${stew.count(Yellow)}%-9s")
    print(f"${stew.count(Grey)}%-9s")
    print(f"${stew.count(Soft)}%-9s")
    print(f"${stew.count(Hard)}%-9s")
    print(f"${stew.count(Small)}%-9s")
    print(f"${stew.count(Precious)}%-9s")
    print(f"${stew.count(Sweet)}%-9s")
    print(f"${stew.count(Mineral)}%-9s")
    print(f"${stew.count(Mushroom)}%-9s")
    print(f"${stew.count(Plant)}%-9s")
    println
  }
}
