package th.ac.up.melondev.new_farm_management.ui.main.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import th.ac.up.melondev.melonpoke.ui.main.base.BaseViewHolder
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.model.*
import th.ac.up.melondev.new_farm_management.ui.main.viewholder.MainCardEventViewHolder
import th.ac.up.melondev.new_farm_management.ui.main.viewholder.MainCardChickenViewHolder
import th.ac.up.melondev.new_farm_management.ui.main.viewholder.MainCardLoadingViewHolder
import th.ac.up.melondev.new_farm_management.ui.main.viewholder.MainCardTitleViewHolder
import th.ac.up.melondev.new_farm_management.until.MainCardType
import th.ac.up.melondev.new_farm_management.until.getViewFromLayoutInflater
import java.util.ArrayList

class MainCardAdapter : RecyclerView.Adapter<BaseViewHolder<BaseProgramCard>>() {

    private var data: ArrayList<BaseProgramCard>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseProgramCard> {
        return when (viewType) {
            MainCardType.TYPE_TITLE -> MainCardTitleViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.title_item
                )
            )
            MainCardType.TYPE_CHICKEN -> MainCardChickenViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.information_card_item
                )
            )
            MainCardType.TYPE_EVENT -> MainCardEventViewHolder(
                parent.getViewFromLayoutInflater(
                    R.layout.card_item
                )
            )
            else -> {
                MainCardLoadingViewHolder(
                    parent.getViewFromLayoutInflater(
                        R.layout.loading_item
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: BaseViewHolder<BaseProgramCard>, position: Int) {
        data?.let {
            when (holder) {
                is MainCardChickenViewHolder -> holder.bind(it[position] as ChickenProgram)
                is MainCardEventViewHolder -> holder.bind(it[position] as ChickenEvent)
                is MainCardTitleViewHolder -> holder.bind(it[position] as TitleModel)
                is MainCardLoadingViewHolder -> holder.bind(it[position] as LoadingItem)

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data?.get(position)) {
            is TitleModel -> MainCardType.TYPE_TITLE
            is ChickenProgram -> MainCardType.TYPE_CHICKEN
            is ChickenEvent -> MainCardType.TYPE_EVENT
            else -> {
                MainCardType.TYPE_LOADING
            }
        }
    }

    fun update(newData: ArrayList<BaseProgramCard>?) {
        data?.clear()
        addData(newData)
    }

    private fun addData(newData: ArrayList<BaseProgramCard>?) {
        newData?.let { new ->
            data?.let {
                data?.addAll(new)
            } ?: run {
                data = newData
            }
            this.notifyDataSetChanged()
        }
    }

}