trait HtmlWriter[A] {
  def write(in: A): String
}

implicit class HtmlOps[T](data: T) {
  def toHtml(implicit writer: HtmlWriter[T]) = 
    writer.write(data)
}

final case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = 
    s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}

