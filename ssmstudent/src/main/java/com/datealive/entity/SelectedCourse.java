package com.datealive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedCourse {

    private int id;
    private int student_id;   /**学生id*/
    private int teacher_id;   /**教师id*/
    private int course_id;    /**课程id*/
    private int score_id;     /**学生考取的分数*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }
}
