package org.hablapps.datasets
package test

import org.scalatest.{Filter =>_, _}

class RDDTest extends FunSpec with Matchers with BeforeAndAfterAll{

  // Start spark context

  import org.apache.spark.{SparkConf, SparkContext}

  var sc: SparkContext = null

  override def beforeAll {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("Intro")
    sc = new SparkContext(conf)
  }

  // Different tests

  describe("Test RDDs"){
    
    it("automatically"){

      val text: List[String] = List(
        "En un lugar de la mancha",
        "de cuyo nombre no quiero acordarme")

      val wcRDD = ToRDD(WordCount(Source(text))).apply(sc)
      val wcRDDDirect = WordCount(sc.parallelize(text))

      wcRDD.collect.toList shouldBe wcRDDDirect.collect.toList
    }
  }

  // Stops spark

  override def afterAll{
    sc.stop
  }

}