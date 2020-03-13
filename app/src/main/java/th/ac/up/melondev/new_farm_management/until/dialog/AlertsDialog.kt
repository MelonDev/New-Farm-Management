package th.ac.up.agr.thai_mini_chicken.Logic.Dialog

import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import th.ac.up.melondev.new_farm_management.R

class AlertsDialog(private val mFragmentActivity: FragmentActivity) : QuickCircleDialog(mFragmentActivity) {

    companion object {
        fun showAlertDialog(activity: FragmentActivity,resource: Int) {
            AlertsDialog(activity).setTitle(resource).positive(R.string.ok_message_response).build().show()
        }
    }

    override fun setTitle(message: Int): QuickCircleDialog {
        this.MESSAGE = message
        return this
    }

    override fun build(): QuickCircleDialog {

        super.MESSSAGE_TEXT_COLOR = R.color.colorPrimary
        super.POSITIVE_TEXT_COLOR = R.color.colorText

        val resource = mFragmentActivity.resources


        super.mDialog.setText(mFragmentActivity.getString(MESSAGE)).configText { params ->
            params?.let {
                it.textSize = resource.getInteger(R.integer.dialog_message_text)
                it.textColor = ContextCompat.getColor(mFragmentActivity.applicationContext, MESSSAGE_TEXT_COLOR)
                it.padding = intArrayOf(50, 50, 50, 50) //(L // eft,TOP,Right,Bottom)
            }

        }.setPositive(mFragmentActivity.getString(POSITIVE)) {
            super.positiveAction()
        }.configPositive { params ->
            params?.let {
                it.height = resource.getInteger(R.integer.dialog_positive_button_height)
                it.textSize = resource.getInteger(R.integer.dialog_positive_text_button)
                it.textColor = ContextCompat.getColor(mFragmentActivity.applicationContext, POSITIVE_TEXT_COLOR)
            }

        }

        return this
    }




}