object PizzaServices {
  case class PizzaOrder(pizza: Pizza, amount: Int) {
    def price = amount * pizza.price
  }
  case class Pizza(name: String, diameter: Int, price: Int) {
    def times(amount: Int): PizzaOrder = PizzaOrder(this, amount)
  }

  def areaOfSinglePizza(d: Double): Double = Math.PI * Math.pow(d / 2, 2)

  trait PizzaService {
    def diameter: Int

    def name: String = getClass.getSimpleName.filter(_.isLetter)

    def orderedPizzas: Seq[PizzaOrder]

    def veggieOrder: PizzaOrder

    def pizzaCount = orderedPizzas.map(_.amount).sum

    def areaPerPizza = areaOfSinglePizza(diameter)
    def area = pizzaCount * areaPerPizza
    def price = orderedPizzas.map(_.price).sum

    def veggieArea = areaPerPizza * veggieOrder.amount

    override def toString = name
    def pizza(name: String, price: Int) = Pizza(name, diameter, price)
  }

  object Smilieys extends PizzaService {
    val diameter = 38

    val veggie = pizza("New Holland", 1590) times 2
    val gyros = pizza("New Arizona", 1895) times  1
    val salame = pizza("Bakersfield", 1745) times 1
    val hamNcream = pizza("Buffalo", 1590) times 1
    val hamNrucola = pizza("Indiana", 1860) times 1

    val orderedPizzas = Seq(veggie, gyros, salame, hamNcream, hamNrucola)
    val veggieOrder = veggie
  }

  object HalloPizza extends PizzaService {
    val diameter = 36

    val veggie = pizza("California", 1810) times 2
    val gyros = pizza("Olymp", 1810) times 2
    val salame = pizza("Dixieland", 1690) times 2
    val hamNrucola = pizza("Luftikuss", 1750) times 2

    val orderedPizzas = Seq(veggie, gyros, salame, hamNrucola)
    val veggieOrder = veggie
  }

//  object PizzaMax extends PizzaService {
//    val diameter = 32
//
//    val veggie = pizza("Vegetaria", 1140) times 2
//    val hamNsalameNchillie = pizza("Mista", 1190) times 2
//    val chickenCurry = pizza("Chicken Curry", 1140) times 1
//    val mexico = pizza("Mexico", 1390) times 1
//
//    val orderedPizzas = Seq(veggie, hamNsalameNchillie, chickenCurry, mexico)
//    val veggieOrder = veggie
//  }

}