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
	  val stDev = sqrt(l.reduceLeft((r, c) => ((r - mn)*(r -mn)) + c))
	  stDev
	}
  }
  
}