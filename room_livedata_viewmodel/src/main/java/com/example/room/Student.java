package com.example.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
//表名
@Entity(tableName = "student")
public class Student {
    //自增
    @PrimaryKey(autoGenerate = true)
    /**
     * name    表中字段名
     * typeAffinity  字段类型
     */
    @ColumnInfo(name="id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name ="name",typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "age",typeAffinity = ColumnInfo.INTEGER)
    public int age;

/*    @ColumnInfo(name = "sex",typeAffinity = ColumnInfo.INTEGER)
    public int sex;

    @ColumnInfo(name = "interest",typeAffinity = ColumnInfo.INTEGER)
    public int interest;*/

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
