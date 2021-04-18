package com.sonukg.tasktodoapp.`interface`

interface RecycleViewDeleteListener<T> {
    fun onDeleteClick(`object`: T, position: Int)
}