package th.ac.up.melondev.new_farm_management.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_notification.*

import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.model.*
import th.ac.up.melondev.new_farm_management.data.viewmodel.MainViewModel
import th.ac.up.melondev.new_farm_management.ui.main.adapter.MainCardAdapter
import th.ac.up.melondev.new_farm_management.until.ProgramCardUtil

/**
 * A simple [Fragment] subclass.
 */
class NotificationFragment : Fragment() {

    private lateinit var cardAdapter: MainCardAdapter
    private var programCardUnit: ProgramCardUtil? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let {
            programCardUnit = ProgramCardUtil(it)
        }
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialRecyclerView()
        loadData()
    }

    private fun loadData() {
        val viewModel = MainViewModel()
        viewModel.todoLiveData.observe(viewLifecycleOwner, Observer { data ->
            val list: ArrayList<BaseProgramCard> = ArrayList()

            if(data.isNotEmpty()){
                val chicken: Chicken = data.first().let {
                    (it as ChickenEventPack).chicken
                }
                data.map {

                    list.addAll(((it as ChickenEventPack).event).map { ChickenEvent(chicken,it) })
                }

                ProgramCardUtil.sorting(list).let { sortData ->
                    ProgramCardUtil.grouping(sortData).let { groupData ->
                        cardAdapter.update(programCardUnit?.insertTitleHeader(groupData))
                    }
                }
            }



            /*ProgramCardUtil.sorting(list).let { sortData ->
                ProgramCardUtil.grouping(sortData).let { groupData ->
                    cardAdapter.update(programCardUnit?.insertTitleHeader(groupData))
                }
            }
             */
        })
        viewModel.loadTodoData()
    }

    private fun initialRecyclerView() {
        cardAdapter = MainCardAdapter()

        main_notification_recyclerView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.isNestedScrollingEnabled = false
            this.onFlingListener = null
            this.adapter = cardAdapter
        }
    }


}
