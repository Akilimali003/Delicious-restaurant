package hub.synapse.cd.delicious.registration

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tv_already_register_register.setOnClickListener {
            //kill the activity
            finish()
        }

        register_button.setOnClickListener {
            //call the method
            performeRegister()
        }
    }

    //register function
    private fun performeRegister(){
        val email = register_email.text.toString()
        val password = register_password.text.toString()

        //if mail or password is empty
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completer l'email et le mot de passe s'il vous plait !", Toast.LENGTH_LONG).show()
            return
        }
        //Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                } else {
//                    Toast.makeText(this, "Bienvenu", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener{
                Toast.makeText(this,  "Impossible de créer un compte: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
