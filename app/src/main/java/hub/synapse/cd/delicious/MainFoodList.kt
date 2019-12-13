package hub.synapse.cd.delicious

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import hub.synapse.cd.delicious.adapter.FoodList
import hub.synapse.cd.delicious.adapter.ListFoodAdapter
import hub.synapse.cd.delicious.registration.MainActivityb
import hub.synapse.cd.delicious.views.MainFoodDisplay
import kotlinx.android.synthetic.main.food_list.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainFoodList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_food_list)

        val listView = findViewById<ListView>(R.id.listView)
        val foodListData = ArrayList<FoodList>()
        val foodList = ListFoodAdapter(this, foodListData)

        foodListData.add(FoodList(R.drawable.cuisine_chinoise, "Cuisine chinoise", "La cuisine a l'origine Chinoise, trop delicieux, gouter et coter."))
        foodListData.add(FoodList(R.drawable.cuisine_africaine, "Cuisine africaine", "La cuisine a l'origine Africaine, essayer pour ne plus s'en passer."))
        foodListData.add(FoodList(R.drawable.cuisine_italienne, "Cuisine italienne", "La cuisine a l'origine Italienne, meme le pape ne se maitrise pas devant cette cuisine, trop de gout et du professionnalisme."))
        foodListData.add(FoodList(R.drawable.repas_register_screen, "Cuisine mixte", "La cuisine mixte, est le melange de plusiers cuisines, si vous n'avez pas encore essayé c'est maintenant ou jamais."))
        foodListData.add(FoodList(R.drawable.chicken_turkey_meat, "Poulet", "Le poulet d'africaine, essayer pour ne plus s'en passer."))
        foodListData.add(FoodList(R.drawable.delicious, "Cuisine personnalisée", "Faites votre propre choix de la cuisine que vous voulez."))
        foodListData.add(FoodList(R.drawable.cuisine_italienne, "Cuisine italienne", "La cuisine a l'origine Italienne, meme le pape ne se maitrise pas devant cette cuisine, trop de gout et du professionnalisme."))

        listView.adapter = foodList
        listView.divider

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            Toast.makeText(this@MainFoodList, "Nourriture cliquée : - $id", Toast.LENGTH_LONG).show()

            val intent = Intent(applicationContext, MainFoodDisplay::class.java)

            intent.putExtra("Titre", "Titre ajouter manuellement")
            listView.context.startActivity(intent)
        }
    }
}