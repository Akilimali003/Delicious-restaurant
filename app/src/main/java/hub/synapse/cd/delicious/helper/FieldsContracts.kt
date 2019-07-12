package hub.synapse.cd.delicious.helper

import javax.sql.StatementEvent

/**
 * Created by Michelo on 2019-07-12 at 04:37.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class FieldsContracts {


    companion object{

        const val SYNC_STATUS="sync_status"
        const val deliciousDB:String="DelicRes.DB"

        //1. Tables
        const val TableRestaurant:String="restaurant"
        const val TableUser:String="user"

        //2. Tables's properties
        //2.1. Table Restaurant
        const val r_id="id"
        const val r_name="r_name"
        const val r_location="r_location"
        const val r_phone="r_phone"
        const val r_opening_Closing_Time="r_opening_Closing_Time"
        const val r_website="r_website"
        const val r_email="r_email"
        const val r_details="r_details"
        const val r_image="r_image"
        const val r_latitude="r_latitude"
        const val r_longitude="r_longitude"
        //2.2. Table User
        private const val u_id="id"
        const val u_name="uname"
        const val u_email="uemail"
        const val u_password="upassword"


        //3.  SQL Statement
        //3.1. Table Restaurant
        const val create_table_restaurant:String="CREATE TABLE " + TableRestaurant + " ( " +
                r_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                r_name + " VARCHAR (100), " +
                r_location + " VARCHAR (200), " +
                r_phone + " VARCHAR (15), " +
                r_opening_Closing_Time + " VARCHAR (100), " +
                r_website + " VARCHAR (200), " +
                r_email + " VARCHAR (100), " +
                r_details + " VARCHAR (250), " +
                r_image + " VARCHAR (250), " +
                r_latitude + " DOUBLE, " +
                r_longitude + " DOUBLE, " +
                SYNC_STATUS + " INTEGER ) "

        const val drop_table_restaurant:String="DROP TABLE IF EXISTS $TableRestaurant"

        //3.2. Table User
        const val create_table_user:String=" CREATE TABLE $TableUser " +
                "(" +
                "$u_id integer primary  key autoincrement," +
                "$u_name varchar(100)," +
                "$u_email varchar(100)," +
                "$u_password varchar(100)" +
                ")"
        const val  drop_table_user:String="DROP TABLE IF EXISTS $TableUser"



    }





}