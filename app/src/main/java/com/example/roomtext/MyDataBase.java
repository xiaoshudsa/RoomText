package com.example.roomtext;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * entities    是一个数组要在此配置你的实体类
 * version     数据库版本号
 * exportSchema  一个找错文件，这里先关闭
 */
@Database(entities = {Student.class},version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    //数据库名字
    private static final String DATABASE_NAME="my_db";
    private static MyDataBase sMyDataBase;
    //这里不能自己创建构造方法
     public static MyDataBase getMyDataBase(Context mContext){
        if (sMyDataBase==null)
            sMyDataBase = Room.databaseBuilder(mContext.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    //.allowMainThreadQueries()   在主线程中操作数据库可以调用这个方法
                    .addMigrations()
                    .build();
    return sMyDataBase;
    }


    //获取Student对象
    public abstract StudentDao getStudentDao();

}
