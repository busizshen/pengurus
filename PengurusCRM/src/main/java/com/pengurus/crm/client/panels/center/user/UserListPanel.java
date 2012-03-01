package com.pengurus.crm.client.panels.center.user;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.pengurus.crm.client.models.UserModel;

public class UserListPanel extends BaseUsersListPanel<UserModel> {

	public UserListPanel() {
		
	}
	
	@Override
	protected GridCellRenderer<UserModel> getButtonRenderer() {
		GridCellRenderer<UserModel> buttonRenderer = new GridCellRenderer<UserModel>() {

			private boolean init;

			public Object render(final UserModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					ListStore<UserModel> store, Grid<UserModel> grid) {
				if (!init) {
					init = true;
					grid.addListener(Events.OnClick,
							new Listener<GridEvent<UserModel>>() {

								public void handleEvent(
										GridEvent<UserModel> be) {
									for (int i = 0; i < be.getGrid().getStore()
											.getCount(); i++) {
										if (be.getGrid().getView()
												.getWidget(i, be.getColIndex()) != null
												&& be.getGrid()
														.getView()
														.getWidget(
																i,
																be.getColIndex()) instanceof BoxComponent) {
											((BoxComponent) be
													.getGrid()
													.getView()
													.getWidget(i,
															be.getColIndex()))
													.setWidth(be.getWidth() - 10);
										}
									}
								}
							});
				}

				Button editButton = new Button("Edit",
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								MessageBox.info("a", model.getUserDTO().getUsername(), null);
							}
						});

				editButton.setWidth(grid.getColumnModel().getColumnWidth(
						colIndex) - 10);
				editButton.setToolTip("Click to edit");
				return editButton;
			}
		};
		return buttonRenderer;
	}

	@Override
	protected String getName() {
		return "User list";
	}

	@Override
	protected ListStore<UserModel> getList() {
		return new ListStore<UserModel>();
	}
	
}
