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
import hub.synapse.cd.delicious.model.*

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


    //** Table User
    fun insertUser(user:User){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.u_name,user.u_name)
        contentValues.put(FieldsContracts.u_email,user.u_email)
        contentValues.put(FieldsContracts.u_password,user.u_password)

        db.insert(FieldsContracts.TableUser,null,contentValues)
        db.close()
    }

    //delete all Users records in the local database
    fun deleteFromUser(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableUser}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }


    //allow to have one user
    fun get_OneUser(u_name: String): User {
        val tasks = User()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableUser} WHERE ${FieldsContracts.u_name} = $u_name"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.u_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_name))
                tasks.u_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_email))
                tasks.u_password=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_password))
            }
        }
        cursor.close()
        return tasks
    }


    //to retrieve a list of users in the database
    fun get_AllUsers(ctx:Context): java.util.ArrayList<User> {
        val qry="select * from ${FieldsContracts.TableUser}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val users= java.util.ArrayList<User>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val us=User()
                us.u_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_name))
                us.u_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_email))
                us.u_password=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_password))


                users.add(us)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return users
    }

    //to execute after a Sync operation was successfully
    fun get_AllUsers_AfterSync(ctx:Context): java.util.ArrayList<User> {
        val qry="select * from ${FieldsContracts.TableUser} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val users= java.util.ArrayList<User>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val us = User()
                us.u_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_name))
                us.u_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_email))
                us.u_password=cursor.getString(cursor.getColumnIndex(FieldsContracts.u_password))

                users.add(us)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return users
    }


    //** Table RestaurantType
    fun insertRestaurantType(restaurantType: RestaurantType){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.r_id,restaurantType.rt_id)
        contentValues.put(FieldsContracts.rt_name,restaurantType.rt_name)

        db.insert(FieldsContracts.TableRestaurantType,null,contentValues)
        db.close()
    }


    //delete all RestaurantType records in the local database
    fun deleteFromRestaurantType(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableRestaurantType}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }


    //allow to have one RestaurantType
    fun get_OneRestaurantType(rt_id: Int): RestaurantType {
        val tasks = RestaurantType()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableUser} WHERE ${FieldsContracts.rt_id} = $rt_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.rt_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.rt_id))
                tasks.rt_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.rt_name))
            }
        }
        cursor.close()
        return tasks
    }


    //to retrieve a list of RestaurantType in the database
    fun get_AllRestaurantType(ctx:Context): java.util.ArrayList<RestaurantType> {
        val qry="select * from ${FieldsContracts.TableRestaurantType}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val restaurantType= java.util.ArrayList<RestaurantType>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val rsType=RestaurantType()
                rsType.rt_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.rt_id))
                rsType.rt_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.rt_name))
                restaurantType.add(rsType)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return restaurantType
    }


    //to execute after a Sync operation was successfully
    fun get_AllRestaurantType_AfterSync(ctx:Context): java.util.ArrayList<RestaurantType> {
        val qry="select * from ${FieldsContracts.TableUser} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val restaurantType= java.util.ArrayList<RestaurantType>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val rsType = RestaurantType()
                rsType.rt_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.rt_id))
                rsType.rt_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.rt_name))

                restaurantType.add(rsType)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return restaurantType
    }

    //** Table Category
    fun insertCategory(category: Category){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.category_id,category.category_id)
        contentValues.put(FieldsContracts.category_name,category.category_name)
        contentValues.put(FieldsContracts.category_sortval,category.category_sortval)

        db.insert(FieldsContracts.TableRestaurantType,null,contentValues)
        db.close()
    }

    //delete all Category records in the local database
    fun deleteFromCategory(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableCategory}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }


    //allow to have one Category
    fun get_OneCategory(category_id: Int): Category {
        val tasks = Category()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableUser} WHERE ${FieldsContracts.category_id} = $category_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.category_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_id))
                tasks.category_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.category_name))
                tasks.category_sortval=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_sortval))
            }
        }
        cursor.close()
        return tasks
    }


    //to retrieve a list of Category in the database
    fun get_AllCategory(ctx:Context): java.util.ArrayList<Category> {
        val qry="select * from ${FieldsContracts.TableCategory}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val category= java.util.ArrayList<Category>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val ct=Category()
                ct.category_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_id))
                ct.category_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.category_name))
                ct.category_sortval=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_sortval))
                category.add(ct)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return category
    }

    //to execute after a Sync operation was successfully
    fun get_AllCategory_AfterSync(ctx:Context): java.util.ArrayList<Category> {
        val qry="select * from ${FieldsContracts.TableUser} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val category= java.util.ArrayList<Category>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val ct = Category()
                ct.category_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_id))
                ct.category_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.category_name))
                ct.category_sortval=cursor.getInt(cursor.getColumnIndex(FieldsContracts.category_sortval))

                category.add(ct)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return category
    }

    //** Table Owner
    fun insertOwner(owner: Owner){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.owner_id,owner.owner_id)
        contentValues.put(FieldsContracts.owner_name,owner.owner_name)
        contentValues.put(FieldsContracts.owner_contact,owner.owner_contact)

        db.insert(FieldsContracts.TableRestaurantType,null,contentValues)
        db.close()
    }

    //delete all Owner records in the local database
    fun deleteFromOwner(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableOwner}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Owner
    fun get_OneOwner(owner_id: Int): Owner {
        val tasks = Owner()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableOwner} WHERE ${FieldsContracts.u_name} = $owner_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.owner_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.owner_id))
                tasks.owner_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_name))
                tasks.owner_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_contact))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Owner in the database
    fun get_AllOwner(ctx:Context): java.util.ArrayList<Owner> {
        val qry="select * from ${FieldsContracts.TableOwner}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val owner= java.util.ArrayList<Owner>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val ow=Owner()
                ow.owner_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.owner_id))
                ow.owner_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_name))
                ow.owner_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_contact))
                owner.add(ow)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return owner
    }

    //to execute after a Sync operation was successfully
    fun get_AllOwner_AfterSync(ctx:Context): java.util.ArrayList<Owner> {
        val qry="select * from ${FieldsContracts.TableOwner} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val owner= java.util.ArrayList<Owner>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val ow = Owner()
                ow.owner_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.owner_id))
                ow.owner_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_name))
                ow.owner_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.owner_contact))

                owner.add(ow)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return owner
    }


    //** Table Bill
    fun insertBill(bill: Bill){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.bill_order_id,bill.bill_order_id)
        contentValues.put(FieldsContracts.bill_customer_id,bill.bill_customer_id)
        contentValues.put(FieldsContracts.bill_total_amount,bill.bill_customer_id)

        db.insert(FieldsContracts.TableBill,null,contentValues)
        db.close()
    }

    //delete all Bill records in the local database
    fun deleteFromBill(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableBill}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Bill
    fun get_OneBill(bill_order_id: Int): Bill {
        val tasks = Bill()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableBill} WHERE ${FieldsContracts.bill_order_id} = $bill_order_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.bill_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_order_id))
                tasks.bill_customer_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_customer_id))
                tasks.bill_total_amount=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.bill_total_amount))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Bill in the database
    fun get_AllBill(ctx:Context): java.util.ArrayList<Bill> {
        val qry="select * from ${FieldsContracts.TableBill}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val bill= java.util.ArrayList<Bill>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val bl=Bill()
                bl.bill_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_order_id))
                bl.bill_customer_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_customer_id))
                bl.bill_total_amount=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.bill_total_amount))
                bill.add(bl)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return bill
    }

    //to execute after a Sync operation was successfully
    fun get_AllBill_AfterSync(ctx:Context): java.util.ArrayList<Bill> {
        val qry="select * from ${FieldsContracts.TableBill} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val bill= java.util.ArrayList<Bill>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val bl=Bill()
                bl.bill_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_order_id))
                bl.bill_customer_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.bill_customer_id))
                bl.bill_total_amount=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.bill_total_amount))
                bill.add(bl)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return bill
    }


    //** Table OrderBill
    fun insertOrderBill(orderBill: OrderBill){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.order_bill_id,orderBill.order_bill_id)
        contentValues.put(FieldsContracts.name,orderBill.name)
        contentValues.put(FieldsContracts.quantity,orderBill.quantity)
        contentValues.put(FieldsContracts.price,orderBill.price)

        db.insert(FieldsContracts.TableOrderBill,null,contentValues)
        db.close()
    }

    //delete all OrderBill records in the local database
    fun deleteFromOrderBill(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableOrderBill}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one OrderBill
    fun get_OneOrderBill(orderBillId: Int): OrderBill {
        val tasks = OrderBill()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableOrderBill} WHERE ${FieldsContracts.order_bill_id} = $orderBillId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.order_bill_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.order_bill_id))
                tasks.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.name))
                tasks.quantity=cursor.getString(cursor.getColumnIndex(FieldsContracts.quantity))
                tasks.price=cursor.getInt(cursor.getColumnIndex(FieldsContracts.price))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of OrderBill in the database
    fun get_AllOrderBill(ctx:Context): java.util.ArrayList<OrderBill> {
        val qry="select * from ${FieldsContracts.TableOrderBill}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val obill= java.util.ArrayList<OrderBill>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val obl=OrderBill()
                obl.order_bill_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.order_bill_id))
                obl.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.name))
                obl.quantity=cursor.getString(cursor.getColumnIndex(FieldsContracts.quantity))
                obl.price=cursor.getInt(cursor.getColumnIndex(FieldsContracts.price))
                obill.add(obl)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return obill
    }

    //to execute after a Sync operation was successfully
    fun get_AllOrderBill_AfterSync(ctx:Context): java.util.ArrayList<OrderBill> {
        val qry="select * from ${FieldsContracts.TableOrderBill} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val obill= java.util.ArrayList<OrderBill>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val obl = OrderBill()
                obl.order_bill_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.order_bill_id))
                obl.name=cursor.getString(cursor.getColumnIndex(FieldsContracts.name))
                obl.quantity=cursor.getString(cursor.getColumnIndex(FieldsContracts.quantity))
                obl.price=cursor.getInt(cursor.getColumnIndex(FieldsContracts.price))
                obill.add(obl)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return obill
    }

    //** Table Customer
    fun insertCustomer(customer: Customer){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.cst_id,customer.cst_id)
        contentValues.put(FieldsContracts.cst_name,customer.cst_name)
        contentValues.put(FieldsContracts.cst_contact,customer.cst_contact)
        contentValues.put(FieldsContracts.cst_email,customer.cst_email)

        db.insert(FieldsContracts.TableCustomer,null,contentValues)
        db.close()
    }

    //delete all Customer records in the local database
    fun deleteFromCustomer(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableCustomer}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Customer
    fun get_OneCustomer(customerId: Int): Customer {
        val tasks = Customer()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableCustomer} WHERE ${FieldsContracts.cst_id} = $customerId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.cst_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.cst_id))
                tasks.cst_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_name))
                tasks.cst_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_contact))
                tasks.cst_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_email))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Customer in the database
    fun get_AllCustomer(ctx:Context): java.util.ArrayList<Customer> {
        val qry="select * from ${FieldsContracts.TableCustomer}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val cst= java.util.ArrayList<Customer>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val cm=Customer()
                cm.cst_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.cst_id))
                cm.cst_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_name))
                cm.cst_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_contact))
                cm.cst_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_email))
                cst.add(cm)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return cst
    }

    //to execute after a Sync operation was successfully
    fun get_AllCustomer_AfterSync(ctx:Context): java.util.ArrayList<Customer> {
        val qry="select * from ${FieldsContracts.TableCustomer} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val cst= java.util.ArrayList<Customer>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val cm = Customer()
                cm.cst_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.cst_id))
                cm.cst_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_name))
                cm.cst_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_contact))
                cm.cst_email=cursor.getString(cursor.getColumnIndex(FieldsContracts.cst_email))
                cst.add(cm)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return cst
    }


    //** Table Manager
    fun insertManager(manager: Manager){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.manager_id,manager.manager_id)
        contentValues.put(FieldsContracts.manager_name,manager.manager_name)
        contentValues.put(FieldsContracts.manager_contact,manager.manager_contact)
        contentValues.put(FieldsContracts.manager_adress,manager.manager_address)
        contentValues.put(FieldsContracts.manager_salary,manager.manager_salary)
        contentValues.put(FieldsContracts.manager_sex,manager.manager_sex)
        contentValues.put(FieldsContracts.manager_b_date,manager.manager_b_date)
        contentValues.put(FieldsContracts.manager_join_date,manager.manager_join_date)

        db.insert(FieldsContracts.TableManager,null,contentValues)
        db.close()
    }

    //delete all Manager records in the local database
    fun deleteFromManager(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableManager}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Manager
    fun get_OneManager(managerId: Int): Manager {
        val tasks = Manager()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableManager} WHERE ${FieldsContracts.manager_id} = $managerId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.manager_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.manager_id))
                tasks.manager_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_name))
                tasks.manager_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_contact))
                tasks.manager_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_adress))
                tasks.manager_salary=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_salary))
                tasks.manager_sex=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_sex))
                tasks.manager_b_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_b_date))
                tasks.manager_join_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_join_date))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Manager in the database
    fun get_AllManager(ctx:Context): java.util.ArrayList<Manager> {
        val qry="select * from ${FieldsContracts.TableManager}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val mng= java.util.ArrayList<Manager>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val mg=Manager()
                mg.manager_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.manager_id))
                mg.manager_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_name))
                mg.manager_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_contact))
                mg.manager_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_adress))
                mg.manager_salary=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_salary))
                mg.manager_sex=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_sex))
                mg.manager_b_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_b_date))
                mg.manager_join_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_join_date))
                mng.add(mg)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return mng
    }

    //to execute after a Sync operation was successfully
    fun get_AllManager_AfterSync(ctx:Context): java.util.ArrayList<Manager> {
        val qry="select * from ${FieldsContracts.TableCustomer} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val mng= java.util.ArrayList<Manager>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val mg = Manager()
                mg.manager_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.manager_id))
                mg.manager_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_name))
                mg.manager_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_contact))
                mg.manager_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_adress))
                mg.manager_salary=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_salary))
                mg.manager_sex=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_sex))
                mg.manager_b_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_b_date))
                mg.manager_join_date=cursor.getString(cursor.getColumnIndex(FieldsContracts.manager_join_date))
                mng.add(mg)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return mng
    }

    //** Table HomeDelivery
    fun insertHomeDelivery(homeDelivery: HomeDelivery){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.delivery_id,homeDelivery.delivery_id)
        contentValues.put(FieldsContracts.delivery_address,homeDelivery.delivery_address)
        contentValues.put(FieldsContracts.delivery_contact,homeDelivery.delivery_contact)
        contentValues.put(FieldsContracts.delivery_cust_id,homeDelivery.delivery_cust_id)
        contentValues.put(FieldsContracts.delivery_order_id,homeDelivery.delivery_order_id)

        db.insert(FieldsContracts.TableHomeDelivery,null,contentValues)
        db.close()
    }

    //delete all HomeDelivery records in the local database
    fun deleteFromHomeDelivery(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableHomeDelivery}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one HomeDelivery
    fun get_OneHomeDelivery(homeDeliveryId: Int): HomeDelivery {
        val tasks = HomeDelivery()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableHomeDelivery} WHERE ${FieldsContracts.delivery_id} = $homeDeliveryId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.delivery_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_id))
                tasks.delivery_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_address))
                tasks.delivery_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_contact))
                tasks.delivery_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_cust_id))
                tasks.delivery_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_order_id))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of HomeDelivery in the database
    fun get_AllHomeDelivery(ctx:Context): java.util.ArrayList<HomeDelivery> {
        val qry="select * from ${FieldsContracts.TableHomeDelivery}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val hdv= java.util.ArrayList<HomeDelivery>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val hd = HomeDelivery()
                hd.delivery_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_id))
                hd.delivery_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_address))
                hd.delivery_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_contact))
                hd.delivery_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_cust_id))
                hd.delivery_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_order_id))
                hdv.add(hd)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return hdv
    }

    //to execute after a Sync operation was successfully
    fun get_AllHomeDelivery_AfterSync(ctx:Context): java.util.ArrayList<HomeDelivery> {
        val qry="select * from ${FieldsContracts.TableCustomer} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val hdv= java.util.ArrayList<HomeDelivery>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val hd = HomeDelivery()
                hd.delivery_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_id))
                hd.delivery_address=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_address))
                hd.delivery_contact=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_contact))
                hd.delivery_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_cust_id))
                hd.delivery_order_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_order_id))
                hdv.add(hd)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return hdv
    }


    //** Table Menu
    fun insertMenu(menu: Menu){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.menu_id,menu.menu_id)
        contentValues.put(FieldsContracts.menu_name,menu.menu_name)
        contentValues.put(FieldsContracts.menu_price,menu.menu_price)
        contentValues.put(FieldsContracts.menu_type,menu.menu_type)
        contentValues.put(FieldsContracts.menu_category,menu.menu_category)
        contentValues.put(FieldsContracts.menu_rid,menu.menu_rid)

        db.insert(FieldsContracts.TableMenu,null,contentValues)
        db.close()
    }

    //delete all Menu records in the local database
    fun deleteFromMenu(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableMenu}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Menu
    fun get_OneMenu(menuId: Int): Menu {
        val tasks = Menu()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableMenu} WHERE ${FieldsContracts.menu_id} = $menuId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.menu_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.menu_id))
                tasks.menu_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_name))
                tasks.menu_price=cursor.getString(cursor.getColumnIndex(FieldsContracts.price))
                tasks.menu_type=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_type))
                tasks.menu_category=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_category))
                tasks.menu_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.menu_rid))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Menu in the database
    fun get_AllMenu(ctx:Context): java.util.ArrayList<Menu> {
        val qry="select * from ${FieldsContracts.TableMenu}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val menus= java.util.ArrayList<Menu>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val mn = Menu()
                mn.menu_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_id))
                mn.menu_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_address))
                mn.menu_price=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_contact))
                mn.menu_type=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_cust_id))
                mn.menu_category=cursor.getString(cursor.getColumnIndex(FieldsContracts.delivery_order_id))
                mn.menu_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.delivery_order_id))
                menus.add(mn)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return menus
    }

    //to execute after a Sync operation was successfully
    fun get_AllMenus_AfterSync(ctx:Context): java.util.ArrayList<Menu> {
        val qry="select * from ${FieldsContracts.TableMenu} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val menus= java.util.ArrayList<Menu>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val mn = Menu()
                mn.menu_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.menu_id))
                mn.menu_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_name))
                mn.menu_price=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_price))
                mn.menu_type=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_type))
                mn.menu_category=cursor.getString(cursor.getColumnIndex(FieldsContracts.menu_category))
                mn.menu_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.menu_rid))
                menus.add(mn)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return menus
    }

    //** Table Tables
    fun insertTables(tables: Tables){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.tables_number,tables.tables_number)
        contentValues.put(FieldsContracts.tables_details,tables.tables_details)
        contentValues.put(FieldsContracts.tables_rid,tables.tables_rid)

        db.insert(FieldsContracts.TableTables,null,contentValues)
        db.close()
    }

    //delete all Tables records in the local database
    fun deleteFromTables(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableTables}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Table... but this logic i don't know if it'll be logically good
    fun get_OneTable(tabNum: String): Tables {
        val tasks = Tables()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableTables} WHERE ${FieldsContracts.tables_number} = $tabNum"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.tables_number=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_number))
                tasks.tables_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_details))
                tasks.tables_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.tables_rid))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Tables in the database
    fun get_AllTables(ctx:Context): java.util.ArrayList<Tables> {
        val qry="select * from ${FieldsContracts.TableTables}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val tables= java.util.ArrayList<Tables>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val tb = Tables()
                tb.tables_number=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_number))
                tb.tables_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_details))
                tb.tables_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.tables_rid))
                tables.add(tb)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return tables
    }

    //to execute after a Sync operation was successfully
    fun get_AllTables_AfterSync(ctx:Context): java.util.ArrayList<Tables> {
        val qry="select * from ${FieldsContracts.TableTables} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val tables= java.util.ArrayList<Tables>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val tb = Tables()
                tb.tables_number=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_number))
                tb.tables_details=cursor.getString(cursor.getColumnIndex(FieldsContracts.tables_details))
                tb.tables_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.tables_rid))
                tables.add(tb)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return tables
    }

    //** Table Booking
    fun insertBooking(booking: Booking){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.booking_id,booking.booking_id)
        contentValues.put(FieldsContracts.booking_table_num,booking.booking_table_num)
        contentValues.put(FieldsContracts.booking_when_made,booking.booking_when_made)
        contentValues.put(FieldsContracts.booking_date_issued,booking.booking_date_issued)
        contentValues.put(FieldsContracts.booking_time,booking.booking_time)
        contentValues.put(FieldsContracts.booking_payment_by,booking.booking_payment_by)
        contentValues.put(FieldsContracts.booking_cust_id,booking.booking_cust_id)

        db.insert(FieldsContracts.TableBooking,null,contentValues)
        db.close()
    }

    //delete all Booking records in the local database
    fun deleteFromBooking(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableBooking}")
        ShortcutMethods.makeToastMessage(mContext,"Insertion locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Book
    fun get_OneBook(bookId: Int): Booking {
        val tasks = Booking()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableBooking} WHERE ${FieldsContracts.booking_id} = $bookId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.booking_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_id))
                tasks.booking_table_num=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_table_num))
                tasks.booking_when_made=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_when_made))
                tasks.booking_date_issued=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_date_issued))
                tasks.booking_time=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_time))
                tasks.booking_payment_by=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_payment_by))
                tasks.booking_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_cust_id))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Book in the database
    fun get_AllBook(ctx:Context): java.util.ArrayList<Booking> {
        val qry="select * from ${FieldsContracts.TableBooking}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val book= java.util.ArrayList<Booking>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val bk = Booking()
                bk.booking_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_id))
                bk.booking_table_num=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_table_num))
                bk.booking_when_made=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_when_made))
                bk.booking_date_issued=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_date_issued))
                bk.booking_time=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_time))
                bk.booking_payment_by=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_payment_by))
                bk.booking_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_cust_id))
                book.add(bk)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return book
    }

    //to execute after a Sync operation was successfully
    fun get_AllBook_AfterSync(ctx:Context): java.util.ArrayList<Booking> {
        val qry="select * from ${FieldsContracts.TableBooking} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val book= java.util.ArrayList<Booking>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val bk = Booking()
                bk.booking_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_id))
                bk.booking_table_num=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_table_num))
                bk.booking_when_made=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_when_made))
                bk.booking_date_issued=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_date_issued))
                bk.booking_time=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_time))
                bk.booking_payment_by=cursor.getString(cursor.getColumnIndex(FieldsContracts.booking_payment_by))
                bk.booking_cust_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.booking_cust_id))
                book.add(bk)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return book
    }

    //** Table Food
    fun insertFood(food: Food){
        val db=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(FieldsContracts.food_id,food.food_id)
        contentValues.put(FieldsContracts.food_name,food.food_name)
        contentValues.put(FieldsContracts.food_desc,food.food_desc)
        contentValues.put(FieldsContracts.food_price,food.food_price)
        contentValues.put(FieldsContracts.food_img,food.food_img)
        contentValues.put(FieldsContracts.food_cat,food.food_cat)
        contentValues.put(FieldsContracts.food_rid,food.food_rid)

        db.insert(FieldsContracts.TableFoods,null,contentValues)
        db.close()
    }

    //delete all Food records in the local database
    fun deleteFromFood(){
        val db=this.writableDatabase
        db.execSQL("DELETE FROM ${FieldsContracts.TableFoods}")
        ShortcutMethods.makeToastMessage(mContext,"Suppression locale reussie", ToastType.Long )
        db.close()
    }

    //allow to have one Food
    fun get_OneFood(foodId: Int): Food {
        val tasks = Food()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM ${FieldsContracts.TableFoods} WHERE ${FieldsContracts.food_id} = $foodId"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.food_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_id))
                tasks.food_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_name))
                tasks.food_desc=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_desc))
                tasks.food_price=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.food_price))
                tasks.food_img=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_img))
                tasks.food_cat=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_cat))
                tasks.food_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_rid))
            }
        }
        cursor.close()
        return tasks
    }

    //to retrieve a list of Food in the database
    fun get_AllFood(ctx:Context): java.util.ArrayList<Food> {
        val qry="select * from ${FieldsContracts.TableFoods}"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val food= java.util.ArrayList<Food>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val fd = Food()
                fd.food_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_id))
                fd.food_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_name))
                fd.food_desc=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_desc))
                fd.food_price=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.food_price))
                fd.food_img=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_img))
                fd.food_cat=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_cat))
                fd.food_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_rid))
                food.add(fd)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return food
    }

    //to execute after a Sync operation was successfully
    fun get_AllFood_AfterSync(ctx:Context): java.util.ArrayList<Food> {
        val qry="select * from ${FieldsContracts.TableFoods} where sync_status=0"
        val db=this.readableDatabase
        val cursor=db.rawQuery(qry,null)
        val food= java.util.ArrayList<Food>()
        if(cursor.count==0)
            Toast.makeText(ctx,"Aucune donnée trouvée", Toast.LENGTH_LONG).show() else
        {
            while(cursor.moveToNext()){
                val fd = Food()
                fd.food_id=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_id))
                fd.food_name=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_name))
                fd.food_desc=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_desc))
                fd.food_price=cursor.getDouble(cursor.getColumnIndex(FieldsContracts.food_price))
                fd.food_img=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_img))
                fd.food_cat=cursor.getString(cursor.getColumnIndex(FieldsContracts.food_cat))
                fd.food_rid=cursor.getInt(cursor.getColumnIndex(FieldsContracts.food_rid))
                food.add(fd)
            }
            Toast.makeText(ctx,"${cursor.count.toString()} enregistrements trouvés", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return food
    }


}