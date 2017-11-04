package hlib.mykhailenko

import hlib.mykhailenko.ImplicitConversions._

object Main extends App {

  for (r <- "/home/hlib/testlog" | "a*a" ) {
    println(r);
  }

}

