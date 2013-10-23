package data.pricing.model

import util.DateService
import math.statistics.std

class TimeSeries(arg: List[TimeSeriesBar]) {
  
  val ts = arg
  
  def volatility = {
    val mReturns = calculateMonthlyReturns
    val stDeviation = std(mReturns map (_._2))
    stDeviation
  }
  
  def calculateMonthlyReturns = {
    var dateService = new DateService
    ts groupBy (x => dateService.convertMonthYear(x.asofDate)) map (x=>
      x match {
        case (monthYear, bars) =>
          val mOpen = bars minBy (_.asofDate)
          val mClose = bars maxBy (_.asofDate)
          val mReturn = (mClose.close / mOpen.close) - 1.0
          (mClose.asofDate, mReturn)
      }
	)
  }
}

case class TimeSeriesBar (
    symbol: String,
    asofDate: java.util.Date,
    open: Double,
    high: Double,
    low: Double,
    close: Double,
    volume: Double)