package com.pengurus.crm.hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOAssistance {

	private final static IOAssistance ourInstance = new IOAssistance();

	public static IOAssistance getInstance() {
		return ourInstance;
	}

	private IOAssistance() {

	}
	
	public String readFromFile(String fileName) throws IOException {
		File fileToRead = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(fileToRead));

		String fileContent = "", line = "";
		while ((line = reader.readLine()) != null) {
			fileContent += line + "\r\n";
		}

		reader.close();
		return fileContent;
	}

	public void writeToFile(String fileName, String content)
			throws IOException {
		FileWriter writer = new FileWriter(fileName);
		writer.write(content);
		writer.close();
	}
}
