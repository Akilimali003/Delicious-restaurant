package hub.synapse.cd.delicious.model

/**
 * Created by Michelo on 2019-07-11 at 13:19.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class Restaurant {

    var id:Int=0
    var name:String=""
    var location:String=""
    var phone:String=""
    var r_opening_Closing_Time:String=""
    var r_website:String=""
    var r_email:String=""
    var r_details:String=""
    var r_image: String=""
    var r_latitude : Double=0.0
    var r_longitude : Double=0.0


    constructor() // null constructor

    constructor(name: String, location: String,phone:String, r_opening_Closing_Time:String, r_website:String, r_email:String, r_details:String, r_image:String, r_latitude:Double, r_longitude:Double ) {
        this.name = name
        this.location = location
        this.phone=phone
        this.r_opening_Closing_Time=r_opening_Closing_Time
        this.r_website=r_website
        this.r_email=r_email
        this.r_details=r_details
        this.r_image=r_image
        this.r_latitude=r_latitude
        this.r_longitude=r_longitude
        //this.sync_status=sync_status
    }


}