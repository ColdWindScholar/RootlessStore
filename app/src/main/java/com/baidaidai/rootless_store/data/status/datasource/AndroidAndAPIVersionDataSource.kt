package com.baidaidai.rootless_store.data.status.datasource

import javax.inject.Inject

class AndroidAndAPIVersionDataSource @Inject constructor() {
    fun getAndroidVersion(): String{
        return "Android ${android.os.Build.VERSION.RELEASE}"
    }
    fun getAndroidAPIVersion(): String{
        return "API ${android.os.Build.VERSION.SDK_INT}"
    }
}