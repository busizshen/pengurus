package com.pengurus.crm.client.panels.center;


import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.pengurus.crm.client.PengurusCRM;
import com.pengurus.crm.shared.dto.StatusDTO;
import com.pengurus.crm.shared.dto.UserRoleDTO;

public class QuoteStatusPanel extends LayoutContainer {

	private StatusDTO status;
	protected final Listener<DomEvent> listenerGenerateProject;
	private Listener<DomEvent> listenerChangeStatus;
	public QuoteStatusPanel(StatusDTO status, Listener<DomEvent> listener1,Listener<DomEvent> listener2 ) {
		this.status = status;
		this.listenerGenerateProject = listener2;
		this.listenerChangeStatus = listener1;
	}
	
	@Override  
	protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);  
	    setLayout(new FlowLayout(10));  
	    VerticalPanel vp = new VerticalPanel();
	    vp.setHeight("Status panel");
	    QuoteStatusBar ss;
	    if(status != null)
	    	ss = new QuoteStatusBar(status.toInt());
	    else ss = new QuoteStatusBar(0);
	    ss.setBorders(true);
	    ss.setAutoHeight(true);
	    ss.setHorizontalAlign(HorizontalAlignment.CENTER);
	    ss.setVerticalAlign(VerticalAlignment.MIDDLE);
	    vp.add(ss);
	    add(vp);
	}

	
	private class QuoteStatusBar extends HorizontalPanel {  
		  Label[] list = new Label[7];
		  int st = 0;
		  public QuoteStatusBar(int status){
			  this.st = status ;
			  getLabel(0,"open",(status >=1));
			  getLabel(1,"in progress", (status >=2));
			  getLabel(2, "resolved", (status >=3));
			  getLabel(3, "verificated", (status >=4));
			  getLabel(4, "accepted", (status >=5));
			  getLabel(5, "accounted", (status >=6));
			  getLabel(6, "closed", (status == 7));
		  }
		  private void getLabel(int num, String descr, boolean status) {
			  list[num] = new Label();
			  Label l = list[num];
			  l.setBorders(true);
			  if(status)
				  l.setStyleAttribute("background", "green");
			  else
				  l.setStyleAttribute("background", "red");
			  l.setWidth(100);
			  
			  l.setSize(100, 60);
			 
			  l.setText(descr);
			  l.setShadowOffset(windowResizeDelay);
		  }
		@Override  
		  protected void onRender(Element parent, int pos) {  
		    super.onRender(parent, pos);  
		    for(Label l : list){
		    	add(l);
		    }	  
			Set<UserRoleDTO> roles = new HashSet<UserRoleDTO>();
			if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
				Button b = new Button("Generate Project");
				b.addListener(Events.OnClick, listenerGenerateProject);
				add(b);
			}
			roles = new HashSet<UserRoleDTO>();
			if(PengurusCRM.getCurrentUser().haveAuthority(roles)){ 
				Button b = new Button("Next Status",null,  new SelectionListener<ButtonEvent>(){

					@Override
					public void componentSelected(ButtonEvent ce) {
						list[st].setStyleAttribute("background", "green");
						st++;
					}});
			
				b.addListener(Events.OnClick, listenerChangeStatus);
				add(b);
				
			}
				
		}
		 }  

}
