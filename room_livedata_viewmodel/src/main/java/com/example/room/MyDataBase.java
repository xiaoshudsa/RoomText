package com.example.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
                                                    //Schema文件可以排查错误
@Database(entities = {Student.class},version = 4, exportSchema = true)
public abstract class MyDataBase extends RoomDatabase {
    private static final String DATABASE_NAME= "my.db";
    private static MyDataBase sMyDataBase;
    //这里不能自己创建构造方法
     public static MyDataBase getMyDataBase(Context mContext){
        if (sMyDataBase==null)
            sMyDataBase = Room.databaseBuilder(mContext.getApplicationContext(), MyDataBase.class, DATABASE_NAME)
                    //.allowMainThreadQueries()   在主线程中操作数据库可以调用这个方法
                    //.addMigrations(MIGRATION_1_2,MIGRATION_2_3/*,MIGRATION_3_4*/)
                    .createFromAsset("prestudent.db")      //赋初始值 把其他表的值复制这个表中
                    //.fallbackToDestructiveMigration()  //此方法在数据库升级时报错时或从高版本返回低版本时会报错 调用此方法可以强行升级数据但原保存数据会丢失
                    .build();
    return sMyDataBase;
    }
        //ROOM数据库升级方法  当要跨等级升级是（1到4，他会按顺序执行1到4的升级方法）  他也会按顺序执行升级方法
    static final Migration  MIGRATION_1_2=new Migration(1,2){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN sex INTEGER NOT NULL DEFAULT 1");
        }
    };

    static final Migration  MIGRATION_2_3=new Migration(2,3){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN interest INTEGER NOT NULL DEFAULT 1");
        }
    };
    //修改原表数据
        static final Migration  MIGRATION_3_4=new Migration(3,4){

            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE temp_student (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                        "name TEXT,"+
                        "age INTEGER NOT NULL,"+
                        "sex TEXT DEFAULT 'M',"+
                        "bar_data INTEGER NOT NULL DEFAULT 1)");
                database.execSQL("INSERT INTO temp_student (name,age,sex,bar_data)" +
                        "SELECT name,age,sex,bar_data FROM student");
                database.execSQL("DROP TABLE student");
                database.execSQL("ALTER TABLE temp_student RENAME TO student");
            }
        };


    public abstract StudentDao getStudentDao();

}
