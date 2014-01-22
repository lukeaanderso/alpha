package data.pricing.model

case class TimeSeriesBar (
    symbol: String,
    asofDate: java.util.Date,
    value: Double)