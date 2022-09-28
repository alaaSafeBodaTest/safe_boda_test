package com.example.safebodatest.core.model_templates

abstract class IDataModel<M, E> {
    abstract fun toEntity(): E
    abstract fun toModel(): M
}