package hub.synapse.cd.delicious.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
    fun insertRestaurant(){

    }


}