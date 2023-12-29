package com.example.suitmediatest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.MainDialogBinding

class MainDialog(private val isPalindrome: Boolean) : DialogFragment() {

    private lateinit var binding: MainDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = MainDialogBinding.inflate(inflater, null, false)

        binding.apply {
            if (isPalindrome) {
                tvDialog.text = getString(R.string.is_palindrome)
            } else {
                tvDialog.text = getString(R.string.isnt_palindrome)
            }

            btnOK.setOnClickListener {
                dismiss()
            }
        }

        return createDialog(binding.root)
    }

    private fun createDialog(view: View): Dialog {
        return activity?.let {
            val dialog = Dialog(it)
            dialog.setContentView(view)
            return dialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
