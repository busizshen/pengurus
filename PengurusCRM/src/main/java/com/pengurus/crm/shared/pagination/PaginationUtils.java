package com.pengurus.crm.shared.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.Model;

public class PaginationUtils {
	
	public static <T extends Model> PagingLoadResultHelper<T> paginate(PagingLoadConfigHelper loadConfig,
			List<T> fullList) {
		
		final String sortField;
		if (loadConfig.getSortField() != null) {
			sortField = loadConfig.getSortField();
		} else {
			sortField = "id";
		}
		
		SortDir sortDir = SortDir.findDir(loadConfig.getSortDir());
		if (sortDir == null) {
			sortDir = SortDir.ASC;
		}
		Collections.sort(fullList, sortDir.comparator(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return new Comparator<T>() {

					@Override
					public int compare(T o1, T o2) {
						if (o1.get(sortField) == null) {
							if (o2.get(sortField) == null) {
								return ((Object)o1.get("id")).toString().compareTo(((Object)o2.get("id")).toString());
							} else {
								return 1;
							}
						} else {
							if (o2.get(sortField) == null) {
								return -1;
							} else {
								return ((Object)o1.get(sortField)).toString().compareTo(((Object)o2.get(sortField)).toString());
							}
						}
					}
					
				}.compare(o1, o2);
			}
		}));
		
		int begin = loadConfig.getOffset();
		int end = Math.min(fullList.size(), begin + loadConfig.getLimit());
		List<T> sublist = new ArrayList<T>();
		for (int i=begin; i<end; ++i) {
			sublist.add(fullList.get(i));
		}
		return new PagingLoadResultHelper<T>(sublist, loadConfig.getOffset(), fullList.size());
	}
}
