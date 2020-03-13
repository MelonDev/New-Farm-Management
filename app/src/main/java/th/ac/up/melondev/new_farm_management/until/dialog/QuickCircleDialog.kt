package th.ac.up.agr.thai_mini_chicken.Logic.Dialog

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.mylhyl.circledialog.CircleDialog
import th.ac.up.melondev.new_farm_management.R

open class QuickCircleDialog(private val mFragmentActivity: FragmentActivity) {

    var mDialog: CircleDialog.Builder

    var NEGATIVE_TEXT_COLOR: Int = R.color.colorText
    var POSITIVE_TEXT_COLOR: Int = R.color.colorPrimary
    var TITLE_TEXT_COLOR: Int = R.color.colorPrimary
    var MESSSAGE_TEXT_COLOR: Int = R.color.colorText

    var BACKGROUND_COLOR: Int = R.color.colorSubBackground


    var MESSAGE: Int = R.string.dialog_message_text_default
    var TITLE: Int = R.string.dialog_title_text_default
    var NEGATIVE: Int = R.string.dialog_negative_text_default
    var POSITIVE: Int = R.string.dialog_positive_text_default

    var WAIT: Int = R.string.dialog_progress_text_default

    var positiveAction: () -> Unit = {}
    var negativeAction: (() -> Unit) = {}

    init {
        mDialog = CircleDialog.Builder().configDialog { params ->
            params.canceledOnTouchOutside = false
            params.width = (mFragmentActivity.resources.getInteger(R.integer.dialog_winth).toDouble() / 10).toFloat()
            params.backgroundColor = ContextCompat.getColor(mFragmentActivity,BACKGROUND_COLOR)
            params.backgroundColorPress = ContextCompat.getColor(mFragmentActivity,R.color.colorOnClickBackground)
        }

        //Log.e("SSS",(mFragmentActivity.resources.getInteger(R.integer.dialog_winth).toDouble() / 10).toString())
    }

    fun positive(posText: Int = R.string.dialog_positive_text_default, lambda: () -> Unit = {}): QuickCircleDialog {
        this.POSITIVE = posText
        this.positiveAction = lambda
        return this
    }

    fun setMessage(message: Int): QuickCircleDialog {
        this.MESSAGE = message
        return this
    }

    open fun setTitle(message: Int): QuickCircleDialog {
        this.TITLE = message
        return this
    }

    fun setProgressText(message: Int = R.string.dialog_progress_text_default): QuickCircleDialog {
        WAIT = message
        return this
    }

    fun negative(negText: Int = R.string.dialog_negative_text_default, lambda: () -> Unit = {}): QuickCircleDialog {
        this.NEGATIVE = negText
        negativeAction = lambda
        return this
    }

    open fun build(): QuickCircleDialog {
        return this
    }

    fun show(): DialogFragment {
        return mDialog.show(mFragmentActivity.supportFragmentManager)
    }

}