package com.example.tts_pm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tts_pm.databinding.ActivityDetailAccountBinding
import java.util.Locale

class DetailAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // Get data from intent
        val bundle = intent.getBundleExtra("EXTRA_BUNDLE")
        if (bundle != null) {
            val name = bundle.getString("EXTRA_NAME")
            val nim = bundle.getString("EXTRA_NIM")
            val ipk = bundle.getDouble("EXTRA_IPK", 0.0)

            // Using string resources with formatting
            binding.tvDetailName.text = getString(R.string.label_name_value, name ?: "-")
            binding.tvDetailNim.text = getString(R.string.label_nim_value, nim ?: "-")
            binding.tvDetailIpk.text = getString(R.string.label_ipk_value, String.format(Locale.getDefault(), "%.2f", ipk))
        }
    }
}