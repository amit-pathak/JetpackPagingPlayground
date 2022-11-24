package com.playground.paging.domain.response

import android.net.Uri
import com.playground.paging.domain.contract.Page
import java.io.Serializable

data class CharactersResponse (
    val info: Info,
    val results: List<Result>
): Page<Result> , Serializable{

    override fun getNextKey(): Int? {
        if(info.next!=null){
            return Uri.parse(info.next).getQueryParameter("page")?.toInt()
        }
       return null
    }

    override fun getPreviousKey(): Int? {
        if(info.prev!=null){
            return Uri.parse(info.prev).getQueryParameter("page")?.toInt()
        }
        return null
    }

    override fun getContent(): List<Result> {
       return results
    }

    override fun isLast(): Boolean {
       return info.next.isNullOrBlank()
    }

}