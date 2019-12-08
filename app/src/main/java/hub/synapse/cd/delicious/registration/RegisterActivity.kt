package hub.synapse.cd.delicious.registration

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hub.synapse.cd.delicious.R
import hub.synapse.cd.delicious.views.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    lateinit var editTextEmailRegister: TextInputEditText
    lateinit var editTextPasswordRegister: TextInputEditText
    lateinit var registerButton: Button
    lateinit var progDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextEmailRegister = findViewById(R.id.register_email)
        editTextPasswordRegister = findViewById(R.id.register_password)
        registerButton = findViewById(R.id.register_button)

        progDialog = ProgressDialog(this@RegisterActivity)

        tv_already_register_register.setOnClickListener {
            //kill the activity
            finish()
        }

        registerButton.setOnClickListener {
            //call the method
            registerFunction()
        }
    }

    private fun performeRegister() {
        val email = editTextEmailRegister.text.toString()
        val password = editTextPasswordRegister.text.toString()

        //Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    progDialog.dismiss()
                    editTextPasswordRegister.setText("")
                    return@addOnCompleteListener
                } else {
                    editTextEmailRegister.setText("")
                    editTextPasswordRegister.setText("")
                    progDialog.dismiss()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Impossible de créer un compte: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun registerFunction(){
        val strEmail = editTextEmailRegister.text.toString().trim()
        val strPassword = editTextPasswordRegister.text.toString().trim()

        if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)){
            editTextEmailRegister.error = "Champs vide"
            editTextPasswordRegister.error = "Champs vide"

        } else if (!emailValidationFunction(strEmail)){
            editTextEmailRegister.error = "Adresse mail invalide"

        } else {
            //call the method
            performeRegister()

            progDialog.setTitle("Création du compte")
            progDialog.setMessage("Patientez svp...")
            progDialog.show()

        }
    }

    private fun emailValidationFunction(strEmail : String) : Boolean{
        val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern : Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher : Matcher = pattern.matcher(strEmail)
        return matcher.matches()
    }
}