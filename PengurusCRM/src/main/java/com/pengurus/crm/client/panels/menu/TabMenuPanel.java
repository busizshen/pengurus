package com.pengurus.crm.client.panels.menu;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public abstract class TabMenuPanel implements TabMenuPanelInterface {
  protected ContentPanel panel;
  protected String buttonWidth = "100%";
  
  protected void setButtonStyle(Button b){
	  b.setWidth(buttonWidth);
  }
  
  protected void setPanelStyle(){
	  panel.setBodyStyleName("pad-text");  
	  panel.setAutoHeight(true);
  }
}
