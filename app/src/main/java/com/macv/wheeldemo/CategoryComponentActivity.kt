package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.category.CategoryItem
import com.macv.category.FooterDismissListener
import com.macv.wheeldemo.databinding.ActivityCategoryCompoenentBinding
import kotlinx.android.synthetic.main.activity_category_compoenent.*

class CategoryComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivityCategoryCompoenentBinding= DataBindingUtil.setContentView(this, R.layout.activity_category_compoenent)
        category.setDynamicList(getCategoryList())
        category.setFooterCloseListener(object : FooterDismissListener{
            override fun onClose() {
                Toast.makeText(this@CategoryComponentActivity, "Footer close", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getCategoryList(): ArrayList<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        list.add(CategoryItem(id = 1, emoji = "https://dl.dropboxusercontent.com/s/grmetj2iixvz253/Group%201320.png", name = "Restaurant", avgPercent = "26%", yourPercent = "36%", monthlyOverSpendDollar = "76"))
        list.add(CategoryItem(id = 2, emoji = "https://dl.dropboxusercontent.com/s/rx8rh1uxhxrawx9/Group%201669.png", name = "Clothing", avgPercent = "27%", yourPercent = "37%", monthlyOverSpendDollar = "76"))
        list.add(CategoryItem(id = 3, emoji = "https://dl.dropboxusercontent.com/s/igoyutvhcf5hgdr/Group%201670.png", name = "Subscription", avgPercent = "26%", yourPercent = "38%", monthlyOverSpendDollar = "76"))

        return list
    }
}