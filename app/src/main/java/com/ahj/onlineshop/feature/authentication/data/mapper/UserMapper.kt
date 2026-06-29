package com.ahj.onlineshop.feature.authentication.data.mapper

import com.ahj.onlineshop.feature.authentication.data.remote.dto.LoginDto
import com.ahj.onlineshop.feature.authentication.data.remote.dto.RegisterDto
import com.ahj.onlineshop.feature.authentication.domain.model.LoginModel
import com.ahj.onlineshop.feature.authentication.domain.model.RegisterModel


fun LoginDto.toDomain(): LoginModel =
    LoginModel(
        id = this.id,
        email = this.email,
        password = this.password
    )

fun RegisterModel.toDto(): RegisterDto =
    RegisterDto(
        id = this.id,
        email = this.email,
        password = this.password,
        username = this.username
    )

fun RegisterDto.toDomain(): RegisterModel =
    RegisterModel(
        id = this.id,
        email = this.email,
        password = this.password,
        username =this.username
    )
