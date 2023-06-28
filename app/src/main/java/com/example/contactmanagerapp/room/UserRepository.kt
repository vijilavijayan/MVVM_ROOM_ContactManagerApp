package com.example.contactmanagerapp.room

/**
 * @author Vijila P
 */
class UserRepository(private val dao: UserDao) {

    val  users = dao.getAllUsers()

    suspend fun insertUser(user: User):Long{
        return dao.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        return dao.delete(user)
    }

    suspend fun deleteAll(){
        return dao.deleteAll()
    }

    suspend fun updateUser(user: User){
        return dao.updateUser(user)
    }
}