package com.pengurus.crm.client.panels.center.user.worker;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.DualListField.Mode;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.pengurus.crm.client.models.UserModel;
import com.pengurus.crm.client.panels.center.user.create.UserPreviewPanel;
import com.pengurus.crm.shared.dto.UserDTO;

public class WorkerPanelChoose extends FormPanel {
	/* to trzeba zmienić dla translatora zeby sie jego jezyki wyswietlaly */
	private ListStore<UserModel> store;
	private ListStore<UserModel> storeTo;

	public WorkerPanelChoose(String name) {
		this.setHeading(name);
		final DualListField<UserModel> lists = new DualListField<UserModel>();

		lists.setMode(Mode.INSERT);
		lists.setFieldLabel(name);
		
		ListField<UserModel> from = lists.getFromList();
		from.setDisplayField("fullName");
		lists.getToList()
				.getListView()
				.getSelectionModel()
				.addListener(Events.SelectionChange,
						new Listener<SelectionChangedEvent<UserModel>>() {
							@Override
							public void handleEvent(
									SelectionChangedEvent<UserModel> be) {
								if (be.getSelectedItem() != null)
									showUser(be.getSelectedItem().getUserDTO());
							}
						});
		/*
		 * lists.getFromList().getListView().getSelectionModel().addListener(Events
		 * .SelectionChange, new Listener<SelectionChangedEvent<UserModel> >() {
		 * 
		 * @Override public void handleEvent(SelectionChangedEvent<UserModel>
		 * be) { showUser(be.getSelectedItem().getUserDTO()); } });
		 */

		store = new ListStore<UserModel>();

		from.setStore(store);
		ListField<UserModel> to = lists.getToList();
		to.setDisplayField("fullName");
		storeTo = new ListStore<UserModel>();

		to.setStore(storeTo);

		add(lists, new FormData("98%"));

	}

	protected void showUser(UserDTO userDTO) {
		final Window window = new Window();
		window.setAutoHeight(true);
		window.setAutoWidth(true);
		UserPreviewPanel userPanel = new UserPreviewPanel(userDTO);
		window.add(userPanel);
		window.addButton(new Button("OK", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				window.hide();
			}
		}));
		window.show();
	}

	public void init(List<UserModel> models, List<UserModel> modelsTo) {
		List<UserModel> removeModels = new ArrayList<UserModel>();
		for (UserModel userModelsTo : modelsTo) {
			for (UserModel userModels : models) {
				if (userModels.equals(userModelsTo)) {
					removeModels.add(userModels);
				}
			}
		}
		models.removeAll(removeModels);
		store.add(models);
		storeTo.add(modelsTo);
	}

	public List<UserModel> getResult() {
		return storeTo.getModels();
	}
}
