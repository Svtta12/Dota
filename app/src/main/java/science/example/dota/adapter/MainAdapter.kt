package science.example.dota.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import science.example.dota.R
import science.example.dota.api.ApiInterface.Companion.BASE_IMAGE
import science.example.dota.data.PostsResponse
import science.example.dota.databinding.ItemActivityBinding

class MainAdapter(): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var mListener: onItemClickListener
    var dotaList : List<PostsResponse> = listOf()


    interface onItemClickListener {
        fun onItemClick(localizedName: String, name: String, img: String, icon: String, attackType: String,
        primaryAttr: String, baseStr: String, baseAgi: String, baseInt: String)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemActivityBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
         val dota = dotaList[position]
        with(holder.binding) {
            holder.itemView.tag = dota
            textName.text = dota.name
            textLocoName.text = dota.localizedName
            imageHero.load(BASE_IMAGE + dota.img) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun getItemCount(): Int = dotaList.size

     inner class MainViewHolder (val binding: ItemActivityBinding, listener: onItemClickListener)
        : RecyclerView.ViewHolder(binding.root) {
         init {
             itemView.setOnClickListener {
                 dotaList?.get(position)?.let {
                         mListener.onItemClick(it.localizedName, it.name, it.img, it.icon,
                             it.attackType, it.primaryAttr, it.baseStr, it.baseAgi, it.baseInt)
                     }
                 }
             }
         }


    @SuppressLint("NotifyDataSetChanged")
    fun setDotaListItems(superDotaList: List<PostsResponse>){
        this.dotaList = superDotaList
        notifyDataSetChanged()
    }

}
