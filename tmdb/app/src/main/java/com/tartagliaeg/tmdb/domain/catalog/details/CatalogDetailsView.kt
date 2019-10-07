package com.tartagliaeg.tmdb.domain.catalog.details

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.ViewFlipper
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.Cast
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.listing.CatalogListingView
import com.tartagliaeg.tmdb.tools_android.ImageTools
import kotlinx.android.synthetic.main.dmcatalog_view_details_screen.view.*

class CatalogDetailsView : RelativeLayout, CatalogDetailsViewContract {
    companion object {
        const val LOADING = 1
        const val CONTENT = 0
        const val FAILURE = 2
    }

    private val selfView: View = inflate(context, R.layout.dmcatalog_view_details_screen, this)
    private val rvCasting: RecyclerView = dmcatalog_details_rv_movie_cast
    private val tvTitle: AppCompatTextView = dmcatalog_details_tv_title
    private val tvYear: AppCompatTextView = dmcatalog_details_tv_year
    private val tvDuration: AppCompatTextView = dmcatalog_details_tv_duration
    private val tvGenres: AppCompatTextView = dmcatalog_details_tv_genres
    private val ivBackdrop: AppCompatImageView = dmcatalog_details_iv_backdrop
    private val tvOverview: AppCompatTextView = dmcatalog_details_tv_overview
    private val vfLazyContent: ViewFlipper = dmcatalog_details_vf_lazy_content

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override lateinit var module: CatalogDetailsModuleContract

    override fun start(id: Int) {
        if (rvCasting.adapter != null)
            return


        val cardCount = module.dimensionTools.itemsToFitInScreen(CatalogListingView.MAX_MOVIE_WIDTH)
        val layoutManager = GridLayoutManager(context, 1)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL
        rvCasting.layoutManager = layoutManager
        rvCasting.adapter = CastContentAdapter(context, module.imageTools)

        module.presenter.didRequestLoadDetails(
            id,
            MovieImages(
                movieBackdropSize = module.dimensionTools.widthInPx(),
                castPosterSize = (module.dimensionTools.widthInPx() / cardCount).toInt()
            )
        )
    }

    override fun stop() {
        module.presenter.didRequestLoadCancellation()
    }

    override fun showDetailsContent(movie: Movie) {
        vfLazyContent.displayedChild = CONTENT
        (rvCasting.adapter as CastContentAdapter).cast = movie.cast
        tvTitle.text = movie.title
        tvGenres.text = movie.genresAsText()
        tvYear.text = movie.year()
        tvDuration.text = "${(movie.duration)}m"
        tvOverview.text = movie.overview

        if (movie.backdropPath != null)
            module.imageTools.loadImageFromUrl(movie.backdropPath, ivBackdrop)
    }

    override fun showDetailsLoading() {
        vfLazyContent.displayedChild = LOADING
    }

    override fun showDetailsFailure(message: String) {
        vfLazyContent.displayedChild = FAILURE
    }
}


internal class CastItemViewHolder(val view: View, val tools: ImageTools) :
    RecyclerView.ViewHolder(view) {
    private var ivBanner: AppCompatImageView =
        view.findViewById(R.id.dmcatalog_iv_catalog_item_banner)
    private var tvHeader: AppCompatTextView = view.findViewById(R.id.dmcatalog_tv_card_header)
    private var tvName: AppCompatTextView = view.findViewById(R.id.dmcatalog_tv_card_name)

    fun bind(cast: Cast): CastItemViewHolder {
        tvName.text = ""
        tvName.visibility = View.GONE
        tvHeader.text = cast.name

        if (cast.profilePath != null)
            tools.loadImageFromUrl(cast.profilePath, ivBanner)
        else
            ivBanner.setImageDrawable(null)

        return this
    }
}

internal class CastContentAdapter(private val context: Context, val tools: ImageTools) :
    RecyclerView.Adapter<CastItemViewHolder>() {

    var cast: List<Cast> = ArrayList()
        set(value) {
            // check for reference
            if (field === value)
                return

            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.dmcatalog_view_catalog_item, parent, false)
        return CastItemViewHolder(view, tools)
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    override fun onBindViewHolder(holder: CastItemViewHolder, position: Int) {
        holder.bind(cast[position])
    }
}

