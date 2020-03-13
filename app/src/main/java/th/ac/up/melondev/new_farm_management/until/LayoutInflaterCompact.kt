package th.ac.up.melondev.new_farm_management.until

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.getViewFromLayoutInflater(resource :Int) :View{

    return LayoutInflater.from(this.context).inflate(
        resource,
        this,
        false
    )
}