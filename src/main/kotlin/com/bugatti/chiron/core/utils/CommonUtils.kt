package com.bugatti.chiron.core.utils

import java.util.*

class CommonUtils {
    companion object {
        fun getId() = System.currentTimeMillis() * 1000 + Random().nextInt(999)
    }
}