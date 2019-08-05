package hub.synapse.cd.delicious.model

class Manager {

    var manager_id: Int = 0
    var manager_name: String = ""
    var manager_contact: String = ""
    var manager_address: String = ""
    var manager_salary: String = ""
    var manager_sex: String = ""
    var manager_b_date: String = ""
    var manager_join_date: String = ""

    constructor() //null constructor

    constructor(manager_name: String, manager_contact: String, manager_address: String, manager_salary: String, manager_sex: String, manager_b_date: String, manager_join_date: String){
        this.manager_name = manager_name
        this.manager_contact = manager_contact
        this.manager_address = manager_address
        this.manager_salary = manager_salary
        this. manager_sex = manager_sex
        this.manager_b_date = manager_b_date
        this.manager_join_date = manager_join_date
    }
}