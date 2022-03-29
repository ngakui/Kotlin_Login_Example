package fr.iim.firebaseauth.ui.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import fr.iim.firebaseauth.HomeActivity
import fr.iim.firebaseauth.R

class WelcomeFragment : Fragment() {

    private var firstName: String? = null

    companion object {
        private const val ARG_FIRST_NAME = "firstName"
        fun newInstance(firstName: String) = WelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_FIRST_NAME, firstName)
            }
        }
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstName = it.getString(ARG_FIRST_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.home_title)
        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        view.findViewById<TextView>(R.id.hello_text).text = "Hello $firstName"
    }

}