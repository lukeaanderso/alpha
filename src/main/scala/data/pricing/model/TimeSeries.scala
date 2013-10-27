package data.pricing.model

import util.DateService
import math.statistics.{std, cov}

class TimeSeries(arg: List[TimeSeriesBar]) {
  
  private val ts = arg
  
  def volatility(returns: List[ReturnBar]): Double = {
    std(returns map (_.ret))
  }
  
  def calculateMonthlyReturns = {
    var dateService = new DateService
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
  
  def beta(that: List[ReturnBar]) = {
    val mReturns = getRecentReturns()
    cov(mReturns.map(_.ret), that.map(_.ret))
  }
  
  def last_px = {
    (ts sortBy(_.asofDate) head).close
  }
  
}