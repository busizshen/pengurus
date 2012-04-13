package com.pengurus.crm.shared.pagination;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PagingLoadResultHelper<Data> implements IsSerializable {
	
	private int offset, totalLength;
	private List<Data> data;
	
	protected PagingLoadResultHelper() {}
	
	public PagingLoadResultHelper(List<Data> data, int offset, int totalLength) {
		super();
		this.data = data;
		this.offset = offset;
		this.totalLength = totalLength;
	}
	
	public int getOffset() {
		return offset;
	}
	public int getTotalLength() {
		return totalLength;
	}
	public List<Data> getData() {
		return data;
	}
}
