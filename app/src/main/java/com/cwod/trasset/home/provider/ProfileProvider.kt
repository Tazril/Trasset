package com.cwod.trasset.home.provider

import com.cwod.trasset.helper.ApiClient
import com.cwod.trasset.helper.GeneralModel
import com.cwod.trasset.helper.PresenterCallback
import com.cwod.trasset.home.provider.model.AssetListModel
import com.cwod.trasset.home.provider.model.ProfileModel
import com.cwod.trasset.home.provider.model.SessionCreateModel
import com.cwod.trasset.home.provider.model.SessionListModel
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProfileProvider() {

    fun getProfileResponse(callback: PresenterCallback<ProfileModel>): Disposable {
        return ApiClient.retroClientCache.create(ProfileApi::class.java).getProfileResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    if(it.error?.message==null)
                        it.data?.apply { callback.onSuccess(this)}
                    else    callback.onFailure("Error: ${it.error.message}")
                },
                onError = { callback.onFailure(it.message?:"Some Error Occurred") }
            )

    }

}