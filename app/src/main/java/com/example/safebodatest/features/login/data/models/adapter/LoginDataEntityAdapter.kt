package com.example.safebodatest.features.login.data.models.adapter

import com.example.safebodatest.core.model_templates.IDataModel
import com.example.safebodatest.features.login.data.models.LoginResponseModel
import com.example.safebodatest.features.login.domain.entities.LoginResponseEntity

class LoginDataEntityAdapter: IDataModel<LoginResponseModel, LoginResponseEntity>() {
    override fun toEntity(model: LoginResponseModel): LoginResponseEntity = model

    override fun toModel(entity: LoginResponseEntity): LoginResponseModel {
        return LoginResponseModel(
            username = entity.username,
            token = entity.token,
            email = entity.email
        )
    }
}