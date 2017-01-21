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
}