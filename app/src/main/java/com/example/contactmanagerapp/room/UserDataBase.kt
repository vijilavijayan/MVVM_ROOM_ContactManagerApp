package com.example.contactmanagerapp.room

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

/**
 * @author Vijila P
 */
@Database(entities = [User :: class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract val  userDao:UserDao

    //singleton
    companion object{
        @Volatile
        private var INSTANCE:UserDataBase ?=null
        fun getInstance(context: Context):UserDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        UserDataBase::class.java,
                    "user_db").build()
                }
                return instance
            }
        }
    }

}