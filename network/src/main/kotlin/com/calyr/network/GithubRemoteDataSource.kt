package com.calyr.network

import android.telephony.AvailableNetworkInfo

class GithubRemoteDataSource (
    val retrofiService: RetrofitBuilder
) {
    suspend fun getAvatarInfo(githubLogin: String): AvatarResponseDto{
        return retrofiService.apiService.getInfoAvatar(githubLogin)
    }
}