package com.forerunner.core.javatest;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ToolsHibernateSchemaExport {

	public static void main(String[] args) {
		ToolsHibernateSchemaExport.d20140516();
	}

	public static void d20140516() {
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(ToolsHibernateSchemaExport.class);

		SchemaExport schemaExport = new SchemaExport(configuration);
		schemaExport.create(true, false);
	}
}
