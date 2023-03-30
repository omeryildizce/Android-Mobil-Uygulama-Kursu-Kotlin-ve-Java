package com.omeryildizce.sqlite

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sqLite()
    }

    @SuppressLint("Recycle")
    private fun sqLite() {
        try {
            val myDatabase = this.openOrCreateDatabase("musicians", MODE_PRIVATE, null)
            myDatabase.execSQL("create table if not exists musicians(id INTEGER primary key,name varchar, age int)")
            // insert(myDatabase)
             delete(myDatabase)
             // update(myDatabase)

            var cursor = myDatabase.rawQuery("select * from musicians ", null)
            cursor = myDatabase.rawQuery("select * from musicians where name = 'James'", null)
            cursor = myDatabase.rawQuery("select * from musicians where id = 3", null)
            cursor = myDatabase.rawQuery("select * from musicians where name like '%ö%'", null)
            val nameIndex = cursor.getColumnIndex("name")
            val ageIndex = cursor.getColumnIndex("age")
            val idIndex = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val info = String.format("Id: %3s, Name: %10s, Age:%3s",cursor.getString(idIndex),cursor.getString(nameIndex),cursor.getString(ageIndex) )
                println(info)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun delete(myDatabase: SQLiteDatabase) {
        myDatabase.execSQL("delete from musicians where name = 'M Ömer'")
    }
    private fun update(myDatabase: SQLiteDatabase) {
        myDatabase.execSQL("update musicians set age = 61 where name = 'James'")
        myDatabase.execSQL("update musicians set name = 'M Ömer' where name = 'Ömer'")
    }

    private fun insert(myDatabase: SQLiteDatabase) {
            myDatabase.execSQL("insert into musicians (name, age) values ('James', 45)")
            myDatabase.execSQL("insert into musicians (name, age) values ('Ömer', 25)")
            myDatabase.execSQL("insert into musicians (name, age) values ('Ali', 35)")
            myDatabase.execSQL("insert into musicians (name, age) values ('Ayşe', 35)")
    }
}