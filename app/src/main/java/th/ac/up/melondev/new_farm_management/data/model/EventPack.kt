package th.ac.up.melondev.new_farm_management.data.model

data class EventPack(
    var chicken: Chicken = Chicken(),
    var event: Event = Event(),
    var day: String = "",
    var duration: Int = 0
): BaseProgramCard()