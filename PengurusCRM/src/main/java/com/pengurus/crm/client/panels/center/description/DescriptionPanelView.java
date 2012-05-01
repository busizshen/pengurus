package com.pengurus.crm.client.panels.center.description;

public class DescriptionPanelView extends DescriptionPanel{

	public DescriptionPanelView(String descr, Integer height ,Integer width){
		super(descr,height,width);
		description.setReadOnly(true);
	}
}
