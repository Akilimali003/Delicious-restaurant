package hub.synapse.cd.delicious.model

class User {
    var u_id:Int=0
    var u_name:String=""
    var u_email:String=""
    var u_password:String=""

    constructor() // null constructor

    constructor(u_name: String, u_email:String, u_password:String ) {
        this.u_name = u_name
        this.u_email= u_email
        this.u_password = u_password
    }
}