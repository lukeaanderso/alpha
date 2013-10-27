import scala.math.sqrt

package math { 

  package object statistics {
  
	def mean(l: Iterable[Double]): Double = {
	  val sum = l reduceLeft(_+_)
	  val len = (l size).toDouble
	  val mn = sum / len 
	  mn
	}
	
	def std(l: Iterable[Double]): Double = {
	  val mn = mean(l)
	  val stDev = sqrt(l.reduceLeft((c, r) => ((r - mn)*(r -mn)) + c))
	  stDev
	}
	
	def variance(l: Iterable[Double]): Double = {
	  val stDev = std(l)
	  val stDevSq = stDev * stDev
	  stDevSq
	}
	
	def cov(l1: Iterable[Double], l2: Iterable[Double]) = {
	  val mn1 = mean(l1)
	  val mn2 = mean(l2)
	  val len = (l1 size).toDouble
	  
	  val num = sqrt((l1.zip(l2)) map(j => ((j._1 - mn1)*(j._2 - mn2))) reduceLeft(_+_))
	  val covariance = num / len
	  covariance
	}
  }
}
 