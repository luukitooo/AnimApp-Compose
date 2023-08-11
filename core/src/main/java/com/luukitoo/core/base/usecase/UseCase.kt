package com.luukitoo.core.base.usecase

interface UseCase<Parameter, Result> {
    suspend fun execute(parameter: Parameter): Result
}