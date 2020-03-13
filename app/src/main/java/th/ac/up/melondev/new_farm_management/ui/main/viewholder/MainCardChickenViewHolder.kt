package th.ac.up.melondev.new_farm_management.ui.main.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.information_card_item.view.*
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.model.BaseProgramCard
import th.ac.up.melondev.new_farm_management.data.model.ChickenProgram
import th.ac.up.melondev.new_farm_management.until.ConvertCard
import th.ac.up.melondev.new_farm_management.until.TimeUntil

class MainCardChickenViewHolder(itemView: View) : BaseViewHolder<BaseProgramCard>(itemView) {

    private val titleTextView = itemView.information_card_item_layout_main_card_title
    private val dateTextView = itemView.information_card_item_layout_main_card_information_date_text
    private val objectiveTextView = itemView.information_card_item_layout_main_card_information_objective_text
    private val ageTextView = itemView.information_card_item_layout_main_card_information_age_text
    private val descriptionTextView = itemView.information_card_item_layout_main_card_description

    private val iconLayoutCardView = itemView.information_card_item_layout_main_card_layout
    private val iconImageView = itemView.information_card_item_layout_main_card_image_view

    fun bind(program: ChickenProgram){
        val chicken = program.chicken
        titleTextView.text = if(chicken.cardName.isNotEmpty()) chicken.cardName else "ชื่อรายการ"

        val dateStr = "${(chicken.dateDay).toInt()} ${ConvertCard(itemView.context).getMonth(chicken.dateMonth)} ${ConvertCard(itemView.context).getYear(chicken.dateYear)}"
        dateTextView.text = dateStr
        objectiveTextView.text = ConvertCard(itemView.context).getObjective(chicken.userObjective)

        ageTextView.text = TimeUntil.getCurrentChickenAgeOnFormat(itemView.context,chicken)

        descriptionTextView.text = "${itemView.context.getString(R.string.to_do_list)} ${program.todoCount} ${itemView.context.getString(R.string.task)}"

        if(chicken.status.contentEquals("INACTIVE")) setStyleNotActive()
    }

    private fun setStyleNotActive(){
        titleTextView.setTextColor(ContextCompat.getColor(itemView.context,R.color.colorInActiveTitle))
        iconLayoutCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorInActiveIconLayout))

        iconImageView.setColorFilter(ContextCompat.getColor(itemView.context, R.color.colorInActiveIconImage), android.graphics.PorterDuff.Mode.MULTIPLY)

        Glide.with(itemView.context)
            .load(R.drawable.ic_checked_icon)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iconImageView)
    }

}