package data.pricing.model

case class TimeSeriesBar (
    symbol: String,
    asofDate: java.util.Date,
    open: Double,
    high: Double,
    low: Double,
    close: Double,
    volume: Double)