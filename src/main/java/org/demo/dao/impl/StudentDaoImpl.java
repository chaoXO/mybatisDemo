package org.demo.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.demo.dao.StudentDao;
import org.demo.entity.QueryParam;
import org.demo.entity.Student;
import org.demo.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {


    @Override
    public Student selectStudentById(Integer id) {
        SqlSession sqlSession = MybatisUtil.getSelSession();
        String  sqlId = "org.demo.dao.StudentDao" + "." + "selectStudentById";
        Student student = sqlSession.selectOne(sqlId,id);
        sqlSession.close();
        return student;
    }

    // 未实现该方法，但是可以通过动态代理使用
    @Override
    public Student selectByEmail(String email) {
        return null;
    }

    @Override
    public List<Student> selectByNameAge(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectByObject(Student student) {
        return null;
    }

    @Override
    public List<Student> selectByQueryParam(QueryParam queryParam) {
        return null;
    }

    @Override
    public List<Student> selectByPosition(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Student> queryStudents(String name) {
        return null;
    }

    @Override
    public List<Student> queryStudentOrderById(String id) {

        return null;
    }

    @Override
    public Map<String, Object> queryMap(Integer id) {
        return null;
    }

    // 用自定义类接受(属性不同)传入值
    @Override
    public List<QueryParam> queryToQP() {
        return null;
    }

    @Override
    public List<Student> selectLikeOne(String name) {
        return null;
    }

    @Override
    public List<Student> selectLikeTwo(String name) {
        return null;
    }

    @Override
    public List<Student> selectForch(List<Integer> idlist) {
        return null;
    }

    @Override
    public List<Student> selectForEach(List<Student> studentList) {
        return null;
    }


    @Override
    public int insertStudent(Student student) {
        // 获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 指定sqlid
        String sqlId = "org.demo.dao.StudentDao" + "." + "insertStudent";
        // 执行sql
        int rows = sqlSession.insert(sqlId,student);
        //提交sql事务
        sqlSession.commit();
        sqlSession.close();
        return rows;
    }

    @Override
    public List<Student> selectStudents() {
        // 获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 指定sqlid
        String sqlId = "org.demo.dao.StudentDao" + "." + "selectStudents";
        // 执行sql
        List<Student> studentList = sqlSession.selectList(sqlId);
        sqlSession.close();
        return studentList;
    }




}
