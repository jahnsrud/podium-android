package no.jahnsrud.podium.Navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import no.jahnsrud.podium.MainActivity
import no.jahnsrud.podium.R

class TabManager(private val mainActivity: MainActivity) {

    private val startDestinations = mapOf(
            R.id.tab_library to R.id.libraryFragment,
            R.id.tab_search to R.id.searchFragment,
            R.id.tab_settings to R.id.settingsFragment
    )
    private var currentTabId: Int = R.id.tab_library
    var currentController: NavController? = null
    private var tabHistory = TabHistory().apply { push(R.id.tab_library) }

    val libraryTabController: NavController by lazy {
        mainActivity.findNavController(R.id.libraryTab).apply {
            graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = startDestinations.getValue(R.id.tab_library)
            }
        }
    }
    private val searchTabController: NavController by lazy {
        mainActivity.findNavController(R.id.searchTab).apply {
            graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = startDestinations.getValue(R.id.tab_search)
            }
        }
    }
    private val settingsTabController: NavController by lazy {
        mainActivity.findNavController(R.id.settingsTab).apply {
            graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = startDestinations.getValue(R.id.tab_settings)
            }
        }
    }

    private val libraryTabContainer: View by lazy { mainActivity.libraryTabContainer }
    private val searchTabContainer: View by lazy { mainActivity.searchTabContainer }
    private val settingsTabContainer: View by lazy { mainActivity.settingsTabContainer }

    fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(KEY_TAB_HISTORY, tabHistory)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            tabHistory = it.getSerializable(KEY_TAB_HISTORY) as TabHistory

            switchTab(mainActivity.tabBar.selectedItemId, false)
        }
    }

    fun switchTab(tabId: Int, addToHistory: Boolean = true) {
        currentTabId = tabId

        when (tabId) {
            R.id.tab_library -> {
                currentController = libraryTabController
                invisibleTabContainerExcept(libraryTabContainer)
            }
            R.id.tab_search -> {
                currentController = searchTabController
                invisibleTabContainerExcept(searchTabContainer)
            }
            R.id.tab_settings -> {
                currentController = settingsTabController
                invisibleTabContainerExcept(settingsTabContainer)
            }
        }
        if (addToHistory) {
            tabHistory.push(tabId)
        }
    }

    fun supportNavigateUpTo(upIntent: Intent) {
        currentController?.navigateUp()
    }

    fun onBackPressed() {
        currentController?.let {
            if (it.currentDestination == null || it.currentDestination?.id == startDestinations.getValue(currentTabId)) {
                if (tabHistory.size > 1) {
                    val tabId = tabHistory.popPrevious()
                    switchTab(tabId, false)
                    mainActivity.tabBar.menu.findItem(tabId)?.isChecked = true
                } else {
                    mainActivity.finish()
                }
            }
            it.popBackStack()
        } ?: run {
            mainActivity.finish()
        }
    }



    private fun invisibleTabContainerExcept(container: View) {
        libraryTabContainer.isInvisible = true
        searchTabContainer.isInvisible = true
        settingsTabContainer.isInvisible = true

        container.isInvisible = false
    }

    companion object {
        private const val KEY_TAB_HISTORY = "key_tab_history"
    }
}