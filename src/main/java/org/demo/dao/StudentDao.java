package org.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.demo.entity.QueryParam;
import org.demo.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    // 查询一个学生
    Student selectStudentById(Integer id);
    // 通过邮箱查找一个学生
    Student selectByEmail(String email);
    // 多个参数查找
    List<Student> selectByNameAge(@Param(value = "myname") String name,@Param(value = "myage") Integer age);
    // 传入对象查找
    List<Student> selectByObject(Student student);
    //传入自定义对象查找
    List<Student> selectByQueryParam(QueryParam queryParam);
    // 按传入位置传参
    List<Student> selectByPosition(String name,Integer age);
    // 用map传参
    List<Student> selectByMap(Map<String,Object> map);

    // $占位符的使用 需要配合Param
    List<Student> queryStudents(@Param(value = "name") String name);

    // $ 查询id排序
    List<Student>  queryStudentOrderById(@Param(value = "id") String id);
    //  查询返回map
    Map<String,Object> queryMap(Integer id);

    // 查询所有学生用 自定义数据类型接受
    List<QueryParam> queryToQP();
    // 模糊查询
    List<Student> selectLikeOne(String name);
    // 模糊查询2
    List<Student> selectLikeTwo(String name);
    // mybatis foreach用法
    List<Student> selectForch(List<Integer> idlist);
    List<Student> selectForEach(List<Student> studentList);



    // 添加学生
    // int :返回本次操作影响的数据库行数
    int insertStudent(Student student);

    List<Student> selectStudents();
}
