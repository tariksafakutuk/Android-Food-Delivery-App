package com.example.foodie.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.foodie.data.entity.AccountCardItem
import com.example.foodie.data.entity.User
import com.example.foodie.datastore.LoginPref
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDataSource(var collectionUser: CollectionReference, var loginPref: LoginPref) {
    var userData = MutableLiveData<List<String>>()

    fun isLoginCheck(): MutableLiveData<List<String>> {
        var email = ""
        var username = ""
        var password = ""

        CoroutineScope(Dispatchers.IO).launch {
            email = loginPref.readEmail()
            username = loginPref.readUsername()
            password = loginPref.readPassword()
        }

        collectionUser.addSnapshotListener { value, error ->
            if (value != null) {
                var userList = ArrayList<String>()
                for (d in value.documents) {
                    val user = d.toObject(User::class.java)
                    if (user != null) {
                        if ((email == user.email.toString() || username == user.username.toString()) && password == user.password.toString()) {
                            userList = arrayListOf("login")
                            break
                        } else {
                            userList = arrayListOf("loginFailed")
                        }
                    }
                }

                if (userData.value != null) {
                    if (userData.value!![0] == "logout") {
                        userList = arrayListOf("logout")
                    }
                }

                userData.value = userList
            }
        }

        return userData
    }

    fun login(account: String, password: String): MutableLiveData<List<String>> {
        collectionUser.addSnapshotListener { value, error ->
            if (value != null) {
                var userList = ArrayList<String>()
                for (d in value.documents) {
                    val user = d.toObject(User::class.java)

                    if (user != null) {
                        if ((account == user.email.toString() || account == user.username.toString())) {
                            if (password == user.password.toString()) {
                                userList = arrayListOf(
                                    user.email.toString(),
                                    user.username.toString(),
                                    user.password.toString()
                                )
                                break
                            } else {
                                userList = arrayListOf("passwordFailed")
                            }
                        } else {
                            userList = arrayListOf("accountFailed")
                        }
                    }
                }
                if (userData.value != null) {
                    if (userData.value!![0] == "logout") {
                        userList = arrayListOf("loginFailed")
                    }
                }
                userData.value = userList
            }
        }

        return userData
    }

    fun signUp(email: String, username: String, password: String): List<String> {
        val newData = if (email != "" && username != "" && password != "") {
            val newUser = User("", email, username, password)
            collectionUser.document().set(newUser)
            arrayListOf(email, username, password)
        } else {
            arrayListOf("")
        }

        return newData
    }


    suspend fun loadAccountCard(): List<AccountCardItem> =
        withContext(Dispatchers.IO) {
            val accountCardItemList = ArrayList<AccountCardItem>()
            val a1 = AccountCardItem("vc_email", "Email Değişikliği")
            val a2 = AccountCardItem("vc_password", "Şifre Değişikliği")
            val a3 = AccountCardItem("vc_logout", "Çıkış")
            accountCardItemList.add(a1)
            accountCardItemList.add(a2)
            accountCardItemList.add(a3)

            return@withContext accountCardItemList
        }

    private fun updateWithId(email: String, username: String, updatedUser: HashMap<String, Any>){
        collectionUser.addSnapshotListener { value, error ->
            if (value != null) {
                for (d in value.documents) {
                    val user = d.toObject(User::class.java)

                    if (user != null) {
                        if (user.email.toString() == email && user.username.toString() == username) {
                            collectionUser.document(d.id).update(updatedUser)
                            userData.value = arrayListOf("logout")
                            break
                        }
                    }
                }
            }
        }
    }

    fun updateAccount(updateStatus: List<String>) {
        val updatedUser = HashMap<String, Any>()
        updatedUser["user_id"] = ""

        when (updateStatus[0]) {
            "email" -> {
                updatedUser["email"] = updateStatus[1]
                updatedUser["username"] = updateStatus[3]
                updatedUser["password"] = updateStatus[4]
            }

            "password" -> {
                updatedUser["email"] = updateStatus[2]
                updatedUser["username"] = updateStatus[3]
                updatedUser["password"] = updateStatus[1]
            }
        }

        updateWithId(updateStatus[2], updateStatus[3], updatedUser)
    }
}