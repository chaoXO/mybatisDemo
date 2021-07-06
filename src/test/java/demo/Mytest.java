package demo;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.dao.StudentDao;
import org.demo.dao.impl.StudentDaoImpl;
import org.demo.entity.QueryParam;
import org.demo.entity.Student;
import org.demo.utils.MybatisUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mytest {

    @Test
    public void testsql() throws IOException {
        // 定义mybatis配置文件
        String resource = "mybatis.xml";
        // 读取配置文件,调用mybatis中的Resource类
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
            // 指定要执行的sql语句标识
            String sqlId = "org.demo.dao.StudentDao" + "." + "selectStudentById";
            Student student = (Student)session.selectOne(sqlId,11);
            System.out.println(student);

        }
    }

    @Test
    public void testsql2() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
            // 指定要执行的sql语句标识
            String sqlId = "org.demo.dao.StudentDao" + "." + "insertStudent";
            //传入实体参数
            Student student = new Student(28,"六六","2525@qq.com",29);
            int rows = session.insert(sqlId,student);
            // mybatis默认手工提交事务,所以在 insert/update/delete 后要手工提交事务
            session.commit();
            System.out.println("添加一个学生,影响了行:" + rows);

        }
    }

    // 使用工具类获取sqlsession
    @Test
    public void test4(){
        // 获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 指定sqlid
        String sqlId = "org.demo.dao.StudentDao" + "." + "selectStudentById";
        // 执行sql
        Student student = sqlSession.selectOne(sqlId,28);
        System.out.println(student);
        //关闭sqlsession
        sqlSession.close();
    }

    @Test
    public void test5(){
        // 获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 指定sqlid
        String sqlId = "org.demo.dao.StudentDao" + "." + "selectStudents";
        // 执行sql
        List<Student> studentList = sqlSession.selectList(sqlId);
        for (Student student:studentList) {
            System.out.println(student);
        }
        //关闭sqlsession
        sqlSession.close();
    }

    @Test
    public void test6(){
        // 获取sqlsession
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 指定sqlid
        String sqlId = "org.demo.dao.StudentDao" + "." + "insertStudent";
        // 执行sql
        int rows = sqlSession.insert(sqlId,new Student(110,"李白","99@qq.com",70));
        //提交sql事务
        sqlSession.commit();
        System.out.println("insert的行数rows = " +rows);
        //关闭sqlsession
        sqlSession.close();
    }

    // 实现Dao接口 调用DaoImol实现类
    @Test
    public void tset7(){
        // 类口类型 = 实现类
        StudentDao studentDao = new StudentDaoImpl();
        Student student = studentDao.selectStudentById(110);
        System.out.println(student);
    }

    @Test
    public void test8(){
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> studentList = studentDao.selectStudents();

        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void test9(){
        StudentDao studentDao = new StudentDaoImpl();
        studentDao.insertStudent(new Student(99,"测测","cece@qq.com",38));

    }

    // 使用mybatis动态代理写法
    @Test
    public void test10(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        // 获取DAO的代理
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = studentDao.selectStudents();

        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void test11(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = studentDao.selectByEmail("lili@qq.com");
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void test12(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = studentDao.selectByNameAge("李白",22);

        studentList.forEach(student -> System.out.println(student));

    }

    @Test
    public void test13(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = studentDao.selectByObject(new Student(null,"李白",null,11));
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();

    }

    @Test
    public void test14(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        QueryParam queryParam = new QueryParam();
        queryParam.setP1("李白");
        queryParam.setP2(29);
        List<Student> studentList = studentDao.selectByQueryParam(queryParam);
        studentList.forEach(student -> System.out.println("学生信息 ："+student));
        sqlSession.close();

    }

    @Test
    public void test15(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = studentDao.selectByPosition("李白",38);
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();

    }

    // 使用 map传参
    @Test
    public void test16(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("myname","赵四");
        map.put("myage",70);

        List<Student> studentList = studentDao.selectByMap(map);
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();

    }

    // $占位符测试
    // 需要加引号 否则无法识别 为字符串
    // 不做类型改变
    @Test
    public void test17(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        // 有注入风险
        List<Student> studentList = studentDao.queryStudents("'李白' or 1=1");
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void test18(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        // 有注入风险
        List<Student> studentList = studentDao.queryStudentOrderById("age");
        studentList.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    // 返回map
    // 返回结果不能超过一行
    @Test
    public void test19(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        // 有注入风险
        Map<String,Object> map = studentDao.queryMap(99);
        System.out.println("Map ==" +map);
        sqlSession.close();
    }

    //返回对象映射到 属性值不相同的 自定义对象中
    @Test
    public void test20(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<QueryParam> queryParams = studentDao.queryToQP();
        queryParams.forEach(queryParam -> System.out.println(queryParam));
        sqlSession.close();
    }

    //模糊查询
    @Test
    public void test21(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList  = studentDao.selectLikeOne("%李%");
        studentList.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }

    //模糊查询2Two
    @Test
    public void test22(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList  = studentDao.selectLikeTwo("李");
        studentList.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }

    // foreach用法
    @Test
    public void test23(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(11);
        list.add(28);
        // 传null的话,则是遍历所有
        // list = null;
        List<Student> studentList  = studentDao.selectForch(list);
        studentList.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }

    // foreach用法(传入 对象的用法)
    @Test
    public void test24(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        List<Student> list = new ArrayList<>();
        list.add(new Student(10,null,null,null));
        list.add(new Student(11,null,null,null));
        list.add(new Student(28,null,null,null));
        // 传null的话,则是遍历所有
        // list = null;
        List<Student> studentList  = studentDao.selectForEach(list);
        studentList.forEach(stu -> System.out.println(stu));
        sqlSession.close();
    }

    // 测试 分页 插件
    @Test
    public void test25(){
        SqlSession sqlSession = MybatisUtil.getSelSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

        //调用分页插件
        PageHelper.startPage(1,3);

        List<Student> studentList = studentDao.selectStudents();
        studentList.forEach(student -> System.out.println(student));

        //

    }

}
