package th.ac.up.melondev.new_farm_management.data.model

data class ChickenEventPack(
    val chicken: Chicken,
    val event :ArrayList<Event>
) : BaseProgramCard()