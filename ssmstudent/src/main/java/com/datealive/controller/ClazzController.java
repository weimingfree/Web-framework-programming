package com.datealive.controller;


import com.alibaba.fastjson.JSONObject;
import com.datealive.entity.*;
import com.datealive.service.ClazzService;
import com.datealive.service.StudentService;
import com.datealive.service.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/ClazzServlet")
public class ClazzController {

        @Autowired(required = false)
        ClazzService clazzService;

        @Autowired(required = false)
        StudentService studentService;

        @Autowired(required = false)
        SelectedCourseService selectedCourseService;

        //获取班级列表
        @RequestMapping("/getClazzList")
        @ResponseBody
        public String getClazzList( HttpServletRequest request, HttpServletResponse response){
            Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            Integer pageSize = request.getParameter("limit") == null ? 999 : Integer.parseInt(request.getParameter("limit"));
            currentPage--;
            Clazz clazz = new Clazz();
            if(null!=request.getParameter("clazzName") && !(""==request.getParameter("clazzName"))) {
                clazz.setName(request.getParameter("clazzName"));
            }
            //clazz.setId(5);
            String from = request.getParameter("from");
            System.out.println("from="+from);
            int total=0;
            List<Clazz> array=null;
           // JSONArray array=null;
            int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
            System.out.println("userType="+userType);



            if(userType == 2){
                //如果是学生，只能查看自己所在班级的信息
                //Student student = new Student();
                Student currentUser = (Student)request.getSession().getAttribute("user");
                System.out.println("currentUesr="+currentUser);
                //student.setId(currentUser.getId());
                clazz.setId(currentUser.getClazz_id());
                //System.out.println("班级取: "+currentUser.getClazzId()+"\n");
                //System.out.println(currentUser);
                System.out.println("clazz="+clazz);
                List<Clazz> clazzList = clazzService.getClazzList(clazz,currentPage,pageSize);
                total = clazzService.getClazzListTotal(clazz);

                array=clazzList;
            }
            //用户为老师时
            else if(userType==3) {
                Teacher currentUser = (Teacher)request.getSession().getAttribute("user");
                //如果from信息是list,返回所有班级列表
                if("list".equals(from)) {
                    List<Clazz> clazzList = clazzService.getClazzList(clazz,currentPage,pageSize);
                    total = clazzService.getClazzListTotal(clazz);
                    array=clazzList;
                }
                //如果from信息是ok
                else if("ok".equals(from)) {
                    SelectedCourse selectedCourse = new SelectedCourse();
                    selectedCourse.setTeacher_id(currentUser.getId());
                    System.out.println("查询的老师:"+selectedCourse);
                    //根据此老师的信息获取对应的选课表
                   List<SelectedCourse> selectList = selectedCourseService
                           .getSelectedCourseList(selectedCourse, currentPage, pageSize);
                    //获取总学生表
                    Student student = new Student();
                    List<Student> studentList = studentService
                            .getStudentList(student, currentPage, pageSize);
                    List<Student> ans=new ArrayList<>();//该老师的学生表
                    int sum=0;
                    //从总的学生表中选取
                    for(Student item1:studentList ) {
                        boolean flag=false;
                        //从选课表中选取
                        for(SelectedCourse item2:selectList) {
                            if(item1.getId()==item2.getStudent_id()) {
                                flag=true;
                                break;
                            }
                        }
                        //获取到此老师对应的学生列表
                        if(flag) {
                            ans.add(item1);
                        }
                    }
                    List<Clazz> clazzList = clazzService.getClazzList(clazz, currentPage, pageSize);
                    List<Clazz> ans2=new ArrayList<>();
                    //从总班级中选取
                    for(Clazz item1:clazzList) {
                        boolean flag=false;
                        //从该老师所对应的学生中选取
                        for(Student item2:ans) {
                            if(item1.getId()==item2.getClazz_id()) {
                                flag=true;break;
                            }
                        }
                        //获取到此老师对应的班级列表
                        if(flag) {
                            ans2.add(item1);
                            sum++;
                        }
                    }
                    array=ans2;
                    total=sum;
                }
                else {
                    //返回该老师所在的班级
                    clazz.setId(currentUser.getClazz_id());
                    List<Clazz> clazzList = clazzService.getClazzList(clazz, currentPage, pageSize);
                    total = clazzService.getClazzListTotal(clazz);
                    array=clazzList;
                }
            }
            //用户类型为管理员
            else {
                List<Clazz> clazzList = clazzService.getClazzList(clazz, currentPage, pageSize);
                total = clazzService.getClazzListTotal(clazz);
                array=clazzList;
            }
            response.setCharacterEncoding("UTF-8");
            return "{ \"code\":0,\"message\":\"成功\",\"data\":" +
                    JSONObject.toJSONString(array) + ",\"count\" :"+total+"}";//            HashMap<String, Object> result = new HashMap<String, Object>();
        }

        //修改班级信息
        @RequestMapping("/EditClazz")
        public void EditClazz(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String info = request.getParameter("info");
            Clazz clazz = new Clazz();
            clazz.setName(name);
            clazz.setInfo(info);
            clazz.setId(id);
            clazzService.editClazz(clazz);
            response.getWriter().write("success");
        }

        @RequestMapping("/AddClazz")
        public void AddClazz(HttpServletRequest request,HttpServletResponse response) throws IOException {
            String name = request.getParameter("name");
            String info = request.getParameter("info");
            Clazz clazz = new Clazz();
            clazz.setName(name);
            clazz.setInfo(info);
            clazzService.addClazz(clazz);
            response.getWriter().write("success");
        }
        @RequestMapping("/DeleteAllClazz")
        public void DeleteAllClazz(HttpServletRequest request,HttpServletResponse response){
            if(request.getParameterValues("newsId[]")==null) {
                System.out.println("This is null\n");
            }
            else {
                String[] str=request.getParameterValues("newsId[]");
                List<Integer> ids = new ArrayList<>();
                for(String string: str){
                    //int id = Integer.valueOf(string);
                    //System.out.println(id);
                    Integer id = Integer.parseInt(string);
                    ids.add(id);

                }

                    try {
                        clazzService.deleteClazzAll(ids);
                        response.getWriter().write("success");

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


            }
        }

        @RequestMapping("/DeleteClazz")
        public void DeleteClazz(HttpServletRequest request,HttpServletResponse response){
            Integer id = Integer.parseInt(request.getParameter("clazzid"));

                try {
                    clazzService.deleteClazz(id);
                    response.getWriter().write("success");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
                }


        }
}
