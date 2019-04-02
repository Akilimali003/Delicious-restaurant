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

    lateinit var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mAuth: FirebaseAuth
    var verificationId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        mAuth = FirebaseAuth.getInstance()

        btn_verification_code_authentication.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            verify()
        }

        btn_auth_authentication.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            authenticate()
        }
    }

    private fun verificationCallBacks(){
        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progressBar?.visibility = View.INVISIBLE
                signIn(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {}

            override fun onCodeSent(verification: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verification, p1)
                verificationId = verification.toString()
                progressBar.visibility = View.INVISIBLE
            }

        }
    }

    private fun authenticate(){
        val verificationNum = edt_virification_authentication.text.toString()
        val authNum2 = AuthNum2(verificationNum)
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, authNum2.verifNumber)
        signIn(credential)
    }

    private fun verify(){
        verificationCallBacks()
        val phoneNum = edt_phone_number_authentication.text.toString()
        val authNum = AuthNum(phoneNum)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(authNum.phoneNumber,60, TimeUnit.SECONDS, this, mCallBacks)
    }

    private fun signIn(credential: PhoneAuthCredential){
        mAuth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){
                toast("Logged in successfully !")
                startActivity(Intent(this, MainActivity::class.java))
            } else{
                toast("Error: ${it.exception}")
            }
        }
    }

    private fun toast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}
