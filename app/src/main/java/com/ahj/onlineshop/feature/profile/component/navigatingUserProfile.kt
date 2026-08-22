package com.ahj.onlineshop.feature.profile.component

import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens
import com.ahj.onlineshop.feature.profile.domain.model.CategoryProfile



fun navigatingUserProfile(
    navController: NavController,
    selected: Int,

){


        when(selected){
            0-> {
                navController.navigate(Screens.ShoppingExperience)
            }
            1-> {
                navController.navigate(Screens.FavoriteScreen)
            }
        }


}
