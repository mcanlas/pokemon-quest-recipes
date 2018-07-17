package com.htmlism

object Recipe {
  val all: List[Recipe] =
    List(
      Recipe("red",      _.count(Red)    >= 4),
      Recipe("blue",     _.count(Blue)   >= 4),
      Recipe("yellow",   _.count(Yellow) >= 4),
      Recipe("grey",     _.count(Grey)   >= 4),
      Recipe("water",    s => s.count(Soft)     >= 4 && s.count(Blue)     >= 3 ),
      Recipe("normal",   s => s.count(Sweet)    >= 3 && s.count(Grey)     >= 2 ),
      Recipe("poison",   s => s.count(Mushroom) >= 4 && s.count(Soft)     >= 3 ),
      Recipe("ground",   s => s.count(Mineral)  >= 2 && s.count(Soft)     >= 3 ),
      Recipe("grass",    s => s.count(Plant)    >= 4 && s.count(Soft)     >= 2 ),
      Recipe("bug",      s => s.count(Sweet)    >= 4 && s.count(Yellow)   >= 3 ),
      Recipe("psychic",  s => s.count(Sweet)    >= 3 && s.count(Hard)     >= 2 ),
      Recipe("rock",     s => s.count(Hard)     >= 4 && s.count(Mineral)  >= 2 ),
      Recipe("flying",   s => s.count(Mineral)  >= 3 && s.count(Plant)    >= 2 ),
      Recipe("fire",     s => s.count(Mushroom) >= 3 && s.count(Red)      >= 1 ),
      Recipe("electric", s => s.count(Soft)     >= 4 && s.count(Yellow)   >= 3 ),
      Recipe("fighting", s => s.count(Sweet)    >= 3 && s.count(Mushroom) >= 2 ),
    )
}

case class Recipe(name: String, criteria: Stew => Boolean)