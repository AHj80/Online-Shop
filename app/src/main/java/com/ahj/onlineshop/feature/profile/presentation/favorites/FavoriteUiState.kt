package com.ahj.onlineshop.feature.profile.presentation.favorites

import android.net.Uri
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel
import com.ahj.onlineshop.feature.profile.domain.model.ProfileModel

data class FavoriteUiState(
    val status: FavoriteStatus = FavoriteStatus.EMPTY,
    val data : List<FavoriteModel> = emptyList(),
    val message: String? = null,
    val avatar : Uri? = null,
    val profile : ProfileModel? = null
)