package com.ahj.onlineshop.feature.product.data.local.offlineData

import com.ahj.onlineshop.R
import com.ahj.onlineshop.feature.product.domain.model.BannerModel
import com.ahj.onlineshop.feature.product.domain.model.CategoryModel
import com.ahj.onlineshop.feature.product.domain.model.SubCategoryModel

object OfflineData {

    val category = listOf(
        CategoryModel(
            id = 1,
            title = "مردانه",
            image = R.drawable.category_man,
            categoryType = CategoryType.MAN
        ),
        CategoryModel(
            id = 2,
            title = "بچه گانه",
            image = R.drawable.category_baby,
            categoryType = CategoryType.NEWBORN
        ),
        CategoryModel(
            id = 3,
            title = "دخترانه",
            image = R.drawable.category_dughter,
            categoryType = CategoryType.GIRL
        ),
        CategoryModel(
            id = 4,
            title = "کفش",
            image = R.drawable.category_shoes,
            categoryType = CategoryType.SHOES
        ),
        CategoryModel(
            id = 5,
            title = "پسرانه",
            image = R.drawable.category_tshirt,
            categoryType = CategoryType.BOY
        ),
        CategoryModel(
            id = 6,
            title = "زنانه",
            image = R.drawable.category_woman,
            categoryType = CategoryType.WOMAN
        ),


        )

    val listBanner = listOf(
        BannerModel(
            id = 1,
            R.drawable.banner_sport
        ),
        BannerModel(
            id = 2,
            R.drawable.banner_sport
        )
    )

    val listSubCategory = listOf(
        SubCategoryModel(
            id = 1,
            title = "تی شرت",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_tshirt
        ),
        SubCategoryModel(
            id = 2,
            title = "رسمی",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_clothes
        ),
        SubCategoryModel(
            id = 3,
            title = "اسپرت",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_sport
        ),
        SubCategoryModel(
            id = 4,
            title = "جوراب",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_socks
        ),
        SubCategoryModel(
            id = 5,
            title = "کفش",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_shoes
        ),
        SubCategoryModel(
            id = 6,
            title = "سرهمی",
            parentCategory = CategoryType.NEWBORN,
            image = R.drawable.sub_newborn
        ),
        SubCategoryModel(
            id = 7,
            title = "کفش",
            parentCategory = CategoryType.NEWBORN,
            image = R.drawable.sub_newborn_shoes
        ),
        SubCategoryModel(
            id = 8,
            title = "رسمی",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_rasmi
        ),
        SubCategoryModel(
            id = 9,
            title = "مجلسی",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_tshirt
        ),

        SubCategoryModel(
            id = 10,
            title = "کفش دخترانه",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_shoes
        ),
        SubCategoryModel(
            id = 11,
            title = "کفش مردانه ",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_man_shoes
        ),
        SubCategoryModel(
            id = 12,
            title = "کفش زنانه",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_woman_shoes
        ),
        SubCategoryModel(
            id = 13,
            title = "کفش پسرانه",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_boy_shoes
        ),
        SubCategoryModel(
            id = 14,
            title = "کفش نوزادی",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_newborn_shoes
        ),
        SubCategoryModel(
            id = 15,
            title = "تی شرت",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_tshirt
        ),
        SubCategoryModel(
            id = 16,
            title = "رسمی",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_rasmi
        ),
        SubCategoryModel(
            id = 17,
            title = "کفش",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_shoes
        ),
        SubCategoryModel(
            id = 18,
            title = "کفش",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_shoes
        ),
        SubCategoryModel(
            id = 19,
            title = "مجلسی",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_rasmi
        ),
        SubCategoryModel(
            id = 20,
            title = "خانگی",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_clothes
        ),


        )


}

object CategoryType{
    const val MAN = "man"
    const val WOMAN = "woman"
    const val GIRL = "girl"
    const val BOY = "boy"
    const val SHOES = "shoes"
    const val NEWBORN = "newborn"
}