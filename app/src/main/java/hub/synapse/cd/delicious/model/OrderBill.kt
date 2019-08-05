package hub.synapse.cd.delicious.model

import com.xwray.groupie.R

class OrderBill {

    var order_bill_id:Int=0
    var name:String=""
    var quantity:String=""
    var price:Int=0

    constructor()

    constructor(order_bill_id:Int,name:String,quantity:String,price:Int){
        this.order_bill_id=order_bill_id
        this.name=name
        this.quantity=quantity
        this.price=price
    }
}