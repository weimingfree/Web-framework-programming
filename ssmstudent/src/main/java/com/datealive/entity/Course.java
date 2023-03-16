package com.datealive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int id;
    private String name;           /**课程的名称*/
    private int teacher_id;        /**教授此课的教师id*/
    private String course_date;    /**上课时间*/
    private  int selected_num = 0; /**选择此课的学生人数*/
    private  int max_num = 50;     /**此课的限选人数*/
    private Integer cgrade;        /**学分*/

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

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCourse_date() {
        return course_date;
    }

    public void setCourse_date(String course_date) {
        this.course_date = course_date;
    }

    public int getSelected_num() {
        return selected_num;
    }

    public void setSelected_num(int selected_num) {
        this.selected_num = selected_num;
    }

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    public Integer getCgrade() {
        return cgrade;
    }

    public void setCgrade(Integer cgrade) {
        this.cgrade = cgrade;
    }
}
