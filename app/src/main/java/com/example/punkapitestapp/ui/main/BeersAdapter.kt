package com.example.punkapitestapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.BeersItem
import com.example.punkapitestapp.R
import com.example.punkapitestapp.databinding.BeersItemBinding
import com.example.punkapitestapp.ui.common.inflate
import com.example.punkapitestapp.ui.common.loadImage

class BeersAdapter(private val clickListener: (BeersItem) -> Unit) :
    PagingDataAdapter<BeersItem, BeersAdapter.ViewHolder>(DiffutilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.beers_item, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = getItem(position)
        if (beer != null) {
            holder.bind(beer)
        }
        holder.itemView.setOnClickListener { clickListener(beer!!) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = BeersItemBinding.bind(view)

        fun bind(mediaAccount: BeersItem) {

            binding.apply {
                beerName.text = mediaAccount.name
                alcoolVolume.text = mediaAccount.abv.toString()
                year.text = mediaAccount.first_brewed
            }


            if(mediaAccount.image_url.equals("https://images.punkapi.com/v2/keg.png"))
                binding.imageBeer.setImageResource(R.drawable.beer_list_ic)
            else
                binding.imageBeer.loadImage(mediaAccount.image_url)

        }
    }

}


private object DiffutilCallback: DiffUtil.ItemCallback<BeersItem>(){
    override fun areItemsTheSame(oldItem: BeersItem, newItem: BeersItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BeersItem, newItem: BeersItem): Boolean =
        oldItem == newItem
}
