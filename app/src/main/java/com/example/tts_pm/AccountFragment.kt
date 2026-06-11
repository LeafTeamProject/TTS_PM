package com.example.tts_pm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tts_pm.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveProfile.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val name = binding.etName.text.toString().trim()
        val nim = binding.etNim.text.toString().trim()
        val ipkString = binding.etIpk.text.toString().trim()

        var isValid = true

        if (name.isEmpty()) {
            binding.etName.error = getString(R.string.error_empty_name)
            isValid = false
        }

        if (nim.isEmpty()) {
            binding.etNim.error = getString(R.string.error_empty_nim)
            isValid = false
        }

        if (ipkString.isEmpty()) {
            binding.etIpk.error = getString(R.string.error_empty_ipk)
            isValid = false
        } else {
            val ipk = ipkString.toDoubleOrNull()
            if (ipk == null || ipk < 0.0 || ipk > 4.0) {
                binding.etIpk.error = getString(R.string.error_invalid_ipk)
                isValid = false
            }
        }

        if (isValid) {
            val intent = Intent(requireContext(), DetailAccountActivity::class.java)
            val bundle = Bundle().apply {
                putString("EXTRA_NAME", name)
                putString("EXTRA_NIM", nim)
                putDouble("EXTRA_IPK", ipkString.toDouble())
            }
            intent.putExtra("EXTRA_BUNDLE", bundle)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}