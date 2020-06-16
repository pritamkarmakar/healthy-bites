package com.healthy.bites.landingscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.landingscreen.R
import com.healthy.bites.landingscreen.databinding.IndividualArticleDetailsBinding
import com.healthy.bites.landingscreen.navigator.Navigator
import com.healthy.bites.landingscreen.utils.STATIC_DOMAIN
import com.healthy.bites.models.Article

/**
 * Recycler view adapater contract
 */
abstract class HealthyBitesAdapter :
    RecyclerView.Adapter<HealthyBitesAdapterImpl.ArticleViewHolder>() {
    abstract fun setData(article: List<Article>)
}

/**
 * Recycler view adapater contract implementation
 */
class HealthyBitesAdapterImpl(
    private val dateParser: DateParser,
    private val navigator: Navigator,
    private val imageDownloader: ImageDownloader
) : HealthyBitesAdapter() {
    private val result: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.individual_article_details, parent, false)
        val binding = DataBindingUtil.bind<IndividualArticleDetailsBinding>(view)
        val viewHolder = ArticleViewHolder(view)
        binding?.holder = viewHolder
        return viewHolder
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleData = result[position]
        holder.run {
            title.text = articleData.headline.main
            snippet.text = articleData.snippet
            pubDate.text = dateParser.parseDate(articleData.pubDate)
        }
        // set visibility of the imageview to gone if there is no image available for this article
        val imageView = holder.image
        if (articleData.multimedia.isNotEmpty()) {
            val imageUrl = STATIC_DOMAIN + articleData.multimedia[0].url
            imageDownloader.loadImage(imageUrl, holder.image)
        } else {
            imageView.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            navigator.launchArticleDetails(articleData.webUrl)
        }
    }

    override fun setData(article: List<Article>) {
        this.result.addAll(article)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.article_image)
        val title: TextView = itemView.findViewById(R.id.article_title)
        val snippet: TextView = itemView.findViewById(R.id.article_snippet)
        val pubDate: TextView = itemView.findViewById(R.id.article_pub_date)
    }
}