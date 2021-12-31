package com.example.roomcomplexdata;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "user")  //实体类创建需要加上@Entity注解
@TypeConverters({DepartmentListConvert.class})
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "user_age")
    public int userAge;
    @ColumnInfo(name = "user_mobile")
    public String userMobile;

        /**
         * 只是单个对象，不是List/set
         * 直接用@Embedded 嵌入到user表中，即user表中的列将根据Department的属性增加
         */
//    @Embedded
//    public Department department;

        public List<Department> departments;

    public User(/*int pId,*/ String userName, int userAge, String userMobile, List<Department> departments) {
 /*       id = pId;*/
        this.userName = userName;
        this.userAge = userAge;
        this.userMobile = userMobile;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userMobile='" + userMobile + '\'' +
                ", departments=" + departments +
                '}';
    }
}