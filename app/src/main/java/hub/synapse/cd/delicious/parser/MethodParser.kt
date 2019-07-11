package hub.synapse.cd.delicious.parser

import android.content.Context
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import hub.synapse.cd.delicious.DeliciousApplication
import hub.synapse.cd.delicious.model.Restaurant
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

/**
 * Created by Michelo on 2019-07-11 at 13:48.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class MethodParser {
    companion object{

        var zRestaurant= Restaurant()
        //Restaurant values
        var name:String=""
        var location:String=""
        var phone:String=""
        var r_opening_Closing_Time:String=""
        var r_website:String=""
        var r_email:String=""
        var r_details:String=""
        var r_image: String=""
        var r_latitude : Double=0.0
        var r_longitude : Double=0.0

        //sync data from remote Restaurants
        fun syncRestaurantFromRemote(context: Context){

            /*
            sqlidb = deliciousHelper(context)
            sharedPreference = PrefsUtils(context)
            if (sharedPreference.getStatusFirstSyncPrice("statussyncprice")!=null) {
                statusSyncPrice = sharedPreference.getStatusFirstSyncPrice("statussyncprice")!!
            }*/

            val url="http://www.michelo.pw/deliciousapi/get_all_restaurants.php"

            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    try {
                        //converting the string to json array object
                        val array = JSONArray(response)

                        //traversing through all the object
                        for (i in 0 until array.length()) {
                            //getting Restaurant object from json array
                            val monRestaurant = array.getJSONObject(i)


                            name=monRestaurant.getString("name")
                            location=monRestaurant.getString("location")
                            phone=monRestaurant.getString("phone")
                            r_opening_Closing_Time=monRestaurant.getString("r_opening_Closing_Time")
                            r_website= monRestaurant.getString("r_website")
                            r_email=monRestaurant.getString("r_email")
                            r_details=monRestaurant.getString("r_details")
                            r_image=monRestaurant.getString("r_image")
                            r_latitude =monRestaurant.getString("r_latitude").toDouble()
                            r_longitude =monRestaurant.getString("r_longitude").toDouble()


                            //adding to database
                            zRestaurant.name=name
                            zRestaurant.location=location
                            zRestaurant.phone=phone
                            zRestaurant.r_opening_Closing_Time=r_opening_Closing_Time
                            zRestaurant.r_website=r_website
                            zRestaurant.r_email=r_email
                            zRestaurant.r_details=r_details
                            zRestaurant.r_latitude=r_latitude.toDouble()
                            zRestaurant.r_longitude=r_longitude

                            /*
                            if(statusSyncPrice==0){
                                //sqlidb.deleteFromPrice()
                                // loadPrices()
                                sqlidb.insertPrice(MonPrix)
                                sharedPreference.statusFirstSyncPrice("statussyncprice",1)
                            }else if(statusSyncPrice==1){
                                //loadPrices()
                                sqlidb.updatePrice(MonPrix)
                            }
                            */
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { Toast.makeText(context,"All restaurants are sync !", Toast.LENGTH_LONG).show() })
            //adding our stringrequest to queue
            Volley.newRequestQueue(context).add(stringRequest)
        }

        //ajout from the phone to MySqlServer
        fun addRestaurantToRemote(context: Context, buff:Restaurant ){
            //creating volley string request
            val stringRequest = object : StringRequest(
                Request.Method.POST, EndPoints.URL_ADD_RESTAURANT,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(context!!, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError -> Toast.makeText(context!!, volleyError.message, Toast.LENGTH_LONG).show() }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    //val price=buff.prix.toString()
                    params.put("name", buff.name)
                    params.put("location", buff.location)
                    params.put("phone", buff.phone)
                    params.put("r_opening_Closing_Time", buff.r_opening_Closing_Time)
                    params.put("r_website", buff.r_website)
                    params.put("r_email", buff.r_email)
                    params.put("r_details", buff.r_details)
                    params.put("r_latitude", buff.r_latitude.toString())
                    params.put("r_longitude", buff.r_longitude.toString())
                    return params
                }
            }
            //adding request to queue
            DeliciousApplication.instance?.addToRequestQueue(stringRequest)
        }

    }
}