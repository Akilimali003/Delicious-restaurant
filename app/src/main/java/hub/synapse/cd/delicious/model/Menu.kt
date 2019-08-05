package hub.synapse.cd.delicious.model

class Menu {

    var menu_id: Int = 0
    var menu_name: String = ""
    var menu_price: String = ""
    var menu_type: String = ""
    var menu_category: String = ""
    var menu_rid: Int = 0

    constructor() //null constructor

    constructor(menu_name: String, menu_price: String, menu_type: String, menu_category: String, menu_rid: Int){
        this.menu_name = menu_name
        this.menu_price = menu_price
        this.menu_type = menu_type
        this.menu_category = menu_category
        this.menu_rid = menu_rid
    }
}