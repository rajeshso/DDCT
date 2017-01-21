package gov.hmrc.ddct.cart

import gov.hmrc.ddct.gov.hmrc.ddct.tip.{DisableServiceCharge, EnableServiceCharge, ServiceCharge}
import gov.hmrc.ddct.menu._

import scala.language.postfixOps
import scala.math.BigDecimal.RoundingMode

class ShoppingCart(additionalCharges: ServiceCharge = DisableServiceCharge) {
  private var items = List.empty[Item]
  private val prices: Map[Item, Double] = Map(Cola -> 0.5, Coffee -> 1, CheeseSandwich -> 2, SteakSandwich -> 4.5)

  def itemCount: Int = items.size

  def +=(item: Item): Unit = items = items :+ item

  def cartItemsTotalPrice: Double = {
    def totalBilllWithoutServiceCharge: Double =
      items.foldLeft(0D) { (total, item) =>
        total + prices(item)
      }

    def totalBillWithServiceCharge: Double = {
      val billWithoutServiceCharge = totalBilllWithoutServiceCharge
      if (items collect { case hotFood: HotFood => hotFood } nonEmpty) {
        billWithoutServiceCharge + hotFoodServiceCharge(billWithoutServiceCharge)
      } else
        billWithoutServiceCharge
    }

    def hotFoodServiceCharge(bill: Double): Double = bill * 0.2

    additionalCharges match {
      case EnableServiceCharge => BigDecimal(totalBillWithServiceCharge).setScale(2, RoundingMode.HALF_UP).toDouble
      case DisableServiceCharge => BigDecimal(totalBilllWithoutServiceCharge).setScale(2, RoundingMode.HALF_UP).toDouble
    }
  }

}
