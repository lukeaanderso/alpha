package data.pricing.service

import data.pricing.model.TimeSeries

trait PricerTrait {
  
  def getTimeSeries(symbol: String): TimeSeries

}