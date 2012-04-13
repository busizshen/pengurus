package com.pengurus.crm.shared.pagination;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.extjs.gxt.ui.client.data.BaseModel;

public class PaginationUtilTest {

	private List<BaseModel> testData;
	BaseModel a, b, c;
	
	PagingLoadConfigHelper config;
	
	@Before
	public void prepareTestData() {
		a = new BaseModel();
		a.set("id", 1);
		a.set("value", 3);
		b = new BaseModel();
		b.set("id", 2);
		b.set("value", 1);
		c = new BaseModel();
		c.set("id", 3);
		c.set("value", 2);
		testData = new ArrayList<BaseModel>();
		testData.add(a);
		testData.add(b);
		testData.add(c);
		config = new PagingLoadConfigHelper("value", "ASC", 1, 1);
	}

	@Test
	public void testPagination() {
		// when
		PagingLoadResultHelper<BaseModel> result = PaginationUtil.paginate(config, testData);
		
		// then
		Assert.assertEquals(1, result.getData().size());
		Assert.assertEquals(1, result.getOffset());
		Assert.assertEquals(3, result.getTotalLength());
	}
	
	@Test
	public void testSort() {
		// when
		PagingLoadResultHelper<BaseModel> result = PaginationUtil.paginate(config, testData);
		
		// then
		Assert.assertEquals(c, result.getData().get(0));				
	}
	
	@Test
	public void testDefaultSortField() {
		// given
		config.setSortField(null);

		// when
		PagingLoadResultHelper<BaseModel> result = PaginationUtil.paginate(config, testData);
		
		// then
		Assert.assertEquals(b, result.getData().get(0));
	}
	
	@Test
	public void testDefaultSortDirection() {
		// given
		config.setSortDir(null);
		config.setOffset(0);

		// when
		PagingLoadResultHelper<BaseModel> result = PaginationUtil.paginate(config, testData);
		
		// then
		Assert.assertEquals(b, result.getData().get(0));
	}
	
	@Test
	public void testDescSortDirection() {
		// given
		config.setSortDir("DESC");
		config.setOffset(0);

		// when
		PagingLoadResultHelper<BaseModel> result = PaginationUtil.paginate(config, testData);
		
		// then
		Assert.assertEquals(a, result.getData().get(0));
	}
	
}
