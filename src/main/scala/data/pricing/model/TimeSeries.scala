package data.pricing.model

import util.DateService
import math.statistics.{stdev, beta}

class TimeSeries(arg: List[TimeSeriesBar]) {
  
  private val ts = arg
  
  def volatility(returns: List[ReturnBar]): Double = {
    stdev(returns map (_.ret))
  }
  
  def calculateMonthlyReturns = {
    val dateService = new DateService
    ts groupBy (x => dateService.convertMonthYear(x.asofDate)) map (x=>
      x match {
        case (monthYear, bars) =>
          val mOpen = bars minBy (_.asofDate)
          val mClose = bars maxBy (_.asofDate)
          val mReturn = (mClose.close / mOpen.close) - 1.0
          ReturnBar(mClose.asofDate, mClose.close, mReturn)
      }
	)
  }
  
  def getRecentReturns(months: Int = 36) = {
    ((calculateMonthlyReturns toList) sortBy (_.asofDate) reverse) take (months)
  }
  
  def calculateMonthlyVolatility = {
    val mReturns = getRecentReturns()
    volatility(mReturns)
  }
  
  def last_px = {
    (ts sortBy(_.asofDate) head).close
  }

  def exposure(l: Iterable[ReturnBar]) = {
    val bars = calculateMonthlyReturns.map(_.ret)
    val thoseBars = l.map(_.ret)
    beta(bars, thoseBars)
  }
  
}