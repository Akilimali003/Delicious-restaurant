package hub.synapse.cd.delicious.views

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.addresses_fragment_layout.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.profile_fragment_layout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Delicious"

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


        //call the Restaurant fragment once the application is being launched
        val fragment = RestaurantFragment.newInstance()
        replaceToFragment(fragment)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_restaurent_menu -> {
                val fragment = RestaurantFragment.newInstance()
                replaceToFragment(fragment)
            }
            R.id.nav_addresses_menu -> {
                replaceToFragment(AdressesFragment())
            }
            R.id.nav_profile_menu -> {
                replaceToFragment(ProfileFragment())
            }
            R.id.nav_contact_us -> {
                replaceToFragment(ContactUsFragment())
            }
            R.id.nav_about_menu -> {
                replaceToFragment(AboutFragment())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceToFragment(fragTo: Fragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.frameLayout, fragTo)
        fm.commit()
    }
}
