package com.rizaki.challengech6Binar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rizaki.challengech6Binar.database.local.UserRoomDatabase
import com.rizaki.challengech6Binar.helper.UserPreferences
import com.rizaki.challengech6Binar.R
import com.rizaki.challengech6Binar.ViewModel.LoginViewModel
import com.rizaki.challengech6Binar.databinding.FragmentLoginBinding
import com.rizaki.challengech6Binar.helper.UserDataStoreManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLoginStatus().observe(viewLifecycleOwner) { isLogin ->
            if (isLogin) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }
            tvRegister.setOnClickListener {
                register()
            }
        }
    }

    private fun login() {
        binding.apply {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            viewModel.login(email, password)
            viewModel.checkUser.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    viewModel.saveUserDataStore(user.id, true)
                }
            }
            viewModel.message.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun register() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}