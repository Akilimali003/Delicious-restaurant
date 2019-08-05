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
        const val TableRestaurantType:String="restaurant_type"
        const val TableCategory:String="category"
        const val TableOwner:String="owner"
        const val TableBill:String="bill"
        const val TableOrderBill:String="order_bill"
        const val TableCustomer:String="customer"
        const val TableManager:String="manager"
        const val TableHomeDelivery:String="HOME_DELIVERY"
        const val TableMenu:String="menu"
        const val TableTables:String="tables"
        const val TableBooking:String="booking"
        const val TableFoods:String="foods"

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

        //2.3 Table RestaurantType
        const val rt_id = "rtid"
        const val rt_name = "rt_name"

        //2.4 Table Category
        const val category_id = "cid"
        const val category_name = "c_name"
        const val category_rid = "c_rid"
        const val category_sortval = "c_sortval"

        //2.5 Table Owner
        const val owner_id = "oid"
        const val owner_name = "oname"
        const val owner_contact = "ocontact"
        const val owner_rid = "rid"


        //2.6 Table Bill
        const val bill_order_id = "Order_Id"
        const val bill_customer_id = "Customer_id"
        const val bill_total_amount = "Total_Amount"

        //2.7 Table OrderBill
        const val order_bill_id = "Order_Id"
        const val name = "Name"
        const val quantity = "Quantity"
        const val price = "Price"

        //2.8 Table Customer
        const val cst_id = "id"
        const val cst_name = "c_name"
        const val cst_contact = "c_contact"
        const val cst_email = "c_email"

        //2.9 Table Manager
        const val manager_id = "manager_Id"
        const val manager_name = "m_name"
        const val manager_contact = "m_contact"
        const val manager_adress = "Address"
        const val manager_salary = "Salary"
        const val manager_sex = "Sex"
        const val manager_b_date = "Bdate"
        const val manager_join_date = "Join_Date"

        //2.10 Table HomeDelivery
        const val delivery_id = "Delivery_Id"
        const val delivery_address = "Address"
        const val delivery_contact = "Contact"
        const val delivery_cust_id = "Cust_Id"
        const val delivery_order_id = "Order_Id"

        //2.11 Table Menu
        const val menu_id = "Menu_Id"
        const val menu_name = "Name"
        const val menu_price = "Price"
        const val menu_type = "Type"
        const val menu_category = "Category"
        const val menu_rid = "rid"

        //2.12 Table Tables
        const val tables_number = "Table_Number"
        const val tables_details = "Details"
        const val tables_rid = "rid"

        //2.13 Table Booking
        const val booking_id = "Booking_Id"
        const val booking_table_num = "Table_Num"
        const val booking_when_made = "WhenMade"
        const val booking_date_issued = "DateIssued"
        const val booking_time = "Time"
        const val booking_payment_by = "PaymentBy"
        const val booking_cust_id = "Cust_Id"


        //2.14 Table Foods
        const val food_id = "food_Id"
        const val food_name = "food_name"
        const val food_desc = "food_desc"
        const val food_price = "food_price"
        const val food_img = "food_img"
        const val food_cat = "food_cat"
        const val food_rid = "rid"



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



    //3.3. Table RestaurantType
    const val create_table_restaurant_type:String="CREATE TABLE " + TableRestaurantType + " ( " +
            rt_id + " INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT, " +
            rt_name + " VARCHAR (50) " +
            ");"

    const val drop_table_restaurant_type:String="DROP TABLE IF EXISTS $TableRestaurantType"


    //3.4. Table Category
    const val create_table_category:String="CREATE TABLE " + TableCategory + " ( " +
            category_id + " INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT, " +
            category_name + " VARCHAR (100), " +
            category_rid + " INT, " +
            category_sortval + " INT );"

    const val drop_table_category:String="DROP TABLE IF EXISTS $TableCategory"


    //3.5. Table Owner
    const val create_table_owner:String="CREATE TABLE " + TableOwner + " ( " +
            owner_id + " INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT, " +
            owner_name + " VARCHAR (75) NOT NULL UNIQUE, " +
            owner_contact + " VARCHAR (100) NOT NULL, " +
            owner_rid + " INT NOT NULL," +
            "FOREIGN KEY ('rid') REFERENCES 'restaurant'('rid')" +
            "ON UPDATE CASCADE" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"

    const val drop_table_owner:String="DROP TABLE IF EXISTS $TableOwner"


    //3.6. Table Bill
    const val create_table_bill:String="CREATE TABLE " + TableBill + " ( " +
            bill_order_id + " INT NOT NULL AUTOINCREMENT, " +
            bill_customer_id + " INT NOT NULL, " +
            bill_total_amount + " DOUBLE NOT NULL, " +
            " PRIMARY KEY ('Order_Id') )ENGINE=InnoDB DEFAULT CHARSET=latin1; "

    const val drop_table_bill:String="DROP TABLE IF EXISTS $TableBill"

    //3.7. Table OrderBill
    const val create_table_order_bill:String="CREATE TABLE " + TableOrderBill + " ( " +
            order_bill_id + " INT NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            name + " VARCHAR (100) NOT NULL, " +
            quantity + " VARCHAR (20) NOT NULL, " +
            price + " VARCHAR (20) NOT NULL, " +
            "FOREIGN KEY('Order_Id') REFERENCES 'bill'('Order_Id')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_order_bill:String="DROP TABLE IF EXISTS $TableOrderBill"

    //3.8. Table Customer
    const val create_table_customer:String="CREATE TABLE " + TableCustomer + " ( " +
            cst_id + " INT NOT NULL AUTOINCREMENT, " +
            cst_name + " VARCHAR (75) NOT NULL, " +
            cst_contact + " VARCHAR (20) DEFAULT NULL, " +
            cst_email + " VARCHAR (50) DEFAULT NULL, " +
            "PRIMARY KEY('id')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_customer:String="DROP TABLE IF EXISTS $TableCustomer"

    //3.9. Table Manager
    const val create_table_manager:String="CREATE TABLE " + TableManager + " ( " +
            manager_id + " INT NOT NULL AUTOINCREMENT, " +
            manager_name + " VARCHAR (75) NOT NULL, " +
            manager_contact + " VARCHAR (20) NOT NULL, " +
            manager_adress + " VARCHAR (30) DEFAULT NULL, " +
            manager_salary + " VARCHAR (30) DEFAULT NULL, " +
            manager_sex + " CHAR (1) DEFAULT NULL, " +
            manager_b_date + " DATE DEFAULT NULL, " +
            manager_join_date + " DATE NOT NULL, " +
            "PRIMARY KEY('manager_Id')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"

    const val drop_table_manager:String="DROP TABLE IF EXISTS $TableManager"


    //3.10. Table Home_Delivery
    const val create_table_home_delivery:String="CREATE TABLE " + TableHomeDelivery + " ( " +
            delivery_id + " INT NOT NULL AUTOINCREMENT, " +
            delivery_address + " VARCHAR (50) NOT NULL, " +
            delivery_contact + " VARCHAR (20) NOT NULL, " +
            delivery_cust_id + " INT NOT NULL, " +
            delivery_order_id + " INT NOT NULL, " +
            "PRIMARY KEY('Delivery_Id')," +
            "FOREIGN KEY('Cust_Id') REFERENCES 'customer'('id')," +
            "FOREIGN KEY('Order_Id') REFERENCES 'bill'('Order_Id')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_home_delivery:String="DROP TABLE IF EXISTS $TableHomeDelivery"

    //3.11. Table Menu
    const val create_table_menu:String="CREATE TABLE " + TableMenu + " ( " +
            menu_id + " INT NOT NULL AUTOINCREMENT, " +
            menu_name + " VARCHAR (100) NOT NULL, " +
            menu_price + " VARCHAR (20) NOT NULL, " +
            menu_type + " VARCHAR (20) DEFAULT NULL, " +
            menu_category + " VARCHAR (30) NOT NULL, " +
            menu_rid + " INT NOT NULL, " +
            "PRIMARY KEY('Menu_Id')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_menu:String="DROP TABLE IF EXISTS $TableMenu"

    //3.12. Table Tables
    const val create_table_tables:String="CREATE TABLE " + TableTables + " ( " +
            tables_number + " VARCHAR (9) NOT NULL UNIQUE, " +
            tables_details + " VARCHAR (200) DEFAULT NULL, " +
            tables_rid + " INT NOT NULL, " +
            "PRIMARY KEY('Table_Number')," +
            "FOREIGN KEY ('rid') REFERENCES 'restaurant'('rid')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_tables:String="DROP TABLE IF EXISTS $TableTables"

    //3.13. Table Booking
    const val create_table_booking:String="CREATE TABLE " + TableBooking + " ( " +
            booking_id + " INT NOT NULL AUTOINCREMENT, " +
            booking_table_num + " VARCHAR (30) NOT NULL, " +
            booking_when_made + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            booking_date_issued + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
            booking_time + " VARCHAR (30) NOT NULL, " +
            booking_payment_by + " VARCHAR (20) NOT NULL, " +
            booking_cust_id + " INT NOT NULL, " +
            "PRIMARY KEY('Booking_Id')," +
            "FOREIGN KEY ('Cust_Id') REFERENCES 'customer'('id')" +
            "FOREIGN KEY ('Table_Num') REFERENCES 'tables'('Table_Number')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_booking:String="DROP TABLE IF EXISTS $TableBooking"

    //3.14. Table Foods
    const val create_table_foods:String="CREATE TABLE " + TableFoods + " ( " +
            food_id + " INT NOT NULL AUTOINCREMENT, " +
            food_name + " VARCHAR (200) NOT NULL, " +
            food_desc + " VARCHAR (250) NOT NULL, " +
            food_price + " DOUBLE NOT NULL, " +
            food_img + " LONGBLOB, " +
            food_cat + " VARCHAR (100) NOT NULL, " +
            food_rid + " INT NOT NULL, " +
            "PRIMARY KEY('food_Id')," +
            "FOREIGN KEY ('rid') REFERENCES 'restaurant'('rid')" +
            ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"


    const val drop_table_foods:String="DROP TABLE IF EXISTS $TableFoods"

    }

}