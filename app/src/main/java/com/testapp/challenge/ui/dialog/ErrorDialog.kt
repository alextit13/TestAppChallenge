package com.testapp.challenge.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.testapp.challenge.databinding.DialogErrorBinding

/**
 * @author aliakseicherniakovich
 */
class ErrorDialog : DialogFragment() {

    private lateinit var binding: DialogErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogErrorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvMessage.text = arguments?.getString(MESSAGE_ARG_KEY)
        binding.tvClose.setOnClickListener { dismiss() }
    }

    companion object {
        const val DIALOG_TAG = "DIALOG_TAG"
        fun getInstance(message: String): ErrorDialog {
            return ErrorDialog().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE_ARG_KEY, message)
                }
            }
        }

        private const val MESSAGE_ARG_KEY = "MESSAGE_ARG_KEY"
    }
}
