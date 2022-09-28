package com.example.safebodatest.core.model_templates

abstract class IDataModel<M, E> {
    abstract fun toEntity(model: M): E
    abstract fun toModel(entity: E): M
}