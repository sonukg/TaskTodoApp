package com.sonukg.tasktodoapp.`interface`

interface RecyclerViewClickListener<T> {
    fun onClick(`object`: T, position: Int)
}