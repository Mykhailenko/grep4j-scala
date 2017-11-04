package hlib.mykhailenko

import hlib.mykhailenko.ImplicitConversions._

object Main extends App {

  for (r <- "/home/hlib/testlog" | "aaa" ) {
    println(r);
  }

}

