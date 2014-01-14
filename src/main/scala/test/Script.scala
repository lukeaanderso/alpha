package test

import portfolio.model.Position
import security.model.Equity

object MyScript {

  def main(args: Array[String]): Unit = {
    val aaplEquity = new Equity("AAPL")
    val googEquity = new Equity("GOOG")
    val beta = aaplEquity.timeseries.exposure(googEquity.timeseries.calculateMonthlyReturns)
    println(beta)
    val aaplPosition = new Position(aaplEquity, .50)
    println(aaplEquity.timeseries.last_px)
    println(aaplPosition)
  }
  
}
