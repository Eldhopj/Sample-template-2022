package com.eldhopj.myapplication.utils

import java.io.InputStreamReader

class MockResponseFileReader(path: String) {
    val content: String

    init {
        val reader =
            InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream("api_response/${path}"))
        content = reader.readText()
        print(content)
        reader.close()
    }
}
