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
	private final static String CLASS_PATH = System.getProperty(
			"java.class.path").split(":")[0];
	private final static String IMPORT_PATH = CLASS_PATH.concat("/../www/WEB-INF/classes/import.sql");
	private final static String IMPORT_BACKUP_PATH = CLASS_PATH
			.concat("/../www/WEB-INF/classes/.import.sql");
	/**
	 * xml file with context which is read, default: dao-beans.xml
	 */
	private final static String XML_CONFIG_PATH = CLASS_PATH
			.concat("/../www/WEB-INF/test-context.xml");

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
			System.out.println("class path: " + CLASS_PATH);
			System.out.println("refreshiing DB");
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
			System.out.println("generating scrypt");
			System.out.println("backuping");
			ourInstance.backupScrypt();
			System.out.println("creating new one");
			ourInstance.createNewScript();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * changes mode of DB to that passed in argument.
	 * Please make notice that it works with xml defined in XML_CONFIG_PATH
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
		String importContent = ioAssistance.readFromFile(IMPORT_PATH);
		ioAssistance.writeToFile(IMPORT_BACKUP_PATH, importContent);
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
