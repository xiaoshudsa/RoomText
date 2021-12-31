package com.example.roomcomplexdata;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseListConvert<T> {
    @TypeConverter
    public List<T> revert(String jsonString) {
        // 使用Gson方法把json格式的string转成List
        try {

            Type type = new TypeToken<ArrayList<T>>(){}.getType();
            List<T> list = new Gson().fromJson(jsonString,type);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public String converter(List<T> list) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        return new Gson().toJson(list);
    }

}
