package com.omeryildizce.cryptocompose.repository

import com.omeryildizce.cryptocompose.model.Crypto
import com.omeryildizce.cryptocompose.model.CryptoList
import com.omeryildizce.cryptocompose.service.CryptoAPI
import com.omeryildizce.cryptocompose.util.Constants.API_KEY
import com.omeryildizce.cryptocompose.util.Constants.CALL_ATTRIBUTES
import com.omeryildizce.cryptocompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class CryptoRepository@Inject constructor(
    private val api: CryptoAPI
) {

    suspend fun getCryptoList(): Resource<CryptoList> {
        val response = try {
            api.getCryptoList(API_KEY)
        } catch(e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(id: String): Resource<Crypto> {
        val response = try {
            api.getCrypto(API_KEY,id,CALL_ATTRIBUTES)
        } catch(e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}