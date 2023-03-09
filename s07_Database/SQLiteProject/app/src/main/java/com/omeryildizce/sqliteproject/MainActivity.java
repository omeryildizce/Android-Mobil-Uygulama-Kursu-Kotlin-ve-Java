package com.omeryildizce.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            database.execSQL("create table if not exists musicians (id integer primary key, name varchar, age int)");
            //database.execSQL("insert into musicians (name, age) values('Kadir', 35)");
            //database.execSQL("insert into musicians (name, age) values('Ömer', 25)");
            //database.execSQL("insert into musicians (name, age) values('Ali', 19)");
            //database.execSQL("insert into musicians (name, age) values('Ayşe', 32)");
            //database.execSQL("update musicians set name = 'Fatih' where id = 2 ");
            // database.execSQL("delete from musicians where id = 2 ");
            //Cursor cursor = database.rawQuery("select * from musicians where id = 2", null);
            Cursor cursor = database.rawQuery("select * from musicians", null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {
                System.out.println("Id:  " + cursor.getInt(idIndex));
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age:  " + cursor.getInt(ageIndex));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}