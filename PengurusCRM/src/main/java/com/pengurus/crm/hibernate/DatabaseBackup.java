package com.pengurus.crm.hibernate;

import java.io.*;

public class DatabaseBackup {

	/**
	 * Enumerator describing what type of mode we want to convert to
	 */
	private enum DBMode {

		update, create;

		public String toString() {
			if (this == update) {
				return "hibernate.hbm2ddl.auto=update";
			} else {
				return "hibernate.hbm2ddl.auto=create";
			}
		}
	}

	/**
	 * bunch of constant Strings containing file paths used inside of class
	 */
	//private final static String CLASS_PATH = System.getProperty(
	//		"java.class.path").split(":")[0];
	private final static String PROJECT_PATH = System.getProperty("user.dir");
	private final static String IMPORT_PATH = PROJECT_PATH
			.concat("/target/www/WEB-INF/classes/import.sql");
	private final static String IMPORT_BACKUP_PATH = PROJECT_PATH
			.concat("/target/www/WEB-INF/classes/.import.sql");
	private final static String IMPORT_RESOURCES_PATH = PROJECT_PATH
			.concat("/src/main/resources/import.sql");
	private final static String IMPORT_RESOURCES_BACKUP_PATH = PROJECT_PATH
			.concat("/src/main/resources/.import.sql");
	/**
	 * xml file with context which is read, default: dao-beans.xml
	 */
	private final static String XML_CONFIG_PATH = PROJECT_PATH
			.concat("/target/www/WEB-INF/test-context.xml");

	private IOAssistance ioAssistance = IOAssistance.getInstance();

	/**
	 * singleton of DatabaseBackup class
	 */
	private final static DatabaseBackup ourInstance = new DatabaseBackup();

	public static DatabaseBackup getInstance() {
		return ourInstance;
	}

	private DatabaseBackup() {

	}

	/**
	 * function prepares the latest backuped version of database to be imported
	 * from import.sql file
	 */
	public void refreshDatabase() {
		try {
			ioAssistance.copyFile(IMPORT_RESOURCES_PATH, IMPORT_PATH);
			ourInstance.changeModeTo(DBMode.create);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * function generates scrypt which can restore current content of database
	 */
	public void generateScrypt() {
		try {
			ourInstance.backupScrypt();
			ourInstance.createNewScript();
			ioAssistance.copyFile(IMPORT_PATH, IMPORT_RESOURCES_PATH);
			ioAssistance.copyFile(IMPORT_BACKUP_PATH, IMPORT_RESOURCES_BACKUP_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * changes mode of DB to that passed in argument. Please make notice that it
	 * works with xml defined in XML_CONFIG_PATH
	 * 
	 * @param mode
	 * @throws IOException
	 */
	private void changeModeTo(DBMode mode) throws IOException {
		String daoBeansContent = ioAssistance.readFromFile(XML_CONFIG_PATH);
		System.out.println("changing mode");
		if (mode == DBMode.create)
			daoBeansContent = daoBeansContent.replaceAll(
					DBMode.update.toString(), DBMode.create.toString());
		else
			daoBeansContent = daoBeansContent.replaceAll(
					DBMode.create.toString(), DBMode.update.toString());
		System.out.println("writing new mode to file");
		System.out.println(daoBeansContent);
		ioAssistance.writeToFile(XML_CONFIG_PATH, daoBeansContent);
	}

	/**
	 * function is responsible for creating backup (.import.sql) file of
	 * import.sql
	 * 
	 * @throws IOException
	 */
	private void backupScrypt() throws IOException {
		ioAssistance.copyFile(IMPORT_PATH, IMPORT_BACKUP_PATH);
	}

	private void createNewScript() throws IOException {
		try {
			Process p = Runtime.getRuntime().exec(
					"pg_dump --data-only --attribute-inserts -U postgres");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String newScriptContent = "", line = "";
			while ((line = stdInput.readLine()) != null) {
				newScriptContent += line + "\r\n";
			}

			p.waitFor();
			ioAssistance.writeToFile(IMPORT_PATH, newScriptContent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			changeModeTo(DBMode.update);
		}

	}
}
