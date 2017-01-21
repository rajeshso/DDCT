package gov.hmrc.ddct.cart

import gov.hmrc.ddct.UnitSpec
import gov.hmrc.ddct.gov.hmrc.ddct.tip.EnableServiceCharge
import gov.hmrc.ddct.menu.{CheeseSandwich, Coffee, Cola, SteakSandwich}

class ShoppingCartTest extends UnitSpec {

  "MyShoppingCart" should "calculate the item count" in {
    val myCart = new ShoppingCart()
    myCart.itemCount should be(0)
  }

  "MyShoppingCart" should "add Cola and Coffee" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
  }
  "MyShoppingCart" should "record the number of items as two" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart.itemCount should be(2)
  }
  "MyShoppingCart" should "record the multiples of same items as individual items" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart += Coffee
    myCart.itemCount should be(3)
  }
  "MyShoppingCart" should "add different kinds of items record the number of items as three" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart += CheeseSandwich
    myCart.itemCount should be(3)
  }

  "MyShoppingCart" should "add varieties of items and record the multiples of same items as individual items" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart += Coffee
    myCart += CheeseSandwich
    myCart.itemCount should be(4)
  }
  "MyShoppingCart" should "add a Cola and tell the price as 0.5 when there is no service charge" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart.itemCount should be(1)
    myCart.cartItemsTotalPrice should be(0.5)
  }

  "MyShoppingCart" should "add a Cola and a Coffee and tell the price as 1.5 when there is no service charge" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart.itemCount should be(2)
    myCart.cartItemsTotalPrice should be(1.5)
  }

  "MyShoppingCart" should "add two Colas and Coffee and tell the price as 2 and the total items should be 3 when there is no offer" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart += Cola
    myCart.cartItemsTotalPrice should be(2)
    myCart.itemCount should be(3)
  }
  "MyShoppingCart" should "add one of all the available Items and tell the price as 8 and the total items should be 4 when there is no offer" in {
    val myCart = new ShoppingCart()
    myCart += Cola
    myCart += Coffee
    myCart += CheeseSandwich
    myCart += SteakSandwich
    myCart.cartItemsTotalPrice should be(8)
    myCart.itemCount should be(4)
  }
  "MyShoppingCart" should "accept two Colas but there should not be any service charges" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += Cola
    cart.cartItemsTotalPrice should be(0.5)
    cart.itemCount should be(1)
    cart += Cola
    cart.itemCount should be(2)
    cart.cartItemsTotalPrice should be(1.0)
  }

  "MyShoppingCart" should "accept one Hot Food and the price should include service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += SteakSandwich
    cart.itemCount should be(1)
    cart.cartItemsTotalPrice should be(5.4)
  }

  "MyShoppingCart" should "accept one Hot Food and one Drink and the price should include 20% service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += SteakSandwich
    cart += Coffee
    cart.itemCount should be(2)
    cart.cartItemsTotalPrice should be(6.6)
  }
  "MyShoppingCart" should "accept one Non Hot Food and should add 10% of service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += CheeseSandwich
    cart.itemCount should be(1)
    cart.cartItemsTotalPrice should be(2.2)
  }
  "MyShoppingCart" should "accept one Non Hot Food and One Drink and should add 10% of service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += CheeseSandwich
    cart += Cola
    cart.itemCount should be(2)
    cart.cartItemsTotalPrice should be(2.75)
  }
  "MyShoppingCart" should "accept one Non Hot Food  and One Hot Food and should add 20% of service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += CheeseSandwich
    cart.itemCount should be(1)
    cart += SteakSandwich
    cart.itemCount should be(2)
    cart.cartItemsTotalPrice should be(7.8)
  }
  "MyShoppingCart" should "accept one Non Hot Food, One Hot Food and One Drink and should add 20% of service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += CheeseSandwich
    cart += SteakSandwich
    cart += Cola
    cart.cartItemsTotalPrice should be(8.4)
  }
  "MyShoppingCart" should "accept two of all foods and drinks and should add 20% of service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart += CheeseSandwich
    cart += SteakSandwich
    cart += Cola
    cart += Coffee
    cart += CheeseSandwich
    cart += SteakSandwich
    cart += Cola
    cart += Coffee
    cart.itemCount should be(8)
    cart.cartItemsTotalPrice should be(19.2)
  }
  "MyShoppingCart" should "When purchased items include any hot food apply a service charge of 20% to the total bill with a maximum £20 service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart.itemCount should be(0)
    cart += SteakSandwich
    cart.itemCount should be(1)
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart.itemCount should be (101)
    // Coffee 100 cups = $100
    // Hot Food 1 plate = $4.5
    // Total = 104.5
    // Service charge = 20.9
    // Actual Total with non-Max Service Charge = 104.5 + 20.9 = 125.4
    // Max Service Charge ceiling = 20
    // Total + Service charge ceiling = 104.5 + 20 = 124.5
    cart.cartItemsTotalPrice should be(124.5)
  }
  "MyShoppingCart" should "When purchased items does NOT include any Non hot food apply a service charge of 10% to the total bill but WITHOUT a maximum CEILING service charge" in {
    val cart = new ShoppingCart(EnableServiceCharge)
    cart.itemCount should be(0)
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;cart += CheeseSandwich;
    cart.itemCount should be(100)
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart += Coffee; cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;cart += Coffee;
    cart.itemCount should be (210)
    // Cola -> 0.5, Coffee -> 1, CheeseSandwich -> 2, SteakSandwich -> 4.5
    // Coffee 110 cups * $1 = $110
    // Cheese Sandwich 100 plates = $200
    // Total = 310
    // Service charge = 31
    // Actual Total with non-Max Service Charge = 310 + 31 = 341
    cart.cartItemsTotalPrice should be(341)
  }
}