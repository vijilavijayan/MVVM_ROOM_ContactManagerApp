package com.example.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerapp.databinding.ActivityMainBinding
import com.example.contactmanagerapp.room.User
import com.example.contactmanagerapp.room.UserDataBase
import com.example.contactmanagerapp.room.UserRepository
import com.example.contactmanagerapp.viewModel.UserViewModel
import com.example.contactmanagerapp.viewModel.UserViewModelFactory
import com.example.contactmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room
        val dao = UserDataBase.getInstance(application).userDao
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)


        mUserViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        binding.userViewModel = mUserViewModel

        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun DisplayUserList() {

        mUserViewModel.users.observe(this, Observer {
            binding.recyclerview.adapter = MyRecyclerViewAdapter(it,{selectedItem:User->listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this,"Selected Item ${selectedItem.name}",Toast.LENGTH_LONG)

        mUserViewModel.initUpdateAndDelete(selectedItem)
    }
}