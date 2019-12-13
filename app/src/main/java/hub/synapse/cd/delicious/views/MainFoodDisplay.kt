package hub.synapse.cd.delicious.views

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import hub.synapse.cd.delicious.R
import hub.synapse.cd.delicious.registration.MainActivityb
import kotlinx.android.synthetic.main.activity_main_food_display.*

class MainFoodDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_food_display)

        val intent = intent
        val title : String = intent.getStringExtra("Titre")
////        val description : String = intent.getStringExtra("Description")
////        val image : ImageView = intent.getIntArrayExtra()Extra("Image")


        textViewTitleValue.text = title
//        textViewTitleValue.text = title

        val commanderButton = findViewById<Button>(R.id.buttonCommander) as Button

        commanderButton.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@MainFoodDisplay, MainActivityb::class.java)
            startActivity(intent)
        })
    }
}
