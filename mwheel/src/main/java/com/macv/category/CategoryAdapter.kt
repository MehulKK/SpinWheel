package com.macv.category

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.ViewDataBinding
import com.macv.base.BaseRecyclerAdapter
import com.macv.mwheel.R
import com.macv.mwheel.databinding.ListItemBinding


class CategoryAdapter(private val mContext : Context) : BaseRecyclerAdapter<CategoryItem>() {
    private lateinit var mFooterDismissListener: FooterDismissListener
    override fun getLayoutIdForType(viewType: Int) = R.layout.list_item

    override fun onItemClick(view: View?, adapterPosition: Int) {

        when(view?.id){
            R.id.btnClose -> {
                mFooterDismissListener.onClose()
                removeFooter()
            }
        }
    }

    override fun setDataForListItem(binding: ViewDataBinding, dataModel: CategoryItem) {
        super.setDataForListItem(binding, dataModel)
        if(binding is ListItemBinding) {
            val listItemBinding: ListItemBinding = binding
            val unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.ic_category_image_placeholder)
            unwrappedDrawable?.let {
                val wrappedDrawable: Drawable = DrawableCompat.wrap(unwrappedDrawable)
                DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, dataModel.iconBackgroundColor))
                listItemBinding.imgCategory.background = wrappedDrawable
            }
            listItemBinding.txtAvg.setTextColor(ContextCompat.getColor(mContext,dataModel.avgTextColor))
            listItemBinding.txtYou.setTextColor(ContextCompat.getColor(mContext,dataModel.youTextColor))
        }
    }

    override fun areItemsSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun getLayoutIdForFooter(viewType: Int): Int = R.layout.category_footer

    override fun getLayoutIdForHeader(viewType: Int): Int = R.layout.category_header

    override fun getFooterItem(): CategoryItem = CategoryItem()

    override fun getHeaderItem(): CategoryItem = CategoryItem(id = -1)

    override fun isPositionFooter(position: Int): Boolean = arrayList[position].id == 0

    override fun isPositionHeader(position: Int): Boolean = arrayList[position].id == -1

    override fun isFooterItemLoading(): Boolean = arrayList.lastOrNull()?.id == 0

    override fun isHeaderItemLoading(): Boolean = arrayList.lastOrNull()?.id == -1

    fun setFooterListener(footerDismissListener: FooterDismissListener){
        mFooterDismissListener = footerDismissListener
    }
}