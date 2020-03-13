package th.ac.up.melondev.new_farm_management.data.viewmodel

import android.annotation.SuppressLint

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import th.ac.up.melondev.new_farm_management.data.listener.FirebaseEventListener
import th.ac.up.melondev.new_farm_management.data.model.*
import th.ac.up.melondev.new_farm_management.data.repository.MainRepository
import th.ac.up.melondev.new_farm_management.until.ProgramCardUtil

import kotlin.collections.ArrayList


class MainViewModel : ViewModel() {

    private val repository: MainRepository = MainRepository()

    val programLiveData = MutableLiveData<ArrayList<BaseProgramCard>>()
    val todoLiveData = MutableLiveData<ArrayList<BaseProgramCard>>()

    fun loadProgramData(isActive :Boolean) {
        repository.getProgramList(object : FirebaseEventListener {
            override fun onDataChanged(snapshot: DataSnapshot) {
                val list: ArrayList<BaseProgramCard> = if(isActive) getChickenProgramCardList(snapshot) else getInactiveChickenProgramCardList(snapshot)
                list.reverse()
                programLiveData.postValue(list)
            }

            @SuppressLint("RestrictedApi")
            override fun onError(error: Int) {
                Log.e("ERROR", DatabaseError.fromCode(error).message)
            }

            override fun onNotFound() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    fun loadTodoData() {
        repository.getProgramList(object : FirebaseEventListener {
            override fun onDataChanged(snapshot: DataSnapshot) {
                val list: ArrayList<BaseProgramCard> = getChickenEventPackCardList(snapshot)
                list.reverse()
                todoLiveData.postValue(list)
            }

            @SuppressLint("RestrictedApi")
            override fun onError(error: Int) {
                Log.e("ERROR", DatabaseError.fromCode(error).message)
            }

            override fun onNotFound() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    

    private fun getChickenProgramCardList(snapshot: DataSnapshot): ArrayList<BaseProgramCard> {
        return snapshot.children.map {
            it.child("รายละเอียด").getValue(Chicken::class.java)?.let { chicken ->
                if (chicken.status.contentEquals("ACTIVE")) {
                    ChickenProgram(chicken = chicken, todoCount = getEventList(it, chicken).size)
            }else {
                    null
                }
            }
        }.filterNotNull().toCollection(ArrayList())
    }

    private fun getInactiveChickenProgramCardList(snapshot: DataSnapshot): ArrayList<BaseProgramCard> {
        return snapshot.children.map {
            it.child("รายละเอียด").getValue(Chicken::class.java)?.let { chicken ->
                if (chicken.status.contentEquals("INACTIVE")) {
                    ChickenProgram(chicken = chicken, todoCount = getEventList(it, chicken).size)
                }else {
                    null
                }
            }
        }.filterNotNull().toCollection(ArrayList())
    }

    private fun getChickenEventPackCardList(snapshot: DataSnapshot): ArrayList<BaseProgramCard> {
        return snapshot.children.map {
            it.child("รายละเอียด").getValue(Chicken::class.java)?.let { chicken ->
                if (chicken.status.contentEquals("ACTIVE")) {
                    ChickenEventPack(chicken = chicken, event = getEventList(it, chicken))

                }else {
                    null
                }
            }
        }.filterNotNull().toCollection(ArrayList())
    }

    private fun getEventList(dataSnapshot: DataSnapshot, chicken: Chicken): ArrayList<Event> {

        return dataSnapshot.child("รายการที่ต้องทำ").children.map {
            it?.let { snap ->
                snap.getValue(Event::class.java)?.let { event ->
                    if (ProgramCardUtil.isEventActive(chicken, event)) {
                        event
                    } else {
                        null
                    }
                }
            }
        }.filterNotNull().toCollection(ArrayList())
    }

}