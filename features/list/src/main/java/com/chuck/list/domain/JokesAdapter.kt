package com.chuck.list.domain

import com.chuck.core.R
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.chuck.list.databinding.ItemJokeBinding
import com.chuck.list.domain.entities.Joke


class JokesAdapter(private val onCLick: (joke: Joke) -> Unit): RecyclerView.Adapter<JokesViewHolder>() {
    var items = mutableListOf<Joke>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(items[position])
        holder.onClick = onCLick
    }
}

class JokesViewHolder(private val binding: ItemJokeBinding, private val context: Context): RecyclerView.ViewHolder(binding.root) {

    var onClick: ((joke: Joke) -> Unit)? = null

    fun bind(joke: Joke) {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        binding.joke.text = joke.joke

        val errorDrawable = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_placeholder, null)
        val newErrorDrawable = DrawableCompat.wrap(errorDrawable!!)
        newErrorDrawable.setTint(getThemeAccentColor(context))

        Glide.with(context)
            .load(joke.img)
            .apply(
                RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(errorDrawable)
                    .fitCenter()
            )
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable?>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }


            })
            .into(binding.img)

        binding.root.setOnClickListener {
            onClick?.invoke(joke)
        }
    }

    private fun getThemeAccentColor(context: Context): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(android.R.attr.colorAccent, value, true)
        return value.data
    }
}