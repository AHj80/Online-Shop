package com.ahj.onlineshop.core.common.notification

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ahj.onlineshop.core.common.permissionManager.PermissionManager
import com.ahj.onlineshop.core.common.ui.theme.BackgroundCircleColor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotificationConfig @Inject constructor(
    private val permissionManager: PermissionManager,
    @ApplicationContext private val context: Context
) : NotificationImpl {


    @SuppressLint("MissingPermission")
    override fun createNotification(id: Int, title: String, desc: String, channelId: String) {


        if (!permissionManager.isGranted(Manifest.permission.POST_NOTIFICATIONS)){

            return
        }

        val notification = NotificationCompat.Builder(context, channelId )
            .setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(
                android.R.drawable.ic_menu_info_details
            )
            .setAutoCancel(true)
            .setColor(BackgroundCircleColor.toArgb())
            .build()

        NotificationManagerCompat.from(context).notify(id , notification)
    }


}