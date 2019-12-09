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
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var editTextEmailLogin : TextInputEditText
    lateinit var editTextPasswordLogin : TextInputEditText
    lateinit var loginButton : Button
    lateinit var progDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmailLogin = findViewById(R.id.email_login)
        editTextPasswordLogin = findViewById(R.id.password_login)
        loginButton = findViewById(R.id.login_button)

        progDialog = ProgressDialog(this@LoginActivity)

        tv_nothaveyetanaccount_login.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            //call the method
            loginFunction()
        }

    }

    private fun performeLogin(){
        val email = editTextEmailLogin.text.toString()
        val password = editTextPasswordLogin.text.toString()

        //Firebase Authentication sign in with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    progDialog.dismiss()
                    editTextPasswordLogin.setText("")
                    return@addOnCompleteListener
                } else {
                    editTextEmailLogin.setText("")
                    editTextPasswordLogin.setText("")
                    progDialog.dismiss()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                //kill the login activity in the memory
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,  "Impossible de vous connecter: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun loginFunction(){
        val strEmail = editTextEmailLogin.text.toString().trim()
        val strPassword = editTextPasswordLogin.text.toString().trim()

        if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)){
            editTextEmailLogin.error = "Champs vide"
            editTextPasswordLogin.error = "Champs vide"

        } else if (!emailValidationFunction(strEmail)){
            editTextEmailLogin.error = "Adresse mail invalide"

        } else {
            //call the method
            performeLogin()

            progDialog.setTitle("Connexion")
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