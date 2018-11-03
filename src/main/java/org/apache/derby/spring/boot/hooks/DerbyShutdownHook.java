package org.apache.derby.spring.boot.hooks;

import org.apache.derby.database.Database;
import org.springframework.core.env.Environment;

public class DerbyShutdownHook extends Thread{
	
	private Database database;
	private Environment environment;
	
	public DerbyShutdownHook(Database database, Environment environment) {
		this.database = database;
		this.environment = environment;
	}

	@Override
	public void run() {
	}
	
}
