package com.how2java.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
/**
 * ясЁы╪сть
 * @author Administrator
 *
 */
public class TestMybatis02_LazyLoad {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		CategoryMapper mapper = session.getMapper(CategoryMapper.class);
		List<Category> cs = mapper.list();
		for (Category c : cs) {
			System.out.println(c.getName());
			List<Product> ps = c.getProducts();
			for (Product p : ps) {
				System.out.println("\t" + p.getName());
			}
		}

		session.commit();
		session.close();
	}
}
