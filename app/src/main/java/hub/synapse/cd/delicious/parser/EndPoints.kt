package hub.synapse.cd.delicious.parser

/**
 * Created by Michelo on 2019-07-11 at 12:34.
 * for project -> Delicious-restaurant Copyright : SynapseHub
 */
class EndPoints {
    companion object{
        private const val URL_ROOT="http://www.michelo.pw/deliciousapi/"
        private const val url_add_food=""
        const val URL_ADD_RESTAURANT= URL_ROOT +"push_restaurant.php"
        const val URL_GET_RESTAURANT= URL_ROOT +"get_all_restaurants.php"
        const val URL_ADD_USER=URL_ROOT+"insertUser.php"
        const val URL_GET_USER=URL_ROOT+"get_all_users.php"
    }
}