package com.example.safebodatest.features.user_details.domain.usecase

import arrow.core.Either
import com.example.safebodatest.core.db.tables.User
import com.example.safebodatest.core.failures.IFailure
import com.example.safebodatest.core.usecase_templates.IUseCaseTemplate
import com.example.safebodatest.features.user_details.domain.entity.LoadUserFollowRequestEntity
import com.example.safebodatest.features.user_details.domain.repository.IUserDetailsRepository
import javax.inject.Inject

class LoadUserFollowings @Inject constructor(private val repo: IUserDetailsRepository): IUseCaseTemplate<LoadUserFollowRequestEntity, List<User>, IFailure> {

    override suspend fun runAsync(params: LoadUserFollowRequestEntity): Either<IFailure?, List<User>> {
        return repo.loadUserFollowingsByUsername(params)
    }
}