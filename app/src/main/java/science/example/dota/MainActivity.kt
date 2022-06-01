package science.example.dota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import science.example.dota.adapter.MainAdapter
import science.example.dota.api.ApiInterface
import science.example.dota.data.PostsResponse
import science.example.dota.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()
        adapter.setOnItemClickListener(object : MainAdapter.onItemClickListener{
            override fun onItemClick(localizedName: String, name: String, img: String, icon: String,
                                     attackType: String, primaryAttr: String, baseStr: String,
                                     baseAgi: String, baseInt: String){
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("img", img)
                intent.putExtra("localizedName", localizedName)
                intent.putExtra("name", name)
                intent.putExtra("icon", icon)
                intent.putExtra("attackType", attackType)
                intent.putExtra("primaryAttr", primaryAttr)
                intent.putExtra("baseStr", baseStr)
                intent.putExtra("baseAgi", baseAgi)
                intent.putExtra("baseInt", baseInt)
                startActivity(intent)
            }


        })
        val apiInterface = ApiInterface.create().getSinglePost()
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        apiInterface.enqueue(object : Callback<List<PostsResponse>> {
            override fun onFailure(call: Call<List<PostsResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<PostsResponse>>, response: Response<List<PostsResponse>>) {
                if(response.body() != null)
                    adapter.setDotaListItems(response.body()!!)
            }

        })
    }
}