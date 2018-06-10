package jp.pigumer.onetime

import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.file.Paths
import java.time.Instant
import java.util.Properties

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base32

import scala.collection.JavaConverters._

/**
  * @see https://tools.ietf.org/html/rfc4226
  *      https://tools.ietf.org/html/rfc6238
  */
trait OneTimePasswordAlgorithm {

  private val algorithm = "HmacSHA1"

  private def hmac(key: Array[Byte], text: Array[Byte]): Array[Byte] = {
    val hmac = Mac.getInstance(algorithm)
    val keySpec = new SecretKeySpec(key, "RAW")
    hmac.init(keySpec)
    hmac.doFinal(text)
  }

  private def truncate(hs: Array[Byte]): String = {
    val offset = hs(19) & 0xF
    val binary: Array[Byte] = Seq[Byte](
      (hs(offset) & 0x7f).toByte,
      hs(offset + 1),
      hs(offset + 2),
      hs(offset + 3)).toArray
    val decimal = ByteBuffer.wrap(binary).getInt
    format(decimal)
  }

  private def format(decimal: Int): String = {
    val otp = decimal % 1000000
    "%06d".format(otp)
  }

  def toBytes(counter: Long): Array[Byte] =
    ByteBuffer.allocate(8).putLong(counter).array()

  def getPassword(key: Array[Byte], time: Array[Byte]): String =
    truncate(hmac(key, time))
}

object OneTimePassword extends App with OneTimePasswordAlgorithm {

  val path = Paths.get(sys.props("user.home"), ".onetime", "onetime.properties")
  val props = {
    val is = new FileInputStream(path.toFile)
    try {
      val p = new Properties()
      p.load(new FileInputStream(path.toFile))
      p.asScala
    } finally {
      is.close()
    }
  }
  val secret = props("secret")
  var time = toBytes(Instant.now.getEpochSecond / 30)
  var result = getPassword(new Base32().decode(secret), time)
  println(result)
}