package com.example

import com.bugatti.chiron.core.constants.APIHeaderType
import org.junit.jupiter.api.Test
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import java.util.*

class Test {
    val restTemplate = RestTemplate();

    /**
     * 고유값 생성
     */
    @Test
    fun getTimeStamp(){
        for (i in 1..30){
            val id = System.currentTimeMillis() * 1000 + Random().nextInt(999)
            println("ID : ${id}")
        }
    }

    @Test
    fun test(): Unit {
//        val apiList = mutableSetOf<String>(
//        )
//        println(apiList.size)
//        apiList.forEach {
        kotlin.runCatching {
            val listApiResponse: ResponseEntity<HashMap<*, *>> = this.exchange(
                "url",
                HttpMethod.GET, APIHeaderType.NAVER,
                "",
                HashMap::class.java
            )
            println(listApiResponse.body)
        }
//        }
    }

    private fun <T> exchange(url: String, httpMethod: HttpMethod, headerType: APIHeaderType, any: Any, t: Class<T>): ResponseEntity<T> {
        val headers = setDefaultHeader(HttpHeaders(), headerType)
        val request: HttpEntity<Any> = HttpEntity<Any>(any, headers)
        return restTemplate.exchange(
            url,
            httpMethod,
            request,
            t
        )
    }

    /**
     * costom header 영역
     */
    fun setDefaultHeader(headers: HttpHeaders, aPIheaderType: APIHeaderType): HttpHeaders {
        headers.contentType = MediaType.APPLICATION_JSON
        when (aPIheaderType) {
            APIHeaderType.NAVER -> {
                headers.add("naver", "naver")
            }
            APIHeaderType.KAKAO -> {
                headers.add("kakao", "kakao")
            }
        }
        return headers
    }
}