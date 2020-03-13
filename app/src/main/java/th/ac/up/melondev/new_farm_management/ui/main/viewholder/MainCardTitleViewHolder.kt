package th.ac.up.melondev.new_farm_management.ui.main.viewholder

import android.view.View
import kotlinx.android.synthetic.main.title_item.view.*
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.new_farm_management.data.model.BaseProgramCard
import th.ac.up.melondev.new_farm_management.data.model.TitleModel

class MainCardTitleViewHolder(itemView: View) : BaseViewHolder<BaseProgramCard>(itemView) {

    val titleTextView = itemView.title_item_title_text

    fun bind(titleModel: TitleModel) {
        titleTextView.text = titleModel.string
    }

}