package hub.synapse.cd.delicious.model

class Tables {

    var tables_number: String = ""
    var tables_details: String = ""
    var tables_rid: Int = 0

    constructor() //null constructor

    constructor(tables_number: String, tables_details: String, tables_rid: Int){
        this.tables_number = tables_number
        this.tables_details = tables_details
        this.tables_rid = tables_rid
    }
}