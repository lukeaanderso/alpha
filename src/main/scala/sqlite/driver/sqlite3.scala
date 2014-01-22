package sqlite.driver


import scala.sys.process._

/**
 * Created by landerson on 1/20/14.
 */
class sqlite3(val dbPath: String) {

  val sqlite3 = "/usr/bin/sqlite3"

  val helper = Seq(sqlite3, dbPath)

  def _execute(command: String): String = {
    val commandSeq = helper :+ command
    commandSeq.!!
  }

  def execute(command: String): Iterable[String] ={
    val l = _execute(command)
    val out = l.split("\\n")
    out
  }

}

object sqlite3 {
  def apply(dbPath: String) = new sqlite3(dbPath)
}
