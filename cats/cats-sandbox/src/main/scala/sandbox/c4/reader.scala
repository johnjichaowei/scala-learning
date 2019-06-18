package sandbox.c4.reader

import cats.data.Reader
import cats.syntax.applicative._

case class Db(
  userNames: Map[Int, String],
  passwords: Map[String, String]
)

object ReaderTest {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.userNames.get(userId))

  def checkPassword(userName: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(userName).contains(password))

  def checkLogin1(userId: Int, password: String): DbReader[Boolean] =
    findUsername(userId).flatMap(x => x match {
      case Some(userName) => checkPassword(userName, password)
      case None => Reader(_ => false)
    })

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      userName <- findUsername(userId)
      passwordOK <- userName.map { userName =>
                      checkPassword(userName, password)
                    }.getOrElse {
                      false.pure[DbReader]
                    }
    } yield passwordOK

  val testDb = Db(
    Map(
      1 -> "Jack", 2 -> "Tom", 3 -> "Bob"
    ),
    Map(
      "Jack" -> "password01",
      "Tom"  -> "password02",
      "Bob"  -> "password03"
    )
  )
}
