package hub.synapse.cd.delicious.model

class Booking {

    var booking_id: Int = 0
    var booking_table_num: String = ""
    var booking_when_made: String = ""
    var booking_date_issued: String = ""
    var booking_time: String = ""
    var booking_payment_by: String = ""
    var booking_cust_id: Int = 0

    constructor() //null constructor

    constructor(booking_table_num: String, booking_when_made: String, booking_date_issued: String, booking_time: String, booking_payment_by: String, booking_cust_id: Int){
        this.booking_table_num = booking_table_num
        this.booking_when_made = booking_when_made
        this.booking_date_issued = booking_date_issued
        this.booking_time = booking_time
        this.booking_payment_by = booking_payment_by
        this.booking_cust_id = booking_cust_id
    }
}