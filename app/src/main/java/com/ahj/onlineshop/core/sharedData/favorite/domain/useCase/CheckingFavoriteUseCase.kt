package com.ahj.onlineshop.core.sharedData.favorite.domain.useCase

import com.ahj.onlineshop.core.sharedData.favorite.domain.repository.FavoriteRepository
import javax.inject.Inject


class CheckingFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

   operator fun invoke(id: Int) =
        repository.isFavorite(id)


}