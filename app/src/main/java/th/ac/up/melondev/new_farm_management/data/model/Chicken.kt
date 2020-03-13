package th.ac.up.melondev.new_farm_management.data.model

data class Chicken(
        var dateDay: String = "",
        var dateMonth: String = "",
        var dateYear: String = "",
        var amountMale: String = "",
        var amountFemale: String = "",
        var systemFarm: String = "",
        var userObjective: String = "",
        var status: String = "",
        var createDate: String = "",
        var lastUpdate: String = "",
        var moveToHistory: String = "",
        var cardName: String = "",
        var cardID: String = "",
        var breed: String = "",
        var ageDay: String = "",
        var ageWeek: String = "",
        var notification: String = "",
        var notiBefore: String = "",
        var managerName: String = "",
        var managerObjective: String = ""
) : BaseProgramCard()