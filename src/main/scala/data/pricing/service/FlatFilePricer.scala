package data.pricing.service

import scala.io.Source
import data.pricing.model.TimeSeriesBar
import data.pricing.model.TimeSeries

class FlatFilePricer(dataPath: String = "/home/landerson/Dev/scala/alpha/data/ts.csv") extends PricerTrait {
  
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  
  def getTimeSeries(symbol: String): TimeSeries = {
    val path = dataPath
    val source = Source.fromFile(path)
    val lines = source.getLines
    
    val bars = for {
      l <- lines
      val x = l.split(",")
      if !l.startsWith("#")
      if symbol.toUpperCase.equals(x(7).toUpperCase)
    } yield TimeSeriesBar(x(7).toUpperCase, format.parse(x(0)), x(1).toDouble, x(2).toDouble, x(3).toDouble, x(6).toDouble, x(5).toDouble)
    
    val ts = new TimeSeries(bars toList)
    ts
  }
  
}