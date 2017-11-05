# grep4j-scala
Porting [grep4j](https://github.com/marcocast/grep4j) library to scala.

Import implicit conversions:
```scala
import hlib.mykhailenko.Grep4jConversions._
```

Then you can do something like this:
```scala

implicit var cred = CredentialsPassword("hlib", "pass")

// to grep something on a remote machine
("serverip:/home/hlib/server.log" | "server has been started").forEach(println _)

// to grep something on a local machine
("/home/hlib/server.log" | "server has been started").forEach(println _)

// or you can assert number of lines
assertEquals(1, "serverip:/home/hlib/server.log" | "server has been started" totalLines)


```
