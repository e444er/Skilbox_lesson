package com.devv.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.devv.fragments.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.button.setOnClickListener {
            val login = binding.editText.text.toString()
            val isValid = Patterns.EMAIL_ADDRESS.matcher(login).matches()
            if (!isValid) {
                Toast.makeText(context, "Email error", Toast.LENGTH_SHORT).show()
            } else {
                fragmentManager?.beginTransaction()?.replace(R.id.container, MainFragment())?.commit()
//                childFragmentManager.beginTransaction()
//                    .add(R.id.container, MainFragment())
//                    .commit()

            }
        }
    }


}