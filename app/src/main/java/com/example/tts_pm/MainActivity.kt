package com.example.tts_pm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tts_pm.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(DashboardFragment())
        }

        (binding.navView as NavigationBarView).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    replaceFragment(DashboardFragment())
                    true
                }
                R.id.navigation_account -> {
                    replaceFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Handles fragment transactions with logic to avoid redundant replacements
     * and updates the top toolbar UI based on the active fragment.
     */
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment != null && currentFragment.javaClass == fragment.javaClass) {
            return
        }

        // Update Toolbar and Navigation UI
        when (fragment) {
            is DashboardFragment, is AccountFragment -> {
                binding.toolbarTitle.text = getString(R.string.title_edudashboard)
                binding.tvLanguage.visibility = android.view.View.VISIBLE
                binding.btnBack.visibility = android.view.View.GONE
                binding.navView.visibility = android.view.View.VISIBLE
                binding.root.findViewById<android.view.View>(R.id.bottom_divider)?.visibility = android.view.View.VISIBLE
                binding.root.findViewById<android.view.View>(R.id.side_divider)?.visibility = android.view.View.VISIBLE
            }
        }

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}