package hub.synapse.cd.delicious.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Michelo on 2019-07-12 at 05:58.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class PrefsUtils (context:Context) {

    private val PREFS_NAME = "delicprefs"
    val prefs:SharedPreferences=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun save_UserName(key_uname:String, uname:String){
        val editor:SharedPreferences.Editor=prefs.edit()
        editor.putString(key_uname, uname)
        editor!!.commit()
    }

    fun save_SyncStatus(key_sync:String, value:Int){
        val editor:SharedPreferences.Editor=prefs.edit()
        editor.putInt(key_sync, value)
        editor!!.commit()
    }

    fun get_UserName(key_uname: String) : String?{
        return prefs.getString(key_uname,null)
    }

    fun get_SyncStatus(key_sync: String) :Int{
        return prefs.getInt(key_sync,0)
    }

}