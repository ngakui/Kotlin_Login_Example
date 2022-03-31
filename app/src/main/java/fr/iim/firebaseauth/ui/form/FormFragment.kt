package fr.iim.firebaseauth.ui.form

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import fr.iim.firebaseauth.R
import java.util.regex.Pattern.compile


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment() {

    private lateinit var listener: FormFragmentInterface
    private var mail: Boolean = false
    private var password: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FormFragment", "FormCreated")

        val passwdText:EditText = view.findViewById(R.id.password)

        val editText: EditText = view.findViewById(R.id.email)

        val button:Button = view.findViewById(R.id.login_button)

        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Does nothing intentionally
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Does nothing intentionally
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
                if (!isValid) {
                    editText.error = "Invalid Email"
                    mail = false
                    button.isEnabled = false
                } else {
                    editText.error === null
                    mail = true
                    if (mail && password)
                        button.isEnabled = true
                }
            }
        })

        // add text changed listener for password text
        passwdText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                s?.apply {
                    // check user input a valid formatted password
                    if (isValidPassword() && toString().length>=8) {
                        passwdText.error === null
                        password = true
                        if (mail && password)
                            button.isEnabled = true
                    }else{
                        // show error on input invalid password
                        passwdText.error = "Password minimum length 8 " +
                                            "\nMust contain 1 uppercase," +
                                            "\n1 special character," +
                                            "\n1 number"
                        password = false
                        button.isEnabled = false
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
        val checkBox: CheckBox = view.findViewById(R.id.form_check)
        view.findViewById<Button>(R.id.login_button).setOnClickListener{
            Log.d("FormFragment", "Button Clicked")
            if (checkBox.isChecked)
                listener.OnHomeClickListener(
                    view.findViewById<TextView>(R.id.email).text.toString(),
                    view.findViewById<TextView>(R.id.password).text.toString()
                )
            else
                Toast.makeText(context, "You must Accept terms and Conditions", Toast.LENGTH_LONG).show()
        }

    }

    // extension function to validate password rules/patterns
    fun CharSequence.isValidPassword(): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        val pattern = compile(passwordPattern)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FormFragmentInterface)
            listener = context
        else{
            throw RuntimeException("$context must implement")
        }


    }

    interface  FormFragmentInterface {
        fun OnHomeClickListener(firstName: String, password: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment FormFragment.
         */
        @JvmStatic
        fun newInstance() = FormFragment()

    }
}