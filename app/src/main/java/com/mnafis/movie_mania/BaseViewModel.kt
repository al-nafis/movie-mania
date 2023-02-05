package com.mnafis.movie_mania

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract val screenName: String
}