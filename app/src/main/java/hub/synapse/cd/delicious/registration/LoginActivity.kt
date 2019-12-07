package hub.synapse.cd.delicious.registration


import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hub.synapse.cd.delicious.R
import hub.synapse.cd.delicious.views.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val progDialog = ProgressDialog(this@LoginActivity)

        tv_nothaveyetanaccount_login.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {

            progDialog.setTitle("Connexion")
            progDialog.setMessage("Patientez svp...")
            progDialog.show()
            //call the method
            performeLogin()
        }

    }

    private fun performeLogin(){
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        //if mail or password is empty
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completer l'email et le mot de passe s'il vous plait !", Toast.LENGTH_LONG).show()
            return
        }
        //Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                //kill the login activity in the memory
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,  "Impossible de vous logger: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}