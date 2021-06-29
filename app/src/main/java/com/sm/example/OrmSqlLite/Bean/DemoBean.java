package com.sm.example.OrmSqlLite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
@DatabaseTable(tableName = "demobean")
public class DemoBean {
    @Override
    public String toString() {
        return "DemoBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @DatabaseField(generatedId = true) //generatedId = true 表示自增长的主键
    private int id;
    @DatabaseField //只有添加这个注释，才能把此属性添加到表中的字段
    private String name;
    @DatabaseField //只有添加这个注释，才能把此属性添加到表中的字段
    private String age;

    public DemoBean(String name, String age) {
        this.name = name;
        this.age = age;

    }
    public DemoBean() {


    }
}
