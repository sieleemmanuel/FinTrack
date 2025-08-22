package se.appthrive.fintrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform