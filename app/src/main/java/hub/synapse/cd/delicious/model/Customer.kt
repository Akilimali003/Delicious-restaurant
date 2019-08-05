package hub.synapse.cd.delicious.model

class Customer {

    var cst_id:Int = 0
    var cst_name:String=""
    var cst_contact:String=""
    var cst_email:String=""


    constructor() // null constructor

    constructor(cst_name: String, cst_contact: String, cst_email: String){
        this.cst_name = cst_name
        this.cst_contact = cst_contact
        this.cst_email = cst_email
    }

}