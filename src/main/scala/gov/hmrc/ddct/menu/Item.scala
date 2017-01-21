package gov.hmrc.ddct.menu

trait Item
trait Drink extends Item

trait HotFood extends Item

trait NonHotFood extends Item
case object Cola extends Drink
case object Coffee extends Drink

case object CheeseSandwich extends NonHotFood

case object SteakSandwich extends HotFood


