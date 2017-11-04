package hlib.mykhailenko

import hlib.mykhailenko.ImplicitConversions._
import org.junit.Assert.assertEquals

object Main extends App {

  implicit var cred = CredentialsPassword("hlib", "pass")

  ("localhost:/home/hlib/testlog" | "ara").forEach(println _)

  ("/home/hlib/testlog" | "ara").forEach(println _)

//  cred = CredentialsKey("hlib", "path/to/key").asInstanceOf[Credentials]

//  ("localhost:/home/hlib/testlog" | "ara").forEach(println _)


  //  stringToSomething("hhhhhh//paaaaa")
  //  for(r <- RemoteProfile("172.0.0.1", "/home/hlib/testlog") | "ara"){
  //    println(r)
  //  }
  for (r <- "/home/hlib/testlog" | "ara") {
    println(r);
  }

  assertEquals(2, "/home/hlib/testlog" | "ara" totalLines)

}

