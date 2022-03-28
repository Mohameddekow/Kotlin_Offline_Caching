package com.example.myofflinecaching.util

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,                                           //get data from database only
    crossinline fetch: suspend () -> RequestType,                                        //get data from the network
    crossinline saveFetchResult: suspend (RequestType) -> Unit,                         //save the data we get from the network into room lib
    crossinline shouldFetch: (ResultType) -> Boolean = { true }                                  //decides if we should fetch new data from network or not

) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))


        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                Resource.Error(throwable, it)
            }
        }
    } else {

        query().map {
            Resource.Success(it)
        }

    }

    emitAll(flow)
}