import portfolio.model.Position
import security.model.Equity

object Script {

  def main(args: Array[String]): Unit = {
    val aaplEquity = new Equity("AAPL")
    val aaplPosition = new Position(aaplEquity, .50)
    println(aaplEquity.timeseries.last_px)
    println(aaplPosition)
  }
  
}
