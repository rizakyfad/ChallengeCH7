package com.rizaki.challengech6Binar.database.local


class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun checkUser(email: String, password: String): User {
        return userDao.checkUser(email, password)
    }

    suspend fun getUser(id: Int): User {
        return userDao.getUser(id)
    }
}