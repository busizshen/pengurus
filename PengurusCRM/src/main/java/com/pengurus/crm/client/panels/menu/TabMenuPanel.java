package com.pengurus.crm.client.panels.menu;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.Widget;

public abstract class TabMenuPanel extends ContentPanel {
  protected String buttonWidth = "100%";
  protected List<Widget> widgetList = new ArrayList<Widget>();
  
  public TabMenuPanel(String heading) {
	  setHeading(heading);
	  setPanelStyle();
  }
  
  public void add(Button b) {
	  widgetList.add(b);
	  setButtonStyle(b);
	  super.add(b);
  }
  
  public boolean isEmpty() {
	  return widgetList.isEmpty();
  }
  
  protected void setButtonStyle(Button b){
	  b.setWidth(buttonWidth);
  }
  
  protected void setPanelStyle(){
	  setBodyStyleName("pad-text");  
	  setAutoHeight(true);
  }
}
