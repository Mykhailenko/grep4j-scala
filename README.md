# grep4j-scala
Porting [grep4j](https://github.com/marcocast/grep4j) library to scala.

Import implicit conversions:
```scala
import hlib.mykhailenko.ImplicitConversions._
```

Then you can do something like this:
```scala
for (r <- "/home/hlib/server.log" | "server has been started" ) {
  println(r);
}
```
... to grep local log.
