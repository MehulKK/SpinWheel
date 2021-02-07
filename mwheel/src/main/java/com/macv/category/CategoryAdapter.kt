package com.macv.category

import android.view.View
import com.macv.base.BaseRecyclerAdapter
import com.macv.mwheel.R


class CategoryAdapter(/*val mAdapterItemClickListener: AdapterItemClick*/) : BaseRecyclerAdapter<CategoryItem>() {
    override fun getLayoutIdForType(viewType: Int) = R.layout.list_item

    override fun onItemClick(view: View?, adapterPosition: Int) {
        val categoryItem : CategoryItem = arrayList[adapterPosition]

        //view?.let { mAdapterItemClickListener.onAdapterItemClick(it, categoryItem, adapterPosition) }
    }

    override fun areItemsSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.id == newItem.id
    }
}