package com.macv.category

import android.view.View
import com.macv.base.BaseRecyclerAdapter
import com.macv.mwheel.R


class CategoryAdapter : BaseRecyclerAdapter<CategoryItem>() {
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

    override fun areItemsSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun getLayoutIdForFooter(viewType: Int): Int = R.layout.category_footer

    override fun getFooterItem(): CategoryItem = CategoryItem()

    override fun isPositionFooter(position: Int): Boolean = arrayList[position].id == 0

    override fun isFooterItemLoading(): Boolean = arrayList.lastOrNull()?.id == 0

    fun setFooterListener(footerDismissListener: FooterDismissListener){
        mFooterDismissListener = footerDismissListener
    }
}