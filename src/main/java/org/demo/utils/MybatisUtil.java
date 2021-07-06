package org.demo.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory factory = null;

    static {
        String  config = "mybatis.xml";
        try {
            InputStream  inputStream = Resources.getResourceAsStream(config);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSelSession(){
        SqlSession sqlSession = null;
        if (factory != null){
            sqlSession = factory.openSession(); // 传入true则自动提交事务
        }
        return sqlSession;
    }


}
