trait HtmlWriter[A] {
  def write(in: A): String
}

object HtmlWriter {
  def write[A](in: A)(implicit writer: HtmlWriter[A]): String = {
    writer.write(in) 
  }

  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] =    writer 
}

final case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

PersonWriter.write(Person("John", "john@example.com"))

import java.util.Date
implicit object DateWriter extends HtmlWriter[Date] {
  def write(in: Date) = s"<span>${in.toString}</span>"
}

DateWriter.write(new Date)

object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = 
    s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}
