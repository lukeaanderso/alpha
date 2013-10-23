package util

import java.util.Calendar

class DateService {
  
  var cal = Calendar.getInstance()
  
  def convertMonthYear(date: java.util.Date):MonthYear = {
    cal.setTime(date)
    var month = cal.get(Calendar.MONTH)
    var year = cal.get(Calendar.YEAR)
    new MonthYear(month, year)
  }
  
  def convertMonthYear(date: java.sql.Date):MonthYear = {
    convertMonthYear(date.asInstanceOf[java.util.Date])
  }

}

case class MonthYear (month: Int, year: Int)