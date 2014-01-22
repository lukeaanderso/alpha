package data.pricing.service

import data.pricing.model.TimeSeries

trait PricerTrait {
  
  def getTimeSeries(symbol: String, factor: Option[String]): TimeSeries

}