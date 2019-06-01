sealed trait Color {
  def red: Double
  def green: Double
  def blue: Double

  def isLight = (red + green + blue) / 3.0 > 0.5
  def isDark = !isLight
}

case object Red extends Color {
  val red = 1.0
  val green = 0.0
  val blue = 0.0
}

case object Yellow extends Color {
  val red = 1.0
  val green = 1.0
  val blue = 0.0
}

case object Pink extends Color {
  val red = 1.0
  val green = 0.0
  val blue = 1.0
}

final case class CustomColor(
  red: Double,
  green: Double,
  blue: Double
) extends Color

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  val perimeter = 2*(width + height)
  val area = width * height
}

final case class Circle(radius: Double, color: Color) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

final case class Square(size: Double, color: Color) extends Rectangular {
  val width = size
  val height = size
}

case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular

object Draw {
  def apply(shape: Shape): String = shape match {
    case Rectangle(width, height, color) => 
      s"A ${Draw(color)} rectangle of width ${width}cm and height ${height} cm"

    case Square(size, color) => 
      s"A ${Draw(color)} square of size ${size}cm"

    case Circle(radius, color) =>
      s"A ${Draw(color)} circle of radius ${radius}cm"
  }

  def apply(color: Color): String = color match {
    case Red => "red"
    case Yellow => "yellow"
    case Pink => "pink"
    case color => if(color.isLight) "light" else "dark"
  }
}

val r = Rectangle(10, 11, Red)
val c = Circle(11.0, CustomColor(0.6, 0.8, 0.9))
val s = Square(14.0, Pink)
