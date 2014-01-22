
import data.pricing.service.SQLItePricer

object MySQLScript {

  def main(args: Array[String]): Unit = {
    val pricer = new SQLItePricer()
    val ts = pricer.getTimeSeries("AAPL", "close")
    val returns = ts.calculateMonthlyVolatility
    println(returns)
  }
}