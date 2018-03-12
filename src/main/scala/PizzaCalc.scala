import PizzaServices._

object PizzaCalc {
  val pizzaServices = Seq(Smilieys, HalloPizza)//, PizzaMax)

  val numberOfPersons = 25
  val normalSized = 26

  val areaPerPerson = areaOfSinglePizza(normalSized)
  val areaForAll = areaPerPerson * numberOfPersons

  def totalArea = pizzaServices.map(_.area).sum
  def totalVeggieArea = pizzaServices.map(_.veggieArea).sum

  def totalPrice = pizzaServices.map(_.price).sum

  def formatArea(area: Double) = f"$area%.2fcm²"
  def formatPrice(price: Int) = f"${price/100.0}%.2f€"

  val veggieRatio = totalVeggieArea/totalArea*100

  def printTotalArea() = {
    println(s"Area required for all $numberOfPersons persons: ${formatArea(areaForAll)}")
    println
  }

  def printAreaSizesByService() = {
    println("Area by diameter")
    pizzaServices.foreach(s => println(s"\t$s (${s.diameter}): ${formatArea(s.areaPerPizza)}"))
    println
  }

  def printNumberOfPizzas() = {
    println(s"How many would I need for $numberOfPersons persons?")
    pizzaServices.foreach(s => {
      val numberOfPizzas = areaForAll / s.areaPerPizza
      println(f"\t$s (${s.diameter}): $numberOfPizzas%.1f")
    })
    println
  }

  def printOrder() = {
    pizzaServices.foreach { service =>
      println(s"${service.name} (${service.pizzaCount} pizzas) => ${formatArea(service.area)} => ${formatPrice(service.price)}")
      service.orderedPizzas.foreach { order =>
        val areaOfThisPizza = order.amount * service.areaPerPizza
        println(s"\t${order.pizza.name} (${order.amount} * ${formatPrice(order.pizza.price)}) => ${formatArea(areaOfThisPizza)}")
      }
      println
    }
    println(s"Total price: ${formatPrice(totalPrice)}")
    println(s"Total area: ${formatArea(totalArea)}")
  }

  def printVeggieTotal() = {
    println(f"Total veggie: ${formatArea(totalVeggieArea)} - this is : $veggieRatio%.2f%%\n")
  }

  def main(args: Array[String]) = {
    printTotalArea()
    printAreaSizesByService()
    printNumberOfPizzas()
    printOrder()
    printVeggieTotal()
  }
}