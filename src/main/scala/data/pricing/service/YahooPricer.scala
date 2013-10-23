package data.pricing.service

import java.net.URL
import scala.io.Source.fromInputStream

class YahooPricer {

	val url = "http://finance.yahoo.com/d/quotes.csv?"
	
	def get(symbol: String, field: String) = {
	  var symbolString = "s=" + symbol
	  var fieldString = "f=" + field
	  var myUrl = new URL(url + symbolString + "&" + fieldString) 
	  val content = fromInputStream( myUrl.openStream ).getLines.mkString("\n")
	  println(content)
	  content
	}

}