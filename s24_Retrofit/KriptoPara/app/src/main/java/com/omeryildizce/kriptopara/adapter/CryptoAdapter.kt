package com.omeryildizce.kriptopara.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.omeryildizce.kriptopara.databinding.RowLayoutBinding
import com.omeryildizce.kriptopara.model.CryptoModel

class CryptoAdapter(val cryptoModelList: ArrayList<CryptoModel>,   val listener:Listener) :RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {
    private val colors = arrayOf("#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF", "#800000", "#008000", "#000080", "#808000", "#800080", "#008080", "#C0C0C0", "#808080", "#FFA07A", "#20B2AA", "#FFC0CB", "#7B68EE", "#F0E68C", "#DDA0DD")
    interface Listener{
        fun onItemCick(cryptoModel: CryptoModel)
    }
    class CryptoHolder(var binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(cryptoModel: CryptoModel, colors:Array<String> , position: Int, listener:Listener){
             binding.textViewName.text = cryptoModel.currency
             binding.textViewPrice.text = cryptoModel.price
             binding.linearLayout.setBackgroundColor(Color.parseColor(colors[position%20]))
            itemView.setOnClickListener {
                listener.onItemCick(cryptoModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoModelList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoModelList.get(position), colors, position,listener )

    }
}