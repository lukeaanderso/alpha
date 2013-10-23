package data.pricing.service

import scala.io.Source
import data.pricing.model.TimeSeriesBar
import data.pricing.model.TimeSeries

class FlatFilePricer(dataDir: String = "C:/Users/LukeA/Dropbox/Prog/Eclipse/alpha/data") {
  
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  
  def get(symbol: String) = {
    val path = dataDir + "/" + symbol.toLowerCase + ".csv"
    val source = Source.fromFile(path)
    val lines = source.getLines
    
    val bars = for {
      l <- lines
      val x = l.split(",")
      if !l.startsWith("#")      
    } yield TimeSeriesBar(symbol.toUpperCase, format.parse(x(0)), x(1).toDouble, x(2).toDouble, x(3).toDouble, x(6).toDouble, x(5).toDouble)
    
    val ts = new TimeSeries(bars toList)
    ts
  }
  
}