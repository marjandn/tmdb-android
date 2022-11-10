package dn.marjan.tmdb.app.base.usecase

import dn.marjan.tmdb.app.base.datastate.DataState

abstract class UseCase<Type, Param> {

    abstract suspend fun invoke(param: Param): DataState<Type>
}


class NoParam
