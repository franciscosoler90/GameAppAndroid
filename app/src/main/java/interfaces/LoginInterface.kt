package interfaces

import entidades.UserData

interface LoginInterface {
    fun signIn(userData: UserData)

    fun createAccount(userData: UserData)

    fun updateName(userData: UserData)

    fun registerActivity()

    fun loginActivity()
}