package com.pengurus.crm.client.panels.center.rating;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.pengurus.crm.client.models.CommentModel;
import com.pengurus.crm.client.panels.center.ListPanel;

public class CommentListPanel extends ListPanel<CommentModel> {

	@Override
	protected List<ColumnConfig> getColumns() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfigMy();
		column = new ColumnConfigMy();
		column.setId("type");
		column.setHeader("Type");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("comment");
		column.setHeader("Comment");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("author");
		column.setHeader("Author");
		configs.add(column);

		column = new ColumnConfigMy();
		column.setId("choose");
		column.setHeader("Choose");
		column.setRenderer(getButtonRenderer());
		configs.add(column);
		return configs;
	}

	@Override
	protected String getName() {
		return "Comments";
	}

	@Override
	protected ListStore<CommentModel> getList() {
		ListStore<CommentModel> comments = new ListStore<CommentModel>();
		return comments;
	}

	@Override
	protected GridFilters getFilters() {
		return new GridFilters();
	}

	@Override
	protected void setStyle(ContentPanel cp) {
		// TODO Auto-generated method stub

	}

	private GridCellRenderer<CommentModel> getButtonRenderer() {

		GridCellRenderer<CommentModel> buttonRenderer = new GridCellRenderer<CommentModel>() {

			private boolean init;

			public Object render(final CommentModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<CommentModel> store, Grid<CommentModel> grid) {
				if (!init) {
					init = true;
				}
				Button b = new Button("DELETE",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
							}
						});
				b.setToolTip("Click to see");
				return b;
			}
		};
		return buttonRenderer;
	}
}
