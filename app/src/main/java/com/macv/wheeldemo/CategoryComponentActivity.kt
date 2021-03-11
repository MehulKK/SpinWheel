package com.macv.wheeldemo

import android.graphics.Color
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
        list.add(CategoryItem(id = 1, emoji = "\uD83C\uDFC6",
                iconBackgroundColor = R.color.colorSecondary,
                name = "Restaurant",
                amount = "320.19",
                avgPercent = "26%",
                yourPercent = "36%",
                avgTextColor = R.color.textPrimary,
                youTextColor = R.color.item_text_you,
                monthlyOverSpendDollar = "76"))
        list.add(CategoryItem(id = 2, emoji = "\uD83C\uDFC6",
                iconBackgroundColor = R.color.colorPrimaryDarker,
                name = "Clothing",
                amount = "30.19",
                avgPercent = "27%",
                yourPercent = "37%",
                avgTextColor = R.color.item_text_you,
                youTextColor = R.color.textPrimary,
                monthlyOverSpendDollar = "76"))
        list.add(CategoryItem(id = 3, emoji = "\uD83C\uDFC6",
                iconBackgroundColor = R.color.category_image_bg,
                name = "Subscription",
                amount = "20.19",
                avgPercent = "26%",
                yourPercent = "38%",
                avgTextColor = R.color.textPrimary,
                youTextColor = R.color.item_text_you,
                monthlyOverSpendDollar = "76"))

        return list
    }
}