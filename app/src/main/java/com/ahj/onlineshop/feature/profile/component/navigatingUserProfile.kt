package com.ahj.onlineshop.feature.profile.component

import androidx.navigation.NavController
import com.ahj.onlineshop.app.navigation.Screens


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
            2->{
                navController.navigate(Screens.ProfileAddress)
            }
            3->{
                navController.navigate(Screens.ChangePassword)
            }
            4->{
                navController.navigate(Screens.UserOrderScreen)
            }
            5->{
                navController.navigate(Screens.Notification)
            }

        }


}
