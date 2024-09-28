package entity

data class UserData(
    var name: String = "",
    var email: String = "",
    var pwd: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && pwd.isNotEmpty()
    }
}
