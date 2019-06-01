trait HtmlWriter[A] {
  def write(in: A): String
}

final case class Person(name: String, email: String)

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

PersonWriter.write(Person("John", "john@example.com"))

import java.util.Date
object DateWriter extends HtmlWriter[Date] {
  def write(in: Date) = s"<span>${in.toString}</span>"
}

DateWriter.write(new Date)

object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = 
    s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}

ObfuscatedPersonWriter.write(Person("John", "john@example.com"))
