package th.ac.up.melondev.new_farm_management.until

import android.content.Context
import androidx.core.content.ContextCompat
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.model.TodoStyle

class StyleUtil {
    companion object {
        fun getStyleFromObjective(context: Context, objective: String): TodoStyle {

            return when (objective) {
                "ฉีดวัคซีน" -> {
                    TodoStyle(
                        R.drawable.ic_inject_icon,
                        ContextCompat.getColor(context, R.color.colorLightBlue)
                    )
                }
                "ถ่ายพยาธิ" -> {
                    TodoStyle(
                        R.drawable.ic_parasite_icon,
                        ContextCompat.getColor(context, R.color.colorLightGreen)
                    )
                }
                "ตัดปาก" -> {
                    TodoStyle(
                        R.drawable.ic_cut_icon,
                        ContextCompat.getColor(context, R.color.colorRed)
                    )

                }
                "ให้แสงสว่าง" -> {
                    TodoStyle(
                        R.drawable.ic_sun_icon,
                        ContextCompat.getColor(context, R.color.colorAmber)
                    )

                }
                else -> {
                    TodoStyle(
                        R.drawable.ic_chicken_icon,
                        ContextCompat.getColor(context, R.color.colorDeepPurple)
                    )

                }
            }
        }
    }
}