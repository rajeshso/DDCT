package gov.hmrc.ddct.cart

import gov.hmrc.ddct.menu.Item

class ShoppingCart {
  private var items = List.empty[Item]

  def itemCount: Int = items.size

  def +=(item: Item): Unit = items = items :+ item
}
