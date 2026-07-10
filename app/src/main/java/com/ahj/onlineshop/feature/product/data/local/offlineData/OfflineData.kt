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
            image = R.drawable.sub_man_tshirt,
            categoryType = ProductCategoryType.MAN_TSHIRT
        ),
        SubCategoryModel(
            id = 2,
            title = "بلوز و پیراهن",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_clothes,
            categoryType = ProductCategoryType.MAN_CLOTHES
        ),
        SubCategoryModel(
            id = 3,
            title = "اسپرت",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_sport,
            categoryType = ProductCategoryType.MAN_SPORT
        ),
        SubCategoryModel(
            id = 4,
            title = "جوراب",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_socks,
            categoryType = ProductCategoryType.MAN_SOCKS
        ),
        SubCategoryModel(
            id = 5,
            title = "کفش",
            parentCategory = CategoryType.MAN,
            image = R.drawable.sub_man_shoes,
            categoryType = ProductCategoryType.MAN_SHOES
        ),
        SubCategoryModel(
            id = 6,
            title = "سرهمی",
            parentCategory = CategoryType.NEWBORN,
            image = R.drawable.sub_newborn,
            categoryType = ProductCategoryType.NEWBORN
        ),
        SubCategoryModel(
            id = 7,
            title = "کفش",
            parentCategory = CategoryType.NEWBORN,
            image = R.drawable.sub_newborn_shoes,
            categoryType = ProductCategoryType.NEWBORN_SHOES
        ),
        SubCategoryModel(
            id = 8,
            title = "رسمی",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_rasmi,
            categoryType = ProductCategoryType.GIRL_RASMI
        ),
        SubCategoryModel(
            id = 9,
            title = "مجلسی",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_tshirt,
            categoryType = ProductCategoryType.GIRL_TSHIRT
        ),

        SubCategoryModel(
            id = 10,
            title = "کفش دخترانه",
            parentCategory = CategoryType.GIRL,
            image = R.drawable.sub_girl_shoes,
            categoryType = ProductCategoryType.GIRL_SHOES
        ),
        SubCategoryModel(
            id = 11,
            title = "کفش مردانه ",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_man_shoes,
            categoryType = ProductCategoryType.MAN_SHOES
        ),
        SubCategoryModel(
            id = 12,
            title = "کفش زنانه",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_woman_shoes,
            categoryType = ProductCategoryType.WOMAN_SHOES
        ),
        SubCategoryModel(
            id = 13,
            title = "کفش پسرانه",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_boy_shoes,
            categoryType = ProductCategoryType.BOY_SHOES
        ),
        SubCategoryModel(
            id = 14,
            title = "کفش نوزادی",
            parentCategory = CategoryType.SHOES,
            image = R.drawable.sub_newborn_shoes,
            categoryType = ProductCategoryType.NEWBORN_SHOES
        ),
        SubCategoryModel(
            id = 15,
            title = "تی شرت",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_tshirt,
            categoryType = ProductCategoryType.BOY_TSHIRT
        ),
        SubCategoryModel(
            id = 16,
            title = "رسمی",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_rasmi,
            categoryType = ProductCategoryType.BOY_RASMI
        ),
        SubCategoryModel(
            id = 17,
            title = "کفش",
            parentCategory = CategoryType.BOY,
            image = R.drawable.sub_boy_shoes,
            categoryType = ProductCategoryType.BOY_SHOES
        ),
        SubCategoryModel(
            id = 18,
            title = "کفش",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_shoes,
            categoryType = ProductCategoryType.WOMAN_SHOES
        ),
        SubCategoryModel(
            id = 19,
            title = "مجلسی",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_rasmi,
            categoryType = ProductCategoryType.WOMAN_RASMI
        ),
        SubCategoryModel(
            id = 20,
            title = "خانگی",
            parentCategory = CategoryType.WOMAN,
            image = R.drawable.sub_woman_clothes,
            categoryType = ProductCategoryType.WOMAN_CLOTHES
        ),


        )


}

object CategoryType {
    const val MAN = "man"
    const val WOMAN = "woman"
    const val GIRL = "girl"
    const val BOY = "boy"
    const val SHOES = "shoes"
    const val NEWBORN = "newborn"
}

object ProductCategoryType {

    const val MAN_TSHIRT = "man_tshirt"

    const val MAN_CLOTHES = "man_clothes"

    const val MAN_SPORT = "man_sport"

    const val MAN_SOCKS = "man_socks"

    const val MAN_SHOES = "man_shoes"

    const val NEWBORN = "newborn"

    const val NEWBORN_SHOES = "newborn_shoes"

    const val GIRL_RASMI = "girl_rasmi"

    const val GIRL_TSHIRT = "girl_tshirt"

    const val GIRL_SHOES = "girl_shoes"

    const val WOMAN_SHOES = "woman_shoes"

    const val BOY_SHOES = "boy_shoes"

    const val BOY_TSHIRT = "boy_tshirt"

    const val BOY_RASMI = "boy_rasmi"

    const val WOMAN_RASMI = "woman_rasmi"

    const val WOMAN_CLOTHES = "woman_clothes"
}