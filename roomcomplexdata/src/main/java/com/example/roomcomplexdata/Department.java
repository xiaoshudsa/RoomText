package com.example.roomcomplexdata;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class Department {
    @ColumnInfo(name = "departmentId")
    public int id;
    @ColumnInfo(name = "departmentName")
    public String name;

    public Department() {
    }

    @Ignore
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}