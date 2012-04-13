package com.pengurus.crm.shared.pagination;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PagingLoadConfigHelper implements IsSerializable {

	String sortField;
	int offset, limit;
	String sortDir;
	
	protected PagingLoadConfigHelper() {}
	
	public PagingLoadConfigHelper(String sortField, String sortDir, int offset, int limit) {
		super();
		this.sortField = sortField;
		this.sortDir = sortDir;
		this.offset = offset;
		this.limit = limit;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}	
}
