package gov.hmrc.ddct.menu

trait Item
trait Drink extends Item

case object Cola extends Drink

case object Coffee extends Drink


