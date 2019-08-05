package hub.synapse.cd.delicious.model

class Category {


    var category_id:Int=0
    var category_name:String=""
    var category_sortval:Int=0

    constructor() // null constructor

    constructor(category_id: Int, category_name:String,c_sortval:Int) {
        this.category_id = category_id
        this.category_name= category_name
        this.category_sortval =category_sortval
    }

}