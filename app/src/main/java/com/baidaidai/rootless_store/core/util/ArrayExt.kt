package com.baidaidai.rootless_store.core.util

fun <T> Array<T>.OutOfStringLike(): String{
    var patched = ""
    for (i in 0..size-1){
        patched = patched + get(i) + ",\n\n"
    }
    return patched
}