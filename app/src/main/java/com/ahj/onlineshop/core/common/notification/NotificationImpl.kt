package com.ahj.onlineshop.core.common.notification



interface NotificationImpl {

    fun createNotification(id: Int, title: String , desc: String, channelId: String)
}