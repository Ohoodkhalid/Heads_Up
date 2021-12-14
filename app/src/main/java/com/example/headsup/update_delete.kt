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

class update_delete : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var taboo1: EditText
    lateinit var taboo2: EditText
    lateinit var taboo3: EditText
    lateinit var update: Button
    lateinit var delete: Button
    lateinit var backButton: Button
    var id = 0
    var nameOfCel= ""
    var taboo_1=""
    var taboo_2=""
    var taboo_3=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)
        name = findViewById(R.id.editTextN)


        taboo1 = findViewById(R.id.editTaboo1)
        taboo2 = findViewById(R.id.editTaboo2)
        taboo3 = findViewById(R.id.editTaboo3)

        update = findViewById(R.id.update)
        delete = findViewById(R.id.delete)
        backButton = findViewById(R.id.backbutton)

        id =(intent.extras?.getInt("pk")!!)
        nameOfCel = intent.extras?.getString("celebrityName")!!
        taboo_1 = intent.extras?.getString("taboo1")!!
        taboo_2 =  intent.extras?.getString("taboo2")!!
        taboo_3 =  intent.extras?.getString("taboo3")!!


        name.setText(nameOfCel)
        taboo1.setText( taboo_1)
        taboo2.setText( taboo_2)
        taboo3.setText(taboo_3)





        update.setOnClickListener {

            Log.d("TAG", "COME1: $id")
           // Log.d("TAG", "COME2: $name1")
            Log.d("TAG", "COME2: $taboo_1")
            updatedata(id,name.text.toString(),taboo1.text.toString(),taboo2.text.toString(),taboo3.text.toString())



        }

        delete.setOnClickListener {
            deleteData()


        }
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun updatedata(id:Int ,name1: String, taboo_1: String, taboo_2: String, taboo_3: String) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.updateCelebrityData(id, Celebrity(id, name1, taboo_1, taboo_2, taboo_3))
            ?.enqueue(object :
                Callback<Celebrity> {

                override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {
                    Toast.makeText(this@update_delete, "update", Toast.LENGTH_LONG).show()


                     Log.d("TAG", "UPDATE SUCC: ")


                }

                override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                    Toast.makeText(this@update_delete, "ERROR", Toast.LENGTH_LONG).show()
                }
            })

    }


    fun deleteData(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteCelebrityData(id)?.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@update_delete, "deleted the user", Toast.LENGTH_LONG).show()





            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@update_delete, "ERROR", Toast.LENGTH_LONG).show()
            }
        })

    }
}