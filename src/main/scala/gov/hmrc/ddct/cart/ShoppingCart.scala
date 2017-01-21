package gov.hmrc.ddct.cart

import gov.hmrc.ddct.gov.hmrc.ddct.tip.{DisableServiceCharge, EnableServiceCharge, ServiceCharge}
import gov.hmrc.ddct.menu._

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

    additionalCharges match {
      case EnableServiceCharge =>
        ???
      case DisableServiceCharge => BigDecimal(totalBilllWithoutServiceCharge).setScale(2, RoundingMode.HALF_UP).toDouble
    }
  }

}
