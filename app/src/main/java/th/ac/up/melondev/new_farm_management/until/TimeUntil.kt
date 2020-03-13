package th.ac.up.melondev.new_farm_management.until

import android.content.Context
import android.util.Log
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.model.Chicken
import th.ac.up.melondev.new_farm_management.data.model.Event
import th.ac.up.melondev.new_farm_management.data.model.NormalDate

class TimeUntil {

    companion object {

        fun getPastDateDuration(calendar: LocalDate): Int {
            val current: LocalDateTime = getCurrentDateTime()
            val eventDateTime: LocalDateTime = getCurrentDateTime(calendar)

            return Duration.between(eventDateTime, current).toDays().toInt()
        }

        fun getFutureDateDuration(calendar: LocalDate): Int {
            val current: LocalDateTime = getCurrentDateTime()
            val eventDateTime: LocalDateTime = getCurrentDateTime(calendar)

            return Duration.between(current, eventDateTime).toDays().toInt()
        }

        fun getCalendarOfEvent(calendar: LocalDate, event: Event): LocalDate {
            return calendar.plusWeeks(event.week.toLong()).plusDays(event.day.toLong())
        }

        fun getDurationOfChickenEvent(chicken: Chicken, event: Event): Int {
            return getFutureDateDuration(
                getCalendarOfEvent(
                    getCalendarOfChicken(chicken),
                    event
                )
            )
        }

        fun getCalendarOfChicken(detail: Chicken): LocalDate {
            return LocalDate.of(
                    detail.dateYear.toInt(),
                    detail.dateMonth.toInt(),
                    detail.dateDay.toInt()
                ).plusWeeks((0 - detail.ageWeek.toInt()).toLong())
                .plusDays((0 - detail.ageDay.toInt()).toLong())
        }

        fun getCurrentDate(): LocalDate = LocalDate.now()

        fun getCurrentDateTime(calendar: LocalDate) =
            LocalDateTime.of(calendar, LocalTime.of(0, 0, 0))

        fun getCurrentDateTime() = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0))

        fun getCurrentChickenAge(chicken: Chicken): Int =
            getPastDateDuration(getCalendarOfChicken(chicken))

        fun getCurrentChickenAgeOnFormat(context: Context, chicken: Chicken): String {
            val age = getCurrentChickenAge(chicken)
            if (age >= 0) {
                val weekDay: WeekDay = addToWeekDay(
                    WeekDay(chicken.ageWeek.toInt(), chicken.ageWeek.toInt()),
                    generateWeekDay(age)
                )
                return "${weekDay.week} ${context.getString(R.string.week)} ${weekDay.day} ${context.getString(
                    R.string.day
                )}"
            } else {
                return context.getString(R.string.not_date_accerpt)
            }
        }

        fun generateWeekDay(day: Int): WeekDay = WeekDay(day / 7, day % 7)

        fun addToWeekDay(weekDay: WeekDay, newWeekDay: WeekDay): WeekDay =
            refreshWeekDay(WeekDay(weekDay.week + newWeekDay.week, weekDay.day + newWeekDay.day))

        fun addDayToLocalDate(normalDate: NormalDate ,duration: Int) :NormalDate {
            val date = LocalDate.of(
                normalDate.year,
                normalDate.month,
                normalDate.day
            ).plusDays(duration.toLong())

            return NormalDate(date.year,date.monthValue,date.dayOfMonth)
        }

        fun refreshWeekDay(weekDay: WeekDay): WeekDay {
            if (weekDay.day >= 7) {
                val weekDayFromDay = generateWeekDay(weekDay.day)
                return WeekDay(weekDay.week + weekDayFromDay.week, weekDayFromDay.day)
            }
            return weekDay
        }

        fun capitalizeEvent(context: Context, date: NormalDate, duration: Int): String {
            return when (duration) {
                0 -> context.getString(R.string.today)
                1 -> context.getString(R.string.yesterday)
                else -> {
                    context.let { con ->
                        val resultDate = addDayToLocalDate(date,duration)
                        "${(resultDate.day)} ${ConvertCard(con).getMonth(resultDate.month)} ${ConvertCard(
                            con
                        ).getYear(resultDate.year)}"
                    }
                }
            }
        }

        fun capitalizeProgram(context: Context, date: NormalDate, duration: Int): String {
            return when (duration) {
                0 -> context.getString(R.string.today)
                1 -> context.getString(R.string.yesterday)
                else -> {
                    context.let { con ->
                        "${(date.day)} ${ConvertCard(con).getMonth(date.month)} ${ConvertCard(con).getYear(date.year)}"
                    }
                }
            }
        }
    }

    data class WeekDay(
        val week: Int,
        val day: Int
    )

}