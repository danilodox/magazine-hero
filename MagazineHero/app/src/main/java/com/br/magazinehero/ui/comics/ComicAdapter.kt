package com.br.magazinehero.ui.comics


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.magazinehero.data.model.Comic
import com.br.magazinehero.databinding.ItemComicBinding
import com.br.magazinehero.util.loadImage

class ComicAdapter (private val comics: List<Comic>,
                    private val onItemClick:((comic : Comic) -> Unit)): RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicAdapter.ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemComicBinding.inflate(inflater, parent, false)
        return ComicViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ComicAdapter.ComicViewHolder, position: Int) {
        holder.bindView(comics[position])
    }

    override fun getItemCount(): Int {

        return comics.count()
    }

    inner class ComicViewHolder(private val binding : ItemComicBinding, private val onItemClick: (comic : Comic) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bindView(comic : Comic) {

            binding.comicPosterImageView.loadImage(comic.thumbnail.path, comic.thumbnail.extension)
            binding.titleTextView.text = comic.title



            binding.comicDetailsRare.text = comic.isRare


            itemView.setOnClickListener{
                onItemClick.invoke(comic)
            }

            }
        }

    }





