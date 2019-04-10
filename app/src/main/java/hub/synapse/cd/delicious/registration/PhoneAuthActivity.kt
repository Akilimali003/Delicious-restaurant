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
import kotlinx.android.synthetic.main.activity_phone_auth.*
import java.util.concurrent.TimeUnit

class PhoneAuthActivity : AppCompatActivity() {

    lateinit var resendToken : PhoneAuthProvider.ForceResendingToken
    lateinit var mAuth : FirebaseAuth
    lateinit var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationId = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        tv_resend_sms_phone_auth_verification.isEnabled = false

        mAuth = FirebaseAuth.getInstance()

        btn_verification_code_authentication.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            verify()
        }

        btn_auth_authentication.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            authenticate()
        }

        tv_resend_sms_phone_auth_verification.setOnClickListener {
            resendCode()
        }
    }

    private fun verificationCallBacks() {
        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progressBar?.visibility = View.INVISIBLE
                signIn(credential)
                tv_resend_sms_phone_auth_verification.isEnabled = false
            }

            override fun onVerificationFailed(p0: FirebaseException?) {

            }

            override fun onCodeSent(verification: String?, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verification, token)
                verificationId = verification.toString()
                resendToken = token
                progressBar.visibility = View.INVISIBLE
                tv_resend_sms_phone_auth_verification.isEnabled = true
            }

        }
    }

    private fun authenticate() {
        val verificationNum = edt_virification_authentication.text.toString()
        if (!verificationNum.isEmpty()) {
            val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, verificationNum)
            signIn(credential)
        } else {
            Toast.makeText(this, "Completer les champs vide s'il vous plait !!!", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun verify() {
        verificationCallBacks()
        val phoneNum = edt_phone_number_authentication.text.toString()

        if (phoneNum.isEmpty()) {
            toast("Completer les champs vide s'il vous plait !!!")
            progressBar.visibility = View.INVISIBLE
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBacks
            )
        }
    }

    private fun resendCode() {
        verificationCallBacks()
        val phoneNum = edt_phone_number_authentication.text.toString()

        if (phoneNum.isEmpty()) {
            toast("Completer les champs vide s'il vous plait !!!")
            progressBar.visibility = View.INVISIBLE
        } else {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBacks,
                resendToken
            )
        }
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
}