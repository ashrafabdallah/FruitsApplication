package com.example.fruits.presention.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fruits.R
import com.example.fruits.databinding.ActivityMainBinding
import com.example.fruits.presention.adapter.FruitsHomeAdapter
import com.example.fruits.presention.viewmodel.HomeViewModel
import com.example.fruits.presention.viewmodel.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var fruitsHomeAdapter: FruitsHomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // navController = findNavController(R.id.main_nav_host) //Initialising navController

        val navHost =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHost.navController

        homeViewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)


        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment2, R.id.marketplaceFragment,
            R.id.ordersFragment, R.id.chatFragment
        ) //Pass the ids of fragments from nav_graph which you d'ont want to show back button in toolbar
            .setOpenableLayout(mainBinding.mainDrawerLayout) //Pass the drawer layout id from activity xml
            .build()

        setSupportActionBar(mainBinding.mainToolbar) //Set toolbar

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) //Setup toolbar with back button and drawer icon according to appBarConfiguration
     //   mainBinding.mainBottomNavigationView.setupWithNavController(navController)
        visibilityNavElements(navController) //If you want to hide drawer or bottom navigation configure that in this function
    }

    private fun visibilityNavElements(navController: NavController) {

        //Listen for the change in fragment (navigation) and hide or show drawer or bottom navigation accordingly if required
        //Modify this according to your need
        //If you want you can implement logic to deselect(styling) the bottom navigation menu item when drawer item selected using listener

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.profileFragment -> hideBothNavigation()
                R.id.settingFragment -> hideBottomNavigation()
                R.id.splashFragment -> {
                    mainBinding.mainBottomNavigationView.visibility = View.GONE
                    mainBinding.mainToolbar.visibility = View.GONE
                }
                else -> showBothNavigation()
            }
        }

    }

    private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        mainBinding.mainBottomNavigationView.visibility = View.GONE
        mainBinding.mainNavigationView.visibility = View.GONE
        mainBinding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun hideBottomNavigation() { //Hide bottom navigation
        mainBinding.mainBottomNavigationView.visibility = View.GONE
        mainBinding.mainNavigationView.visibility = View.VISIBLE
        mainBinding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer

        mainBinding.mainNavigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    private fun showBothNavigation() {
        mainBinding.mainToolbar.visibility = View.VISIBLE
        mainBinding.mainBottomNavigationView.visibility = View.VISIBLE
        mainBinding.mainNavigationView.visibility = View.VISIBLE
        mainBinding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
         setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        mainBinding.mainNavigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
        mainBinding.mainBottomNavigationView.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    fun exitApp() { //To exit the application call this function from fragment
        this.finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
       // navController.navigate(R.id.homeFragment2)

//        if(NavigationUI.navigateUp(navController, appBarConfiguration)){
//            navController.navigate(R.id.homeFragment2)
//
//        }
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val navigationController = navHost.navController
        //  val navigationController = binding.navigation.findNavController()
        if (navigationController.currentDestination?.id == R.id.homeFragment2) {
            finish()
        } else if (navigationController.currentDestination?.id == R.id.marketplaceFragment) {
            navigationController.navigate(R.id.homeFragment2)

        } else if (navigationController.currentDestination?.id == R.id.ordersFragment) {
            navigationController.navigate(R.id.homeFragment2)
        } else if (navigationController.currentDestination?.id == R.id.chatFragment){
            navigationController.navigate(R.id.homeFragment2)
        }else if (navigationController.currentDestination?.id == R.id.profileFragment){
            navigationController.navigate(R.id.homeFragment2)
        }
        else if (navigationController.currentDestination?.id == R.id.settingFragment){
            navigationController.navigate(R.id.homeFragment2)
        }

        else {
            super.onBackPressed()
        }




        when { //If drawer layout is open close that on back pressed
            mainBinding.mainDrawerLayout.isDrawerOpen(GravityCompat.START) -> {

                mainBinding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Notifcation -> {
                Toast.makeText(applicationContext, "Notification", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}
