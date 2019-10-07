package com.tartagliaeg.tmdb.domain.catalog.listing

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ViewFlipper
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tartagliaeg.tmdb.R
import com.tartagliaeg.tmdb.domain.catalog.Movie
import com.tartagliaeg.tmdb.domain.catalog.MovieImages
import com.tartagliaeg.tmdb.domain.catalog.TMDBPage
import com.tartagliaeg.tmdb.tools_android.ImageTools
import kotlinx.android.synthetic.main.dmcatalog_view_listing_catalog.view.*

class CatalogListingView : LinearLayout, CatalogListingViewContract {
    companion object {
        const val LOADING = 1; const val CONTENT = 0; const val FAILURE = 2
        const val MAX_MOVIE_WIDTH = 130
    }

    private var order: Int = CatalogListingConstants.ORDER_BY_DATE
    private var page: Int = 1
    private val selfView: View = inflate(context, R.layout.dmcatalog_view_listing_catalog, this)
    private val rvContent: RecyclerView = dmcatalog_listing_rv_catalog
    private val vfLazyContent: ViewFlipper = dmcatalog_listing_vf_lazy_content
    private val lvLoading: View = dmcatalog_listing_lv_loading
    private val btUpcoming: AppCompatButton = dmcatalog_listing_bt_upcoming
    private val btPopular: AppCompatButton = dmcatalog_listing_bt_popular


    override lateinit var module: CatalogListingModuleContract

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {}

    init {
        orientation = VERTICAL
    }

    override fun start() {
        if(rvContent.adapter != null)
            return

        val cardCount = module.dimensionTools.itemsToFitInScreen(MAX_MOVIE_WIDTH)
        val layoutManager = GridLayoutManager(context, cardCount.toInt())

        layoutManager.orientation = GridLayoutManager.VERTICAL
        rvContent.layoutManager = layoutManager
        rvContent.adapter = CatalogContentAdapter(context, module.imageTools) { module.presenter.didTouchOnCatalogItem(it) }

        val mi =             MovieImages(
            moviePosterSize = (module.dimensionTools.widthInPx() / cardCount).toInt()
        )

        module.presenter.didRequestLoadCatalog(page,mi,order)

        btUpcoming.setOnClickListener { module.presenter.didRequestLoadCatalog(page, mi, CatalogListingConstants.ORDER_BY_DATE) }
        btPopular.setOnClickListener { module.presenter.didRequestLoadCatalog(page, mi, CatalogListingConstants.ORDER_BY_POPULARITY) }

    }

    override fun stop() {
        module.presenter.didRequestLoadCancellation()
    }

    override fun showCatalogsContent(state: CatalogListingViewState) {
        order = state.order

        if(order == CatalogListingConstants.ORDER_BY_POPULARITY) {
            btPopular.setBackgroundResource(R.drawable.btn_group_right_selected)
            btPopular.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryContrast))
            btUpcoming.setBackgroundResource(R.drawable.btn_group_left_neutral)
            btUpcoming.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        } else {
            btPopular.setBackgroundResource(R.drawable.btn_group_right_neutral)
            btPopular.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            btUpcoming.setBackgroundResource(R.drawable.btn_group_left_selected)
            btUpcoming.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryContrast))
        }

        vfLazyContent.displayedChild = CONTENT
        (rvContent.adapter as CatalogContentAdapter).catalog = state.catalog
    }

    override fun showCatalogsLoading() {
        vfLazyContent.displayedChild = LOADING
    }

    override fun showCatalogsFailure(message: String) {
        vfLazyContent.displayedChild = FAILURE
    }

    override fun onSaveInstanceState(): Parcelable? {
        val svd = SavedState(super.onSaveInstanceState())
        svd.order = this.order
        svd.page = this.page
        return svd
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val svd =  state as SavedState
        super.onRestoreInstanceState(state.superState)
        this.order = svd.order
        this.page = svd.page
    }
}

internal class CatalogItemViewHolder(val view: View, val tools: ImageTools) : RecyclerView.ViewHolder(view) {
    private var ivBanner: AppCompatImageView = view.findViewById(R.id.dmcatalog_iv_catalog_item_banner)
    private var tvDate: AppCompatTextView = view.findViewById(R.id.dmcatalog_tv_card_header)
    private var tvTitle: AppCompatTextView = view.findViewById(R.id.dmcatalog_tv_card_name)

    fun bind(movie: Movie, listener: View.OnClickListener): CatalogItemViewHolder {
        view.setOnClickListener (listener)

        tvTitle.text = movie.title
        tvDate.text = movie.releaseDate

        if (movie.posterPath != null)
            tools.loadImageFromUrl(movie.posterPath, ivBanner)
        else
            ivBanner.setImageDrawable(null)

        return this
    }
}

typealias ClickListener = (movie: Movie) -> Unit

internal class CatalogContentAdapter(private val context: Context, val tools: ImageTools, val listener: ClickListener) :
    RecyclerView.Adapter<CatalogItemViewHolder>() {

    var catalog: TMDBPage<Movie> = TMDBPage()
        set(value) {
            // check for reference
            if (field === value)
                return

            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dmcatalog_view_catalog_item, parent, false)
        return CatalogItemViewHolder(view, tools)
    }

    override fun getItemCount(): Int {
        return catalog.results.size
    }

    override fun onBindViewHolder(holder: CatalogItemViewHolder, position: Int) {
        holder.bind(
            catalog.results[position],
            View.OnClickListener { listener(catalog.results[position]) }
        )
    }
}

internal class SavedState : View.BaseSavedState {
    var page: Int = 1
    var order: Int = CatalogListingConstants.ORDER_BY_DATE

    constructor(superState: Parcelable?) : super(superState)
    constructor(source: Parcel) : super(source) {
        page = source.readInt()
        order = source.readInt()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(page)
        out.writeInt(order)
    }

    companion object {
        @JvmField
        @NonNull
        val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel): SavedState { return SavedState(source) }
            override fun newArray(size: Int): Array<SavedState?> { return arrayOfNulls(size) }
        }
    }
}
