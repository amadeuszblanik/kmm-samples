package me.blanik.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform