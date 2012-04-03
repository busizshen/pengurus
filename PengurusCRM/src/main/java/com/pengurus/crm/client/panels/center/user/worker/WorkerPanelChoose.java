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
import com.pengurus.crm.client.panels.center.user.UserPanel.UserViewInfo;
import com.pengurus.crm.shared.dto.UserDTO;
import com.pengurus.crm.shared.dto.WorkerDTO;

public class WorkerPanelChoose extends FormPanel {
/*to trzeba zmienić dla translatora zeby sie jego jezyki wyswietlaly*/
	private ListStore<UserModel> store;
	private ListStore<UserModel> storeTo;
	public WorkerPanelChoose(String name){
		this.setCollapsible(true);
		this.setAnimCollapse(true);
	    this.setHeading(name);
		final DualListField<UserModel> lists = new DualListField<UserModel>();  

	    lists.setMode(Mode.INSERT);  
	    lists.setFieldLabel(name); 

	    
		ListField<UserModel> from = lists.getFromList();  
	    from.setDisplayField("fullName"); 
	    lists.getToList().getListView().getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<UserModel> >() {
	        @Override
	         public void handleEvent(SelectionChangedEvent<UserModel> be) {
	        	showUser(be.getSelectedItem().getUserDTO());
	        }
	    });
/*	    lists.getFromList().getListView().getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<UserModel> >() {
	        @Override
	         public void handleEvent(SelectionChangedEvent<UserModel> be) {
	        	showUser(be.getSelectedItem().getUserDTO());
	        }
	    });*/


	    store = new ListStore<UserModel>(); 
	    
	    from.setStore(store);  
	    ListField<UserModel> to = lists.getToList();  
	    to.setDisplayField("fullName");  
	    storeTo = new ListStore<UserModel>();

	    to.setStore(storeTo);  
	    
	    add(lists, new FormData("98%")); 

	}

	protected void showUser(UserDTO userDTO) {
		final Window w = new Window();
    	w.setAutoHeight(true);
    	w.setAutoWidth(true);
    	WorkerPanelView workerPanel = new WorkerPanelView((WorkerDTO) userDTO);
    	UserViewInfo panel = workerPanel.getInfoPanel();
    	panel.setCollapsible(false);
    	panel.expand();
    	w.add(panel);
    	w.addButton(new Button("OK", new SelectionListener<ButtonEvent>(){
			@Override
			public void componentSelected(ButtonEvent ce) {
				w.hide();
			}}));
    	w.show();
      }
	

	public void init(List<UserModel> models,List<UserModel> modelsTo){
		List<UserModel> removeModels = new ArrayList<UserModel>();
		for(UserModel userModelsTo : modelsTo){
			for(UserModel userModels : models){
				if(userModels.compare(userModelsTo)){
					removeModels.add(userModels);
				}
			}
		}
		models.removeAll(removeModels);
	    store.add(models);  
	    storeTo.add(modelsTo);  
	}
	
	public List<UserModel> getResult(){
		return storeTo.getModels();
	}
}
