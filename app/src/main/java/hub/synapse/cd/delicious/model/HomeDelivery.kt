package hub.synapse.cd.delicious.model

class HomeDelivery {

    var delivery_id: Int = 0
    var delivery_address: String = ""
    var delivery_contact: String = ""
    var delivery_cust_id: Int = 0
    var delivery_order_id: Int = 0

    constructor() //null constructor

    constructor(delivery_address: String, delivery_contact: String, delivery_cust_id: Int, delivery_order_id: Int){
        this.delivery_address = delivery_address
        this.delivery_contact = delivery_contact
        this.delivery_cust_id = delivery_cust_id
        this.delivery_order_id = delivery_order_id
    }
}