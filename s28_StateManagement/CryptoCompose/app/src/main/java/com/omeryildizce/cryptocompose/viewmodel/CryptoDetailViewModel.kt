package com.omeryildizce.cryptocompose.viewmodel


import androidx.lifecycle.ViewModel
import com.omeryildizce.cryptocompose.model.Crypto
import com.omeryildizce.cryptocompose.repository.CryptoRepository
import com.omeryildizce.cryptocompose.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    suspend fun getCrypto(id: String): Resource<Crypto> {
        return repository.getCrypto(id)
    }
}