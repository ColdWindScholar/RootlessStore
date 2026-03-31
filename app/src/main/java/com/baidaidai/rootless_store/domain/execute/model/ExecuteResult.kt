package com.baidaidai.rootless_store.domain.execute.model

import javax.inject.Inject

data class ExecuteResult @Inject constructor(
    val resulTag: ResultTag,
    val content: String
)
