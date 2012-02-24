package com.pengurus.crm.client.panels.center.user.create;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.Timer;

/**
 * Monitors the valid state of a form and enabled / disabled all buttons.
 */
public class UserFormButtonBinding {

	private List<Field<?>> fields;
	private Timer timer;
	private int interval = 500;
	private Listener<ComponentEvent> listener;
	private List<Button> buttons;
	private ContentPanel panel;

	public UserFormButtonBinding(ContentPanel panel) {
		this.panel = panel;
		
		fields = new ArrayList<Field<?>>();
		buttons = new ArrayList<Button>();
		timer = new Timer() {
			@Override
			public void run() {
				UserFormButtonBinding.this.checkFields();
			}
		};
		listener = new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent be) {
				if (be.getType() == Events.Attach) {
					UserFormButtonBinding.this.startMonitoring();
				} else if (be.getType() == Events.Detach) {
					UserFormButtonBinding.this.stopMonitoring();
				}
			}
		};
		panel.addListener(Events.Attach, listener);
		panel.addListener(Events.Detach, listener);

		if (panel.isAttached()) {
			startMonitoring();
		}

	}

	public void addButton(Button button) {
		buttons.add(button);
	}

	public int getInterval() {
		return interval;
	}

	public void removeButton(Button button) {
		buttons.remove(button);
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void startMonitoring() {
		if (panel.isAttached()) {
			timer.run();
			timer.scheduleRepeating(interval);
		}
	}

	public List<Field<?>> getFields() {
		return fields;
	}
	
	public void stopMonitoring() {
		timer.cancel();
	}
	
	private boolean areValid() {
	    boolean valid = true;
	    for (Field<?> f : fields) {
	      if (!f.isValid(true)) {
	        valid = false;
	      }
	    }
	    return valid;
	}

	protected boolean checkFields() {
		boolean v = areValid();
		for (Button button : buttons) {
			if (v != button.isEnabled()) {
				button.setEnabled(v);
			}
		}
		return v;
	}

}
