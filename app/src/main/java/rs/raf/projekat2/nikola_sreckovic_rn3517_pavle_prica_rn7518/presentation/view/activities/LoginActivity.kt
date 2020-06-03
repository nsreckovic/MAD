package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.contract.UserContract
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        login_LogIn_Btn.setOnClickListener {
            val username = login_Username_Et.text.toString()
            val pin_str = login_Pin_Et.text.toString()

            if (username.isEmpty() || pin_str.isEmpty()) {
                Toast.makeText(this, R.string.input_text_error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pin_str)

            if (pin_str.length != 4) {
                Toast.makeText(this, R.string.login_input_pin_length_error, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (userViewModel.login(username, pin)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, R.string.login_input_error, Toast.LENGTH_SHORT).show()
            }


        }
    }

}
