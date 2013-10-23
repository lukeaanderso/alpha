package testing

import data.pricing.service.FlatFilePricer
import data.pricing.service.FlatFilePricer

object Script {

  def main(args: Array[String]): Unit = {
    var pricer = new FlatFilePricer
    var ts = pricer.get("IBM")
    println(ts.volatility)
  }
  
}

