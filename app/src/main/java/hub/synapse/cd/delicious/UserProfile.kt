package hub.synapse.cd.delicious

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hub.synapse.cd.delicious.helper.DeliciousDatabaseHelper
import hub.synapse.cd.delicious.model.User
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        btn_save_user_profile.setOnClickListener {
            saveUserProfile()
        }
    }

    fun saveUserProfile(){
        val userProfile = User(edit_text_username.text.toString(), edit_text_email.text.toString(), edit_text_password.text.toString())
        var us =  DeliciousDatabaseHelper(this)
        us.insertUser(userProfile)

        toast("Insertion reussi!")
    }

    fun toast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
