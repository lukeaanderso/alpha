package security.model

import data.pricing.service.FlatFilePricer

trait Instrument {
  
  val pricer = new FlatFilePricer

}