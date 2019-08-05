package hub.synapse.cd.delicious.model

class Food {

    var food_id: Int = 0
    var food_name: String = ""
    var food_desc: String = ""
    var food_price: Double = 0.0
    var food_img: String = ""
    var food_cat: String = ""
    var food_rid: Int = 0

    constructor() //null constructor

    constructor(food_name: String, food_desc: String, food_price: Double, food_img: String, food_cat: String, food_rid: Int){
        this.food_name = food_name
        this.food_desc = food_desc
        this.food_price = food_price
        this.food_img = food_img
        this.food_cat = food_cat
        this.food_rid = food_rid
    }
}