package com.pengurus.crm.client;

import com.google.gwt.i18n.client.Messages;

public interface i18nMessages extends Messages{

	String PleaseChoose(String string, String string2);

	String QuotesList(String fullName);

	String ServerError(String t);

	String Updated(String project);

	String Delete(String project);

	String ListPanel(String projects);

	String ClickTo(String see);

	String ProjectsList(String fullName, String projectManager);

	String Choose(String currency);

	String Enter2(String price, String string);

	String Enter1(String amount);

	String TaskList(String fullName);
	
	String RemoveSelected(String selected);
	
	String RemoveSelectedFem(String selected);
	
	String CreateNew(String newRow);
	
	String CreateNewFem(String newRow);
	
	String ListOf(String list);
	
	String CreateFailed(String type);
	
	String CreateFailedFem(String type);
	
	String CreateSuccess(String type, String name);
		
	String CreateSuccessFem(String type, String name);

	String DeleteFailed(String type);
	
	String DeleteSuccess(String type, String name);
	
	String UploadFailed(String type);
	
	String DependencyException(String type);
	
	String DependencyExceptionFem(String type);

	String Select(String client);
	
}
