package fr.iim.firebaseauth.ui.search

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.iim.firebaseauth.HomeActivity
import fr.iim.firebaseauth.R
import fr.iim.firebaseauth.ui.form.FormFragment

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var listener: SearchFragmentInterface

    interface  SearchFragmentInterface {
        fun OnSearchClickListener(city: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.search_city).setOnClickListener{
            Log.d("SearchFragment", "Button Clicked")
            listener.OnSearchClickListener(
                view.findViewById<EditText>(R.id.city_search).text.toString()
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is SearchFragmentInterface)
            listener = context
        else{
            throw RuntimeException("$context must implement")
        }


    }

}