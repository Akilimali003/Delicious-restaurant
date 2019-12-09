package hub.synapse.cd.delicious.registration

import android.animation.Animator
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.TextInputEditText
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hub.synapse.cd.delicious.R
import hub.synapse.cd.delicious.views.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_b.*
import kotlinx.android.synthetic.main.activity_main_b.tv_nothaveyetanaccount_login
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Michelo on 2019-12-09 at 14:33.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class MainActivityb : AppCompatActivity() {

    lateinit var emailEditText : TextInputEditText
    lateinit var passwordEditText : TextInputEditText
    lateinit var loginButton : Button
    lateinit var progDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main_b)
        object : CountDownTimer(5000, 1000) {
            override fun onFinish() {
                bookITextView.visibility = View.GONE
                loadingProgressBar.visibility = View.GONE
                rootView.setBackgroundColor(ContextCompat.getColor(this@MainActivityb, R.color.colorSplashText))
                bookIconImageView.setImageResource(R.drawable.background_color_book)
                startAnimation()
            }

            override fun onTick(p0: Long) {}
        }.start()


        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        progDialog = ProgressDialog(this@MainActivityb)

        tv_nothaveyetanaccount_login.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            //call the method
            loginFunction()
        }

    }

    private fun startAnimation() {
        bookIconImageView.animate().apply {
            x(50f)
            y(100f)
            duration = 1000
        }.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                afterAnimationView.visibility = VISIBLE
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }
        })
    }

    private fun performeLogin(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        //Firebase Authentication sign in with email and password
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    progDialog.dismiss()
                    passwordEditText.setText("")
                    return@addOnCompleteListener
                } else {
                    emailEditText.setText("")
                    passwordEditText.setText("")
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
        val strEmail = emailEditText.text.toString().trim()
        val strPassword = passwordEditText.text.toString().trim()

        if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)){
            emailEditText.error = "Champs vide"
            passwordEditText.error = "Champs vide"

        } else if (!emailValidationFunction(strEmail)){
            emailEditText.error = "Adresse mail invalide"

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