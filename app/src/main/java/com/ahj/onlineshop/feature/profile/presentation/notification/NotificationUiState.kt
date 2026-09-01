package com.ahj.onlineshop.feature.profile.presentation.notification

import com.ahj.onlineshop.feature.profile.domain.model.HeaderDataModel


data class NotificationUiState(
    val status: NotificationStatus = NotificationStatus.EMPTY,
    val message: String? = null,
    val header: HeaderDataModel? = null
)
