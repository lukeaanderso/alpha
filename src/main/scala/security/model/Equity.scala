package security.model

case class Equity(symbol: String) extends Instrument {
  
  val timeseries = pricer.getTimeSeries(symbol)
  
}