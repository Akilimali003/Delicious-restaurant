package hub.synapse.cd.delicious.model

class RestaurantType {
    var rt_id:Int=0
    var rt_name:String=""

    constructor() // null constructor

    constructor(rt_id: Int, rt_name:String) {
        this.rt_id = rt_id
        this.rt_name= rt_name
    }
}