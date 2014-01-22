package data.pricing.service

import data.pricing.model.{TimeSeriesBar, TimeSeries}
import sqlite.driver.sqlite3

/**
 * Created by landerson on 1/21/14.
 */
class SQLItePricer(dbPath: String = "/home/landerson/Dev/scala/alpha/data/alpha.db") extends PricerTrait {

  val format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

  val db = sqlite3(dbPath)

  def getTimeSeries(symbol: String, factor: Option[String]): TimeSeries = {
    getTimeSeries(symbol, factor.get)
  }

  def getTimeSeries(symbol: String, factor: String): TimeSeries = {

    val query = s"select * from fundm where symbol='$symbol' and factor='$factor'"
    val series = db execute query

    val bars = for (s <- series) yield {
      val tuple = s split '|'
      val date = format.parse(tuple(1))
      val t = tuple(3).toDouble
      TimeSeriesBar(symbol, date, t)
    }

    val ts = new TimeSeries(bars toList)
    ts

  }


}
