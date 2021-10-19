package com.infinity.marvelapi_mvvm_kotlin.utils

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object PaginationUtils {

    fun addLoadMoreListener(recyclerView: RecyclerView, onLoadMore: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                (recyclerView.layoutManager as LinearLayoutManager?)?.let { layoutManager ->
                    val totalItemCount = layoutManager.itemCount
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val endHasBeenReached = lastVisible + 1 >= totalItemCount

                    if (totalItemCount > 0 && endHasBeenReached) onLoadMore()
                }
            }
        })
    }

}