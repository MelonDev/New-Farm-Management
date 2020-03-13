package th.ac.up.melondev.new_farm_management.ui.main.viewholder

import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.card_item.view.*
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.new_farm_management.data.model.BaseProgramCard
import th.ac.up.melondev.new_farm_management.data.model.ChickenEvent
import th.ac.up.melondev.new_farm_management.data.model.TodoStyle
import th.ac.up.melondev.new_farm_management.until.ConvertCard
import th.ac.up.melondev.new_farm_management.until.StyleUtil

class MainCardEventViewHolder(itemView: View) : BaseViewHolder<BaseProgramCard>(itemView) {

    private val titleTextView = itemView.card_item_main_card_title
    private val messageTextView = itemView.card_item_main_card_message_text_view
    private val descriptionTextView = itemView.card_item_main_card_description

    private val iconLayoutCardView = itemView.card_item_main_card_image_layout
    private val iconImageView = itemView.card_item_main_card_image_view

    fun bind(event : ChickenEvent){
        titleTextView.text = ConvertCard(itemView.context).getObjectiveString(event.event.title)
        messageTextView.text = event.event.message

        if(event.chicken.cardName.isEmpty()){
            descriptionTextView.text = "ชื่อรายการ"
        } else {
            descriptionTextView.text = event.chicken.cardName
        }

        val style = StyleUtil.getStyleFromObjective(itemView.context,event.event.title)

        titleTextView.setTextColor(style.color)
        iconLayoutCardView.setCardBackgroundColor(style.color)

        Glide.with(itemView.context)
            .load(style.icon)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iconImageView)
    }

}