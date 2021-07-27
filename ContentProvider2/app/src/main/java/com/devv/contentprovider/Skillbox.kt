package com.devv.contentprovider
//
//import android.content.*
//import android.database.Cursor
//import android.database.MatrixCursor
//import android.net.Uri
//import com.squareup.moshi.Moshi
//
//class Skillbox : ContentProvider() {
//
//    private lateinit var userPrefs: SharedPreferences
//    private lateinit var coursesPrefs: SharedPreferences
//
//    private val userAdapter = Moshi.Builder().build().adapter(User::class.java)
//    private val coursesAdapter = Moshi.Builder().build().adapter(Courses::class.java)
//
//    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
//        addURI(AUTH, PATH_USERS, TYPE_USERS)
//        addURI(AUTH, PATH_COURSES, TYPE_COURSES)
//        addURI(AUTH, "$PATH_USERS/#", TYPE_USERS_ID)
//        addURI(AUTH, "$PATH_COURSES/#", 4)
//    }
//
//    override fun onCreate(): Boolean {
//        userPrefs = context!!.getSharedPreferences("user_shared", Context.MODE_PRIVATE)
//        coursesPrefs = context!!.getSharedPreferences("course_shared", Context.MODE_PRIVATE)
//        return true
//    }
//
//    override fun query(
//        uri: Uri,
//        projection: Array<out String>?,
//        selection: String?,
//        selectionArgs: Array<out String>?,
//        sortOrder: String?,
//    ): Cursor? {
//        return when (uriMatcher.match(uri)) {
//            TYPE_USERS -> {
//                getuser()
//            }
//            else -> null
//        }
//    }
//
//    private fun getuser(): Cursor {
//        val allUsers = userPrefs.all.mapNotNull {
//            val userJsonString = it.value as String
//            userAdapter.fromJson(userJsonString)
//        }
//        val cursor = MatrixCursor(arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_NAME))
//        allUsers.forEach {
//            cursor.newRow()
//                .add(it.id)
//                .add(it.name)
//                .add(it.age)
//        }
//        return cursor
//    }
//
//    override fun getType(uri: Uri): String? {
//        return null
//    }
//
//    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        values ?: return null
//        return when (uriMatcher.match(uri)) {
//            TYPE_USERS -> saveUser(values)
//            else -> null
//        }
//    }
//
//    private fun saveUser(contentValues: ContentValues): Uri? {
//        val id = contentValues.getAsLong(COLUMN_USER_ID) ?: return null
//        val name = contentValues.getAsString(COLUMN_USER_NAME) ?: return null
//        val age = contentValues.getAsInteger(COLUMN_USER_AGE) ?: return null
//
//        val user = User(id, name, age)
//        userPrefs.edit()
//            .putString(id.toString(), userAdapter.toJson(user))
//            .commit()
//        return Uri.parse("content://$AUTH/$PATH_USERS/$id")
//    }
//
//    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
//        return when (uriMatcher.match(uri)){
//            TYPE_USERS -> deleteUser(uri)
//            else -> 0
//        }
//    }
//
//    private fun deleteUser(uri:Uri):Int{
//        val userId = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
//        return if (userPrefs.contains(userId)){
//            userPrefs.edit()
//                .remove(userId)
//                .commit()
//            1
//        }else{
//            0
//        }
//    }
//
//    override fun update(
//        uri: Uri,
//        values: ContentValues?,
//        selection: String?,
//        selectionArgs: Array<out String>?,
//    ): Int {
//        values ?: return 0
//        return when (uriMatcher.match(uri)){
//            TYPE_USERS_ID -> updateUser(uri, values)
//            else -> 0
//        }
//    }
//
//    private fun updateUser(uri:Uri, conValues:ContentValues):Int {
//        val userId = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
//        return if (userPrefs.contains(userId)){
//            saveUser(conValues)
//            1
//        }else{
//            0
//        }
//    }
//
//
//        companion object {
//        private const val AUTH = "${BuildConfig.APPLICATION_ID}.provider"
//        private const val PATH_USERS = "users"
//        private const val PATH_COURSES = "courses"
//        private const val TYPE_COURSES = 2
//        private const val TYPE_USERS = 1
//        private const val TYPE_USERS_ID = 3
//        private const val COLUMN_USER_ID = "id"
//        private const val COLUMN_USER_NAME = "name"
//        private const val COLUMN_USER_AGE = "age"
//    }
//}