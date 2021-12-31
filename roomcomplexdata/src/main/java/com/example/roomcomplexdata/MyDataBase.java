package com.example.roomcomplexdata;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


                                     //加入新建表，必须要升级数据库版本
@Database(entities = {User.class,Student.class},version = 2, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {
    private static final String DATABASE_NAME="my_db";
    private static MyDataBase sMyDataBase;
    //这里不能自己创建构造方法
     public static MyDataBase getMyDataBase(Context mContext){
        if (sMyDataBase==null)
            sMyDataBase = Room.databaseBuilder(mContext.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    //.allowMainThreadQueries()   在主线程中操作数据库可以调用这个方法
                    .addMigrations(MIGRATION_1_2)
                    .build();
    return sMyDataBase;
    }

    //ROOM数据库升级方法  当要跨等级升级是（1到4，他会按顺序执行1到4的升级方法）  他也会按顺序执行升级方法
    static final Migration MIGRATION_1_2=new Migration(1,2){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE student (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `age` INTEGER NOT NULL) ");
        }
    };

    public abstract UserDao getUserDao();

    public abstract StudentDao getStudentDao();

}
