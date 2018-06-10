package jp.pigumer.onetime

import java.nio.charset.StandardCharsets

import org.scalatest.{FlatSpec, Matchers}

class OneTimePasswordSpec extends FlatSpec with Matchers {

  "see https://tools.ietf.org/html/rfc4226" should "test" in {
    val oneTimePasswordAlgorithm = new OneTimePasswordAlgorithm {}
    val key = "12345678901234567890".getBytes(StandardCharsets.UTF_8)
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(0L)) should be("755224")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(1L)) should be("287082")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(2L)) should be("359152")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(3L)) should be("969429")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(4L)) should be("338314")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(5L)) should be("254676")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(6L)) should be("287922")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(7L)) should be("162583")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(8L)) should be("399871")
    oneTimePasswordAlgorithm.getPassword(key, oneTimePasswordAlgorithm.toBytes(9L)) should be("520489")
  }
}
