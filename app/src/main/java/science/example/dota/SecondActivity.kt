package science.example.dota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import science.example.dota.api.ApiInterface.Companion.BASE_IMAGE
import science.example.dota.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var imageViewDetails: ImageView
    private lateinit var textNameDetails: TextView
    private lateinit var textNicknameDetails: TextView
    private lateinit var imageIcon: ImageView
    private lateinit var attack: TextView
    private lateinit var primaryattr: TextView
    private lateinit var basestr: TextView
    private lateinit var baseage: TextView
    private lateinit var baseint: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = intent.getStringExtra("img")
        val localizedName = intent.getStringExtra("localizedName")
        val name = intent.getStringExtra("name")
        val icon = intent.getStringExtra("icon")
        val attackType = intent.getStringExtra("attackType")
        val primaryAttr = intent.getStringExtra("primaryAttr")
        val baseStr = intent.getStringExtra("baseStr")
        val baseAgi = intent.getStringExtra("baseAgi")
        val baseInt = intent.getStringExtra("baseInt")

        imageViewDetails = findViewById(R.id.image_superheroes_detail)
        textNameDetails = findViewById(R.id.text_name)
        textNicknameDetails = findViewById(R.id.text_nickname)
        imageIcon = findViewById(R.id.image_icon)
        attack = findViewById(R.id.text_attack_type)
        primaryattr = findViewById(R.id.text_primary_attr)
        basestr = findViewById(R.id.text_base_str)
        baseage = findViewById(R.id.text_base_age)
        baseint = findViewById(R.id.text_base_int)

        textNameDetails.text = localizedName
        textNicknameDetails.text = name
        imageViewDetails.load(BASE_IMAGE + img)
        imageIcon.load(BASE_IMAGE + icon){
            transformations(CircleCropTransformation())
        }
        attack.text = attackType
        primaryattr.text = primaryAttr
        basestr.text = baseStr
        baseage.text = baseAgi
        baseint.text = baseInt

    }
}
