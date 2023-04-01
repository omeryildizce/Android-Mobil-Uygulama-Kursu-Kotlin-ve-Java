package com.omeryildizce.instagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omeryildizce.instagramclone.databinding.RecyclerRowBinding
import com.omeryildizce.instagramclone.model.Post
import com.squareup.picasso.Picasso

class FeedAdapter(val postArrayList: ArrayList<Post>) : RecyclerView.Adapter<FeedAdapter.FeedHolder>() {
    class FeedHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedHolder(binding)

    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }


    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        val username = postArrayList.get(position).username
        val comment = postArrayList.get(position).comment
        val url = postArrayList.get(position).downloadUrl
        holder.binding.tvUsername.text = username
        holder.binding.tvCommentUsername.text = username
        holder.binding.tvCommentText.text = comment

        Picasso.get().load(url).into(holder.binding.ivPostImage)

    }
}