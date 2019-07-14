package hub.synapse.cd.delicious.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Michelo on 2019-07-12 at 05:56.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class isNetworkConnected {
    companion object {
        @SuppressLint("MissingPermission")
        fun isNEtworkConnected(context: Context): Boolean {

            var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectionManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}


/* use like this

if (NetWorkConection.isNEtworkConnected(applicationContext)) {
    val inent = Intent(this@FirstScreen, MainActivity::class.java)
    startActivity(inent)
} else {
    toast("Please turn on your Internet")
}
*/