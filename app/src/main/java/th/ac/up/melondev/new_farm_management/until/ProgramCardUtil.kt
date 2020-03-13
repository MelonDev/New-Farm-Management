package th.ac.up.melondev.new_farm_management.until

import android.content.Context
import android.util.Log
import th.ac.up.melondev.new_farm_management.data.model.*

class ProgramCardUtil(private val context: Context) {

    companion object {
        fun isEventActive(chicken: Chicken, event: Event): Boolean {
            return TimeUntil.getDurationOfChickenEvent(
                chicken,
                event
            ) >= 0 && event.status.contentEquals("ACTIVE")
        }

        fun sorting(data: ArrayList<BaseProgramCard>): ArrayList<BaseProgramCard> {
            return data.sortedBy {
                return@sortedBy when (it) {
                    is ChickenProgram -> TimeUntil.getCurrentChickenAge(it.chicken)
                    is ChickenEvent -> TimeUntil.getDurationOfChickenEvent(it.chicken, it.event)
                    else -> {
                        null
                    }
                }
            }.toCollection(ArrayList())
        }

        fun grouping(data: ArrayList<BaseProgramCard>): List<List<BaseProgramCard>> {
            return data.groupBy {
                return@groupBy when (it) {
                    is ChickenProgram -> TimeUntil.getCurrentChickenAge(it.chicken)
                    is ChickenEvent -> TimeUntil.getDurationOfChickenEvent(it.chicken, it.event)
                    else -> {
                        null
                    }
                }
            }.map { it.value }
        }
    }

    fun insertTitleHeader(data: List<List<BaseProgramCard>>): ArrayList<BaseProgramCard> {
        val resultData = ArrayList<BaseProgramCard>()

        if (data.isNotEmpty()) {
            data.map {
                var dateStr: String? = null
                when (it.first()) {
                    is ChickenProgram -> {
                        val chicken = (it.first() as ChickenProgram).chicken
                        dateStr = TimeUntil.capitalizeProgram(
                            context,
                            NormalDate(
                                chicken.dateYear.toInt(),
                                chicken.dateMonth.toInt(),
                                chicken.dateDay.toInt()
                            ),
                            TimeUntil.getCurrentChickenAge(chicken)
                        )
                    }
                    is ChickenEvent -> {
                        val chickenEvent = (it.first() as ChickenEvent)
                        dateStr = TimeUntil.capitalizeEvent(
                            context,
                            NormalDate(
                                chickenEvent.chicken.dateYear.toInt(),
                                chickenEvent.chicken.dateMonth.toInt(),
                                chickenEvent.chicken.dateDay.toInt()
                            ),
                            TimeUntil.getDurationOfChickenEvent(
                                chickenEvent.chicken,
                                chickenEvent.event
                            )
                        )
                    }
                    else -> {
                        null
                    }
                }
                dateStr?.let {str ->
                    resultData.add(TitleModel(str))
                    resultData.addAll(it)
                }

            }
        }

        return resultData
    }


}