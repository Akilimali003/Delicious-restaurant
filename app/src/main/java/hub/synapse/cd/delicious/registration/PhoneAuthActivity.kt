package hub.synapse.cd.delicious.registration

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import hub.synapse.cd.delicious.R
import kotlinx.android.synthetic.main.activity_phone_auth.*

class PhoneAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)


        btn_suivent_activity_phone_auth.setOnClickListener {
            val phoneNo = edt_phone_number_activity_phone_auth.text.toString()
            val alert = AlertDialog.Builder(this)

                alert.setTitle("Verification")
                alert.setMessage("${phoneNo} êtes-vous sure d'enregistrer ce numero ?")
                alert.setPositiveButton("Oui"){ dialog: DialogInterface?, which: Int ->
                    verify()
                }

                alert.setNegativeButton("Non"){dialog: DialogInterface?, which: Int ->  }
                alert.show()

        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    private fun verify() {
        val phoneNo = edt_phone_number_activity_phone_auth.text.toString()
        if (phoneNo.isEmpty()){
            toast("Completer le numero de téléphone s'il vous plait !!!")
        }else{
            val intent = Intent(this, PhoneAuthActivity2::class.java)
            intent.putExtra("phone", phoneNo)
            startActivity(intent)

            edt_phone_number_activity_phone_auth.setText("")
        }
    }

    private fun toast(message: String?){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}