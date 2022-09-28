package com.example.safebodatest.core.usecase_templates

import arrow.core.Either

interface IUseCaseTemplate<P, R, F> {

    fun run(params: P): Either<F?, R> = Either.Left(null)

    suspend fun runAsync(params: P): Either<F?, R> = Either.Left(null)

    class NoParams()
}