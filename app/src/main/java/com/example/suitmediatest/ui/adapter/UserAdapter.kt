package com.example.suitmediatest.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ItemUserBinding
import com.example.suitmediatest.model.User
import com.example.suitmediatest.network.UserApi
import com.example.suitmediatest.ui.secondscreen.SecondScreenActivity

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val data = mutableListOf<User>()

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

            root.setOnClickListener {
                val fullName = "${user.first_name} ${user.last_name}"
                val detailIntent = Intent(root.context, SecondScreenActivity::class.java)
                detailIntent.putExtra(SecondScreenActivity.FULLNAME, fullName)
                root.context.startActivity(
                    detailIntent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(root.context as Activity)
                        .toBundle()
                )
            }
        }
    }

    fun updateData(newData: List<User>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return data.size
            }

            override fun getNewListSize(): Int {
                return newData.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return data[oldItemPosition].id == newData[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return data[oldItemPosition] == newData[newItemPosition]
            }
        })

        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}
