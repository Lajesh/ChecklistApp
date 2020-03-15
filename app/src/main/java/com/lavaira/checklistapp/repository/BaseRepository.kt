package com.lavaira.checklistapp.repository

import com.lavaira.checklistapp.data.remote.model.response.ApiResponse
import com.lavaira.checklistapp.data.remote.model.response.ResponseListener
import com.lavaira.checklistapp.data.remote.model.response.ResponseStatus
import com.lavaira.checklistapp.schedulers.SchedulerContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/****
 * Base repository which is responsible for executing all the REST service calls
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
open class BaseRepository (val scheduler: SchedulerContract){


    /**
     * This method perfroms the asynchronous network request using the scheduler
     * @param observable : Observable network request
     * @param responseListener: ResponseListener Callback
     */
     fun <T : Any> performRequest(observable: Observable<T>, responseListener: ResponseListener<T>) : Disposable {
        return observable.subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { responseListener.onStart() }
            .doAfterTerminate { responseListener.onFinish() }
            .subscribe({ result: T -> responseListener.onResponse(ApiResponse(ResponseStatus.SUCCESS, result, null)) },
                { error: Throwable? -> responseListener.onResponse(ApiResponse(ResponseStatus.FAILURE, null, error)) })

    }

}