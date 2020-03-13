package th.ac.up.melondev.new_farm_management.data.model

data class Event(
        var title: String = "",
        var message: String = "",
        var week: Int = 0,
        var day: Int = 0,
        var cardID: String = "",
        var fromID: String = "",
        var status: String = "",
        var totalDay: Int = 0
) :BaseProgramCard()