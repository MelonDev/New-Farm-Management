package th.ac.up.melondev.new_farm_management.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.threetenabp.AndroidThreeTen

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import th.ac.up.melondev.new_farm_management.R
import th.ac.up.melondev.new_farm_management.data.FirebaseAuthenticationClient
import th.ac.up.melondev.new_farm_management.data.FirebaseDatabaseCenter
import th.ac.up.melondev.new_farm_management.data.listener.GoogleSignInEventListener
import th.ac.up.melondev.new_farm_management.ui.main.adapter.MainViewPagerAdapter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var firebaseDatabase : FirebaseDatabaseCenter? = null
    private var firebaseAuthenticationClient :FirebaseAuthenticationClient? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidThreeTen.init(this)

        setupNavigationDrawer()
        setUpViewPagerAndTab()

        demo()

        main_activity_fab.setOnClickListener {
            demo()
        }
        main_activity_fab.setOnLongClickListener{
            firebaseAuthenticationClient?.signout()
            true
        }


    }

    private fun demo(){

        this.firebaseDatabase = FirebaseDatabaseCenter.createService()
        this.firebaseAuthenticationClient = FirebaseAuthenticationClient.createService()

        firebaseDatabase?.isAccessDatabase()?.let {
            if(it){

                demoLoad()
            }else {
                firebaseAuthenticationClient?.signinByGoogle(this)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        firebaseAuthenticationClient?.activateAccountFromGoogle(requestCode,data,object :GoogleSignInEventListener{
            override fun onSignInSuccess() {
                demoLoad()
            }

            override fun onError() {
                Log.e("SIGNIN","FAIL")
            }
        })

    }

    private fun demoLoad(){
        /*firebaseDatabase?.loadEvent(listener = object : FirebaseEventListener {
            override fun onDataChanged(snapshot: DataSnapshot) {
                Log.e("onDataChanged", "PASS")
            }

            @SuppressLint("RestrictedApi")
            override fun onError(error: Int) {
                AlertsDialog.showAlertDialog(this@MainActivity,R.string.you_can_not_access)
                Log.e("onError", DatabaseError.fromCode(error).message)
            }

            override fun onNotFound() {
                AlertsDialog.showAlertDialog(this@MainActivity, R.string.not_found_data)
            }
        })

         */
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setUpViewPagerAndTab() {
        main_activity_viewpager.adapter = MainViewPagerAdapter(this)
        main_activity_viewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> main_activity_fab.show()
                    else -> main_activity_fab.hide()
                }
            }
        })
        TabLayoutMediator(program_main_activity_tabbar, main_activity_viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_program_text)
                1 -> getString(R.string.tab_notification_text)
                else -> getString(R.string.tab_history_text)
            }
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
