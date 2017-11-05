package hlib.mykhailenko

import org.grep4j.core.Grep4j.{constantExpression, grep, regularExpression}
import org.grep4j.core.fluent.Dictionary.on
import org.grep4j.core.model.{Profile, ProfileBuilder}
import org.grep4j.core.request.GrepExpression
import org.grep4j.core.result.GrepResults
import collection.JavaConverters._


object Grep4jConversions {
  implicit def stringToConstantExpression(s: String) = constantExpression(s)

  def autoGenerateNameForProgile = "auto-generated name " + System.currentTimeMillis

  def localStringToGrep(filePath: String)
  : GrepWorker = new GrepWorker((ProfileBuilder.newBuilder
    .name(autoGenerateNameForProgile)
    .filePath(filePath)
    .onLocalhost.build))

  class Credentials(val user: String)
  case class CredentialsPassword(override val user: String, val password: String) extends Credentials(user)
  case class CredentialsKey(override val user: String, val pathToKey: String) extends Credentials(user)

  def remoteStringToGrep(host: String, filePath: String)
                        (implicit credentials: Credentials) = {
    val profile = credentials match {
      case cp: CredentialsPassword => ProfileBuilder.newBuilder
        .name(autoGenerateNameForProgile)
        .filePath(filePath)
        .onRemotehost(host)
        .credentials(cp.user,
          cp.password)
        .build
      case ck : CredentialsKey =>   ProfileBuilder.newBuilder
        .name(autoGenerateNameForProgile)
        .filePath(filePath)
        .onRemotehost(host)
        .userAuthPrivateKeyLocation(ck.pathToKey)
          .withUser(ck.user)
        .build
    }
    new GrepWorker(profile)
  }

  implicit def stringToSomething(s: String)(implicit credentials: Credentials) = s match {
    case HostAndFile(host, filePath) => remoteStringToGrep(host, filePath)
    case _ => localStringToGrep(s)
  }


  object HostAndFile {
    def apply(host: String, filePath: String) = host + "//" + filePath

    def unapply(str: String): Option[(String, String)] = {
      val parts = str split ":"
      if (parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }
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