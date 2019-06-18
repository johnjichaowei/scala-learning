package sandbox

import c4.reader._

object Main extends App {
  val db = ReaderTest.testDb
  println(s"Test DB: $db")

  val result1 = ReaderTest.findUsername(2).run(db)
  println(s"find Username result: $result1")

  val result2 = ReaderTest.checkPassword("Bob", "password03").run(db)
  println(s"check password result: $result2")

  val result3 = ReaderTest.checkLogin1(1, "password01").run(db)
  println(s"check login result: $result3")

  val result4 = ReaderTest.checkLogin(2, "password02").run(db)
  println(s"check login result: $result4")
}
