package com.playground.paging.domain.contract

interface Page<T> {
    fun getNextKey(): Int?
    fun getPreviousKey(): Int?
    fun getContent(): List<T>
    fun isLast(): Boolean
}