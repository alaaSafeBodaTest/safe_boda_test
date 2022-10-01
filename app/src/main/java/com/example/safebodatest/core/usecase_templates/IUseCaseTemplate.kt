package com.example.safebodatest.core.usecase_templates

import arrow.core.Either
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.features.users_list.domain.entity.FollowingListItemEntity

interface IUseCaseTemplate<P, R, F> {

    fun run(params: P): Either<F?, R> = Either.Left(null)

    suspend fun runAsync(params: P): Either<IFailure?, R> = Either.Left(null)

    class NoParams()
}