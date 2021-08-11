package jay.nehete.newsnexus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.article_block.view.*

class ListAdapter(private val listener: ItemClicked): RecyclerView.Adapter<ArticleViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_block,parent,false)
        val viewHolder = ArticleViewHolder(view)
        view.setOnClickListener {
            listener.onClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val curr = items[position]
        holder.title.text = curr.title
        holder.author.text = curr.author
        Glide.with(holder.itemView.context).load(curr.imageToUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updateNews: ArrayList<News>) {
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }
}

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.tvTitle
    val image:ImageView = itemView.ivNewsImg
    val author:TextView = itemView.tvAuthor
}

interface ItemClicked {
    fun onClick(item: News)
}