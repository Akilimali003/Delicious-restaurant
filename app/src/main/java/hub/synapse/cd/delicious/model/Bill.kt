package hub.synapse.cd.delicious.model

class Bill {
    var  bill_order_id:Int =0
    var bill_customer_id:Int =0
    var bill_total_amount:Double=0.0

    constructor() // null constructor

    constructor(bill_order_id: Int, bill_customer_id:Int,bill_total_amount:Double) {
        this.bill_order_id = bill_order_id
        this.bill_customer_id= bill_customer_id
        this.bill_total_amount =bill_total_amount
    }

}