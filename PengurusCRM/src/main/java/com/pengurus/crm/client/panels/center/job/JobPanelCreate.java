package com.pengurus.crm.client.panels.center.job;

import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pengurus.crm.client.models.CurrencyModel;
import com.pengurus.crm.client.models.TranslationModel;
import com.pengurus.crm.client.panels.center.DescriptionPanel;
import com.pengurus.crm.client.panels.center.administration.translation.TranslationsListPanel;
import com.pengurus.crm.client.service.AdministrationService;
import com.pengurus.crm.client.service.AdministrationServiceAsync;
import com.pengurus.crm.shared.dto.CurrencyTypeDTO;
import com.pengurus.crm.shared.dto.JobDTO;
import com.pengurus.crm.shared.dto.TranslationDTO;

public class JobPanelCreate {
	private JobDTO jobDTO;
	protected Listener<DomEvent> listenerCreateJob;
	private Listener<DomEvent> listenerClose;
	private ListStore<CurrencyModel> listCurrencyModel;
	private ListStore<TranslationModel> listTranslationModel;

	public JobPanelCreate() {
		listCurrencyModel = new ListStore<CurrencyModel>();
		listTranslationModel = new ListStore<TranslationModel>();
		
		AsyncCallback<Set<CurrencyTypeDTO>> callback = new AsyncCallback<Set<CurrencyTypeDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<CurrencyTypeDTO> result) {
				for (CurrencyTypeDTO c : result)
					listCurrencyModel.add(new CurrencyModel(c));
			}
		};
		AdministrationServiceAsync service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getCurrency(callback);
		AsyncCallback<Set<TranslationDTO>> callback2 = new AsyncCallback<Set<TranslationDTO>>() {

			public void onFailure(Throwable t) {
				Window.Location.assign("/spring_security_login");
			}

			public void onSuccess(Set<TranslationDTO> result) {
				for (TranslationDTO c : result)
					listTranslationModel.add(new TranslationModel(c));
			}
		};
	 service = (AdministrationServiceAsync) GWT
				.create(AdministrationService.class);
		service.getTranslations(callback2);
	}

	public JobDTO getJobDTO() {
		return jobDTO;
	}

	public JobPanelInfo getPanel(Listener<DomEvent> listenerClose,
			Listener<DomEvent> listenerCreateJob) {
		this.listenerCreateJob = listenerCreateJob;
		this.listenerClose = listenerClose;
		JobPanelInfo jobPanel = new JobPanelInfo();
		return jobPanel;
	}

	public JobPanelInfo getPanel(/* Listener<DomEvent> listenerCreateJob */) {
		// this.listenerCreateJob = listenerCreateJob;
		JobPanelInfo jobPanel = new JobPanelInfo();
		return jobPanel;
	}

	public class JobPanelInfo extends LayoutContainer {

		private VerticalPanel vp;
		private FormData formData;

		public JobPanelInfo() {
			formData = new FormData("-20");
			vp = new VerticalPanel();
			vp.setSpacing(10);
			createForm1();
			add(vp);
		}

		private void createForm1() {
			FormPanel simple = new FormPanel();
			simple.setHeading("Job Panel");
			simple.setFrame(true);
			simple.setAutoHeight(true);
			simple.setAutoWidth(true);

			ComponentPlugin plugin = new ComponentPlugin() {
				public void init(Component component) {
					component.addListener(Events.Render,
							new Listener<ComponentEvent>() {
								public void handleEvent(ComponentEvent be) {
									El elem = be.getComponent().el()
											.findParent(".x-form-element", 3);
									// should style in external CSS rather than
									// directly
									elem.appendChild(XDOM
											.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>"
													+ be.getComponent()
															.getData("text")
													+ "</div>"));
								}
							});
				}
			};

			DateField date = new DateField();
			date.setFieldLabel("Deadline");
			date.addPlugin(plugin);
			date.setAllowBlank(false);
			date.setData("text", "Enter your birthday");
			simple.add(date, formData);

			/*
			 * List<Stock> stocks = TestData.getStocks();
			 * Collections.sort(stocks, new Comparator<Stock>() { public int
			 * compare(Stock arg0, Stock arg1) { return
			 * arg0.getName().compareTo(arg1.getName()); } });
			 */
			TranslationsListPanel translations = new TranslationsListPanel(listTranslationModel);
			simple.add(translations);

			// HorizontalPanel hp = new HorizontalPanel();
			TextField<Long> amount = new TextField<Long>();
			amount.setFieldLabel("Amount");
			amount.setAllowBlank(false);
			amount.addPlugin(plugin);
			amount.setData("text", "Enter your amount and choose Currnecy");
			simple.add(amount);

			ComboBox<CurrencyModel> combo = new ComboBox<CurrencyModel>();
			combo.setFieldLabel("Currency");
			combo.setDisplayField("currency");
			combo.setTriggerAction(TriggerAction.ALL);
			combo.setStore(listCurrencyModel);
			combo.addPlugin(plugin);
			combo.setData("text", "Choose Language");
			combo.setAllowBlank(false);
			simple.add(combo, formData);

			DescriptionPanel descr = new DescriptionPanel();
			simple.add(descr, formData);

			Button b = new Button("Submit");
			simple.addButton(b);
			Button c = new Button("Cancel");
			c.addListener(Events.OnClick, listenerClose);
			simple.addButton(c);

			simple.setButtonAlign(HorizontalAlignment.CENTER);

			FormButtonBinding binding = new FormButtonBinding(simple);
			binding.addButton(b);

			vp.add(simple);
		}

		/*
		 * private ListStore<TranslationModel> getTranslations() {
		 * 
		 * ListStore<TranslationModel> list = new
		 * ListStore<TranslationTypeModel>(); return list; }
		 */

	}
}
