package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var  celebrityData = ArrayList<Celebrity>()
    lateinit var recView : RecyclerView
    lateinit var adapter:RecyclerViewAdapter
    lateinit var celebrityName : EditText
    lateinit var addButton : Button
    lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        recView = findViewById(R.id.recView)
        adapter=RecyclerViewAdapter(celebrityData)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(applicationContext)

        celebrityName= findViewById(R.id.celebrityName)
        addButton= findViewById(R.id.addCelerity)
        submit = findViewById(R.id.submit)


        addButton.setOnClickListener{
            val intent = Intent(this, add_celebrity::class.java)
            startActivity(intent)
        }

        submit.setOnClickListener{
            val name= celebrityName.text.toString()
            if(name.isNotEmpty()){
                checkIfCelebrityNameExist()
            }
            else{
                Toast.makeText(this,"Enter a name",Toast.LENGTH_SHORT).show()
            }




        }



    }


    fun getData(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.getCelebrityData()?.enqueue(object : Callback<List<Celebrity>> {

            override fun onResponse(call: Call<List<Celebrity>>, response: Response<List<Celebrity>>) {



                for(data in response.body()!!){
                    celebrityData.add(data)

                }
                adapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<Celebrity>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun checkIfCelebrityNameExist(){
        var celebrityName1=""
        var taboo1= ""
        var taboo2 = ""
        var taboo3 = ""
        var id = 0
        var counter=0
        for(celebrity in celebrityData)
        {
            if(celebrityName.text.toString().capitalize() == celebrity.name){
                id = celebrity.pk
                celebrityName1 = celebrity.name
                taboo1 = celebrity.taboo1
                taboo2 = celebrity.taboo2
                taboo3 = celebrity.taboo3
                Log.d("TAG", "dataCELE: $taboo1")


                val intent = Intent(applicationContext, update_delete::class.java)
                intent.putExtra("pk", id)
                intent.putExtra("celebrityName", celebrityName1)
                intent.putExtra("taboo1", taboo1)
                intent.putExtra("taboo2", taboo2)
                intent.putExtra("taboo3", taboo3)
                startActivity(intent)
                break
            }
            else{
                counter++
            }
            if(counter==celebrityData.size){
                Toast.makeText(this, "${celebrityName.text.toString().capitalize()} not found", Toast.LENGTH_LONG).show()
            }

        }    }


}