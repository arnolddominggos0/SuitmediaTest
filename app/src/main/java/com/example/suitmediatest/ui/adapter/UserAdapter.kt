package com.example.suitmediatest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ItemUserBinding
import com.example.suitmediatest.model.User
import com.example.suitmediatest.network.UserApi

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val data = mutableListOf<User>()

    fun updateData(newData: List<User>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            tvEmail.text = user.email
            tvFirstName.text = user.first_name
            tvLastName.text = user.last_name
            Glide.with(ivUser.context)
                .load(UserApi.getUserUrl(user.avatar))
                .placeholder(R.drawable.baseline_account_circle_24)
                .error(R.drawable.baseline_broken_image_24)
                .into(ivUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
