implicit class IntOps(n: Int) {
  def yeah() = times(_ => println("Oh yeah!"))

  def times(f: Int => Unit) = 
    for(i <- 0 until n) f(i)
}
