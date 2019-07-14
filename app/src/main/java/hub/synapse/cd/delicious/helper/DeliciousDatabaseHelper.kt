package hub.synapse.cd.delicious.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import hub.synapse.cd.delicious.model.Restaurant
import hub.synapse.cd.delicious.model.ToastType
import hub.synapse.cd.delicious.utils.ShortcutMethods
import java.sql.SQLException

/**
 * Created by Michelo on 2019-07-12 at 05:01.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class DeliciousDatabaseHelper:SQLiteOpenHelper {

    companion object{
        //nothing here for now
    }


    private var mContext:Context

    constructor(context: Context) : super(context, FieldsContracts.deliciousDB, null, 1) {
        this.mContext = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FieldsContracts.create_table_user)
        db?.execSQL(FieldsContracts.create_table_restaurant)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try{
            db?.execSQL(FieldsContracts.drop_table_restaurant)
        }catch (e:SQLException){
            //...
        }
    }

    //Statements
    //** Table Restaurant
    fun insertRestaurant(resto:Restaurant){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(FieldsContracts.r_name,resto.name)
        cv.put(FieldsContracts.r_location,resto.location)
        cv.put(FieldsContracts.r_phone,resto.phone)
        cv.put(FieldsContracts.r_opening_Closing_Time,resto.r_opening_Closing_Time)
        cv.put(FieldsContracts.r_website,resto.r_image)
        cv.put(FieldsContracts.r_details,resto.r_details)
        cv.put(FieldsContracts.r_email,resto.name)
        cv.put(FieldsContracts.r_image,resto.r_image)
        cv.put(FieldsContracts.r_latitude,resto.r_latitude)
        cv.put(FieldsContracts.r_longitude,resto.r_longitude)

        db.insert(FieldsContracts.TableRestaurant,null,cv)
        db.close()
    }

    //delete all Restaurants records in the local database
    fun deleteFromRestaurant(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableRestaurant}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one restaurant
    fun get_OneRestaurant(_id: Int): Restaurant {
        val tasks = Restaurant()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableRestaurant} WHERE ${FieldsContracts.r_id} = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FieldsContracts.r_id)))
                tasks.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_name))
                tasks.location=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_location))
                tasks.phone=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_phone))
                tasks.r_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_email))
                tasks.r_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_details))
                tasks.r_opening_Closing_Time=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_opening_Closing_Time))
                tasks.r_website=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_website))
                tasks.r_image=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_image))
                tasks.r_latitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_latitude))
                tasks.r_longitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_longitude))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of restaurants in the database
    fun get_AllRestaurants(ctx:Context): java.util.ArrayList<Restaurant> {
        val qry="select * from ${FieldsContracts.TableRestaurant}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val restos= java.util.ArrayList<Restaurant>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnee trouvee", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val tm=Restaurant()
                tm.id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.r_id))
                tm.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_name))
                tm.location=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_location))
                tm.phone=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_phone))
                tm.r_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_email))
                tm.r_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_details))
                tm.r_opening_Closing_Time=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_opening_Closing_Time))
                tm.r_website=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_website))
                tm.r_image=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_image))
                tm.r_latitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_latitude))
                tm.r_longitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_longitude))


                restos.add(tm)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouves", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return restos
    }

    //to execute after a Sync operation was successfully
    fun get_AllRestaurants_AfterSync(ctx:Context): java.util.ArrayList<Restaurant> {
        val qry="select * from ${FieldsContracts.TableRestaurant} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val restos= java.util.ArrayList<Restaurant>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnee trouvee", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val tm=Restaurant()
                tm.id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.r_id))
                tm.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_name))
                tm.location=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_location))
                tm.phone=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_phone))
                tm.r_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_email))
                tm.r_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_details))
                tm.r_opening_Closing_Time=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_opening_Closing_Time))
                tm.r_website=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_website))
                tm.r_image=cursor.getString(cursor.getColumnIndex(FieldsContracts.r_image))
                tm.r_latitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_latitude))
                tm.r_longitude=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.r_longitude))


                restos.add(tm)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouves", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return restos
    }



}