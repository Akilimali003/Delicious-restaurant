package hub.synapse.cd.delicious.model

class Owner {

    var owner_id:Int=0
    var owner_name:String=""
    var owner_contact:String=""

    constructor() // null constructor

    constructor(owner_id: Int, owner_name:String,owner_contact:String) {
        this.owner_id = owner_id
        this.owner_name= owner_name
        this.owner_contact =owner_contact
    }

}