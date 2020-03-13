package th.ac.up.melondev.new_farm_management.data.repository

import th.ac.up.melondev.new_farm_management.data.FirebaseDatabaseCenter
import th.ac.up.melondev.new_farm_management.data.listener.FirebaseEventListener

class MainRepository {
    private val service: FirebaseDatabaseCenter = FirebaseDatabaseCenter.createService()

    fun getProgramList(listener: FirebaseEventListener) = service.loadEvent(path = "/รายการ/ใช้งาน",listener = listener)
}