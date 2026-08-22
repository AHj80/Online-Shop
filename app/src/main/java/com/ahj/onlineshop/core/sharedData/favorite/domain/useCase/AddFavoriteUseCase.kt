package com.ahj.onlineshop.core.sharedData.favorite.domain.useCase

import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import com.ahj.onlineshop.core.sharedData.favorite.domain.repository.FavoriteRepository
import javax.inject.Inject


class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(favorite: FavoriteModel) = repository.addFavorite(favorite)

}