package th.ac.up.melondev.new_farm_management.until

import android.content.Context
import th.ac.up.melondev.new_farm_management.R

class ConvertCard(private val context: Context) {

    private val arrSystem = ArrayList<String>()
    private val arrObjective = ArrayList<String>()
    private val arrMonth = ArrayList<String>()

    //add(getString(R.string.))


    fun getSystem(position: String): String {
        arrSystem.apply {
            add(getString(R.string.liver_cage))
            add(getString(R.string.prison_cell))
            add(getString(R.string.release))
            add(getString(R.string.semi))
            add(getString(R.string.organic))
        }
        return arrSystem[position.toInt()]
    }

    fun getSystem(): ArrayList<String> {
        arrSystem.apply {
            add(getString(R.string.liver_cage))
            add(getString(R.string.prison_cell))
            add(getString(R.string.release))
            add(getString(R.string.semi))
            add(getString(R.string.organic))
        }
        return arrSystem
    }

    fun getMonth(month: String): String {
        arrMonth.apply {
            add(getString(R.string.january))
            add(getString(R.string.february))
            add(getString(R.string.march))
            add(getString(R.string.april))
            add(getString(R.string.may))
            add(getString(R.string.june))
            add(getString(R.string.july))
            add(getString(R.string.august))
            add(getString(R.string.september))
            add(getString(R.string.october))
            add(getString(R.string.november))
            add(getString(R.string.december))
        }
        return arrMonth[month.toInt() - 1]
    }

    fun getMonth(month: Int): String {
        arrMonth.apply {
            add(getString(R.string.january))
            add(getString(R.string.february))
            add(getString(R.string.march))
            add(getString(R.string.april))
            add(getString(R.string.may))
            add(getString(R.string.june))
            add(getString(R.string.july))
            add(getString(R.string.august))
            add(getString(R.string.september))
            add(getString(R.string.october))
            add(getString(R.string.november))
            add(getString(R.string.december))
        }
        return arrMonth[month - 1]
    }


    fun getArrMonth(): ArrayList<String> {
        arrMonth.apply {
            add(getString(R.string.january))
            add(getString(R.string.february))
            add(getString(R.string.march))
            add(getString(R.string.april))
            add(getString(R.string.may))
            add(getString(R.string.june))
            add(getString(R.string.july))
            add(getString(R.string.august))
            add(getString(R.string.september))
            add(getString(R.string.october))
            add(getString(R.string.november))
            add(getString(R.string.december))
        }
        return arrMonth
    }

    fun getYear(year: String): String {
        return (year.toInt() + 543 - (getString(R.string.minus_year).toInt())).toString()
    }

    fun getYear(year: Int): String {
        return (year + 543 - (getString(R.string.minus_year).toInt())).toString()
    }

    fun getBool(bool: Boolean): String {
        return if (bool) {
            "เปิด"
        } else {
            "ปิด"
        }
    }

    fun getObjective(position: String): String {
        arrObjective.apply {
            add(getString(R.string.breeder_chicken))
            add(getString(R.string.sell_chicks))
            add(getString(R.string.chicken_meat))
            add(getString(R.string.contest))
            add(getString(R.string.other))
        }
        return arrObjective[position.toInt()]
    }

    fun getObjective(): ArrayList<String> {
        arrObjective.apply {
            add(getString(R.string.breeder_chicken))
            add(getString(R.string.sell_chicks))
            add(getString(R.string.chicken_meat))
            add(getString(R.string.contest))
            add(getString(R.string.other))
        }
        return arrObjective
    }

    fun getNotiObjective(): ArrayList<String> {
        arrObjective.apply {
            add(getString(R.string.vaccination))
            add(getString(R.string.give_the_light))
            add(getString(R.string.cut_the_mouth))
            add(getString(R.string.parasite_infection))
            add(getString(R.string.other))

        }
        return arrObjective
    }

    fun getNotiObjective(position: String): String {
        arrObjective.apply {
            add(getString(R.string.vaccination))
            add(getString(R.string.give_the_light))
            add(getString(R.string.cut_the_mouth))
            add(getString(R.string.parasite_infection))
            add(getString(R.string.other))
        }
        return arrObjective[position.toInt()]
    }

    fun getObjectiveString(objective: String): String {
        return when(objective){
            "ฉีดวัคซีน" -> context.getString(R.string.vaccination)
            "ให้แสงสว่าง" -> context.getString(R.string.give_the_light)
            "ตัดปาก" -> context.getString(R.string.cut_the_mouth)
            "ถ่ายพยาธิ" -> context.getString(R.string.parasite_infection)
            else -> context.getString(R.string.other)

        }
    }

    private fun getString(resource: Int): String {
        return context.getString(resource)
    }


}
