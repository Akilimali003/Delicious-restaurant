package hub.synapse.cd.delicious.registration

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import hub.synapse.cd.delicious.R
import hub.synapse.cd.delicious.views.MainActivity
import kotlinx.android.synthetic.main.activity_phone_auth2.*
import java.util.concurrent.TimeUnit

class PhoneAuthActivity2 : AppCompatActivity() {

    lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    lateinit var mAuth : FirebaseAuth
    lateinit var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth2)

        val intent = intent
        val receivedPhoneNo : String = intent.getStringExtra("phone")
        tv_verify.text = receivedPhoneNo
        tv_phone_number_to_verify.text = receivedPhoneNo

        mAuth = FirebaseAuth.getInstance()

        verify()

        btn_connexion.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            authenticate()
        }
    }

    /***** Methods *****/

    private fun verificationCallBacks() {
        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progressBar2?.visibility = View.INVISIBLE
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
            }

            override fun onCodeSent(verification: String?, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verification, token)
                verificationId = verification.toString()
                resendToken = token
                progressBar2.visibility = View.INVISIBLE
            }

        }
    }

    private fun authenticate() {
        val verificationCode = edt_verification_code.text.toString()
        if (!verificationCode.isEmpty()) {
            val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
            signIn(credential)
        } else {
            toast("Completer le code de vérification s'il vous plait !!!")
            progressBar2.visibility = View.INVISIBLE
        }
    }

    private fun verify() {
        verificationCallBacks()
        val intent = intent
        val receivedPhoneNo : String = intent.getStringExtra("phone")
        PhoneAuthProvider.getInstance().verifyPhoneNumber(receivedPhoneNo, 60, TimeUnit.SECONDS, this, mCallBacks)
    }


    private fun signIn(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    toast("Logged in successfully !")
                    startActivity(Intent(this, MainActivity::class.java))
                }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
