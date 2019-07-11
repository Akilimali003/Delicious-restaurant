package hub.synapse.cd.delicious

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by Michelo on 2019-07-11 at 14:21.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class DeliciousApplication:Application() {

    companion object {
        private val TAG=DeliciousApplication::class.java.simpleName
        @get:Synchronized var instance:DeliciousApplication?=null

            private set
    }

    //cree une instance de l'application
    override fun onCreate() {
        super.onCreate()
        instance=this

    }

    val requestQueue: RequestQueue?=null
        get(){
            if(field==null){
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun<T> addToRequestQueue(request: Request<T>){
        request.tag= TAG
        requestQueue?.add(request)
    }
}