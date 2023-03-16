package com.datealive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private int id;
    private String sn;        /**工号*/
    private String name;      /**姓名*/
    private String password;  /**密码*/
    private int clazz_id;     /**所教授班级*/
    private String sex;       /**性别*/
    private String tposition; /**职称*/
    private String salary;    /**薪资*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClazz_id() {
        return clazz_id;
    }

    public void setClazz_id(int clazz_id) {
        this.clazz_id = clazz_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTposition() {
        return tposition;
    }

    public void setTposition(String tposition) {
        this.tposition = tposition;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
