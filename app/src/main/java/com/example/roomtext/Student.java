package com.example.roomtext;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name ="name",typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "age",typeAffinity = ColumnInfo.INTEGER)
    public int age;
                    //参数与属性名必须同名
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Ignore   //Room数据库不会用这个初始化
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Ignore   //Room数据库不会用这个初始化
    public Student(int id) {
        this.id=id;
    }

}

