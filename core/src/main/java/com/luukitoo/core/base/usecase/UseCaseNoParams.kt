package com.luukitoo.core.base.usecase

interface UseCaseNoParams<Result> {
    suspend fun execute(): Result
}