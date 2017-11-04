package hlib.mykhailenko

import org.grep4j.core.Grep4j.{constantExpression, grep, regularExpression}
import org.grep4j.core.fluent.Dictionary.on
import org.grep4j.core.model.{Profile, ProfileBuilder}
import org.grep4j.core.request.GrepExpression
import org.grep4j.core.result.GrepResults
import collection.JavaConverters._


object ImplicitConversions {

  implicit def stringToConstantExpression(s: String) = constantExpression(s)

  implicit def stringToRegularExpression(s: String): StringRegular = new StringRegular(s)

  implicit def profileToGrep(p: Profile): GrepWorker = new GrepWorker(p)

  implicit def stringToGrep(filePath: String): GrepWorker = profileToGrep(ProfileBuilder.newBuilder
    .name("auto-generated name " + System.currentTimeMillis)
    .filePath(filePath)
    .onLocalhost.build)

  implicit def grepResultsToList(grepResults: GrepResults) = {
    asScalaIterator(grepResults.iterator())
  }
}

class StringRegular(val s: String) {
  def reg = regularExpression(s)
}

class GrepWorker(val p: Profile) {
  def |(grepExpression: GrepExpression) = {
    grep(grepExpression, on(Array(p): _*))
  }
}