package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class add_celebrity : AppCompatActivity() {
    lateinit var nameEditText : EditText
    lateinit var taboo1EditT : EditText
    lateinit var taboo2EditT : EditText
    lateinit var taboo3EditT : EditText
    lateinit var ADD: Button
    lateinit var back: Button
    var name = ""
    var taboo1= ""
    var taboo2= ""
    var taboo3= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celebrity)
        nameEditText = findViewById(R.id.nameEditText)
        taboo1EditT = findViewById(R.id.taboo1EditT)
        taboo2EditT = findViewById(R.id.taboo2EditT)
        taboo3EditT = findViewById(R.id.taboo3EditT)
        ADD = findViewById(R.id.ADD)
        back =  findViewById(R.id.back)

        ADD.setOnClickListener{
            name =  nameEditText.text.toString()
            taboo1 =  taboo1EditT.text.toString()
            taboo2 =  taboo2EditT.text.toString()
            taboo3 =  taboo3EditT.text.toString()

            addData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        back.setOnClickListener{
          val intent = Intent(this, MainActivity::class.java)
          startActivity(intent)

        }

    }


    fun addData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        var data = Celebrity(1,name,taboo1,taboo2,taboo3)
        apiInterface?.addData(data)?.enqueue(object : Callback<Celebrity> {


            override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {
                Toast.makeText(this@add_celebrity, "ADDED", Toast.LENGTH_LONG).show()


               /// Log.d(TAG, "onResponse: ${user.name}")


            }

            override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                Toast.makeText(this@add_celebrity, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }
}