package com.forerunner.core.javatest;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class ToolsRunSql {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Test
	public void importSpace() throws Exception {
		DefaultResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("data.sql");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copy(new InputStreamReader(resource.getInputStream()), outputStream);
		String data = new String(outputStream.toByteArray());
		String[] sqls = data.split(";");

		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();

		System.out.println(sqls.length);

		for (String query : sqls) {
			ResultSet executeQuery = statement.executeQuery(query);
			while (executeQuery.next()) {
				System.out.println(executeQuery.getString(1));
			}
		}

		statement.executeBatch();
		connection.commit();

		statement.close();
		connection.close();
	}
}
