package com.ahj.onlineshop.feature.product.presentation.detailProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahj.onlineshop.core.sharedData.favorite.domain.model.FavoriteModel
import com.ahj.onlineshop.core.sharedData.favorite.domain.useCase.AddFavoriteUseCase
import com.ahj.onlineshop.core.sharedData.favorite.domain.useCase.CheckingFavoriteUseCase
import com.ahj.onlineshop.core.sharedData.favorite.domain.useCase.DeleteFavoriteUseCase
import com.ahj.onlineshop.core.sharedData.product.domain.useCase.AddToCartUseCase
import com.ahj.onlineshop.core.sharedData.product.domain.useCase.GetCartDataByIdUseCase
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.model.UserExperienceModel
import com.ahj.onlineshop.core.sharedData.shoppingExperience.domain.useCase.AddExperienceUseCase
import com.ahj.onlineshop.feature.product.domain.model.ProductModel
import com.ahj.onlineshop.feature.product.domain.usecase.GetDetailProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val getDetailProductUseCase: GetDetailProductUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getCartDataByIdUseCase: GetCartDataByIdUseCase,
    private val checkingFavoriteUseCase: CheckingFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val addExperienceUseCase: AddExperienceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailProductUiState())
    val uiState: StateFlow<DetailProductUiState> = _uiState.asStateFlow()

    fun getProduct(id: String, categoryType: String) {

        viewModelScope.launch {
            checkingCartStatus(id)
            _uiState.update { it.copy(status = DetailProductStatus.LOADING, messageStatus = null) }
            getDetailProductUseCase(id, categoryType)
                .onSuccess { data ->
                    _uiState.update {
                        it.copy(
                            product = data.productData,
                            similarProduct = data.similarData,
                            status = DetailProductStatus.SUCCESS
                        )
                    }
                    isFavorite(data.productData.id.toInt())

                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            status = DetailProductStatus.ERROR,
                            messageStatus = error.message,
                        )
                    }
                }
        }


    }

    private fun checkingCartStatus(id: String) {

        viewModelScope.launch {
            getCartDataByIdUseCase(id).collect { inCart ->
                _uiState.update {
                    it.copy(
                        inCart = inCart
                    )
                }

            }
        }
    }

    fun addToCart(product: ProductModel) {
        viewModelScope.launch {
            addToCartUseCase(product)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            message = "محصول به سبد خرید اضافه گردید",
                            inCart = true
                        )
                    }

                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }

                }

        }
    }


    private fun changeTab(index: Int) = _uiState.update { it.copy(selectedTab = index) }

    private fun isFavorite(id: Int) {
        viewModelScope.launch {
            checkingFavoriteUseCase(id).collect { result ->
                result
                    .onSuccess { result ->
                        _uiState.update { it.copy(isFavorite = result) }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(message = error.message) }
                    }
            }

        }
    }

    private fun addFavorite(
        statusFavorite: Boolean,
        id: Int,
        categoryType: String,
        title: String,
        image: String
    ) {

        viewModelScope.launch {
            if (statusFavorite) {
                deleteFavoriteUseCase(FavoriteModel(id, categoryType, image, title))
                    .onSuccess {
                        _uiState.update { it.copy(message = "از لیست علاقه مندی ها حذف گردید") }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(message = error.message) }
                    }

            } else {
                addFavoriteUseCase(FavoriteModel(id, categoryType, image, title))
                    .onSuccess {
                        _uiState.update { it.copy(message = "به لیست علاقه مندی ها اضافه گردید") }
                    }
                    .onFailure { error ->
                        _uiState.update { it.copy(message = error.message) }
                    }
            }
        }

    }

    private fun sendComment(id: Int, title: String, image: String, comment: String, rate: Int) {

        viewModelScope.launch {

            addExperienceUseCase(UserExperienceModel(id, title, image, comment, rate))
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            stateTextComment = "",
                            message = "نظر شما با موفقیت ارسال و پس از تایید نمایش داده خواهد شد"
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(message = error.message) }
                }


        }

    }

    private fun changeRate(rate: Int) = _uiState.update { it.copy(rate = rate) }

    private fun changeStateComment(text: String) =
        _uiState.update { it.copy(stateTextComment = text) }

    fun resetMessage() = _uiState.update { it.copy(message = null) }

    fun onEvent(event: DetailProductUiEvent) {
        when (event) {
            is DetailProductUiEvent.OnAddToCart -> {
                addToCart(event.product)
            }

            is DetailProductUiEvent.OnSendComment -> {
                sendComment(
                    event.id,
                    event.title,
                    event.image,
                    event.text,
                    event.rate,
                )
            }

            is DetailProductUiEvent.OnRateChange -> {
                changeRate(event.rate)
            }

            is DetailProductUiEvent.OnCommentChange -> {
                changeStateComment(event.text)
            }

            is DetailProductUiEvent.FavoriteOnClick -> {
                addFavorite(
                    event.isFavorite,
                    event.productId,
                    event.productCategoryType,
                    event.productTitle,
                    event.productImage
                )
            }

            is DetailProductUiEvent.OnTabChange -> {
                changeTab(event.index)
            }

            else -> {}
        }
    }

}
