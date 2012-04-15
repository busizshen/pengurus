package com.pengurus.crm.client.panels.center.user.create;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ColumnModelEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.pengurus.crm.client.models.StringModel;

public class StringGrid extends ContentPanel{
    
    private Grid<StringModel> grid;
    protected final ListStore<StringModel> store;

    protected void doAutoHeight() {
        if (grid.isViewReady()) {
            grid.getView().getScroller()
                    .setStyleAttribute("overflowY", "hidden");
            setHeight((grid.getView().getBody().isScrollableX() ? 19 : 0)
                    + grid.el().getFrameWidth("tb")
                    + grid.getView().getHeader().getHeight() + getFrameHeight()
                    + grid.getView().getBody().firstChild().getHeight());
        }
    }

    public StringGrid(final String header, final String label) {
        
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("value");
        column.setHeader(header);
        configs.add(column);

        store = new ListStore<StringModel>();

        ColumnModel cm = new ColumnModel(configs);

        setFrame(false);
        setLayout(new FitLayout());

        grid = new EditorGrid<StringModel>(store, cm);
        grid.setAutoExpandColumn("value");
        setHeaderVisible(false);
        grid.setBorders(false);
        add(grid);

        setButtonAlign(HorizontalAlignment.CENTER);

        grid.getStore().addStoreListener(new StoreListener<StringModel>() {
            @Override
            public void storeUpdate(StoreEvent<StringModel> se) {
                super.storeUpdate(se);
                store.commitChanges();
            }
        });

        grid.addListener(Events.ViewReady, new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent be) {
                grid.getStore().addListener(Store.Add,
                        new Listener<StoreEvent<StringModel>>() {
                            public void handleEvent(StoreEvent<StringModel> be) {
                                doAutoHeight();
                            }
                        });
                doAutoHeight();
            }
        });
        grid.addListener(Events.ColumnResize, new Listener<ComponentEvent>() {
            public void handleEvent(ComponentEvent be) {
                doAutoHeight();
            }
        });
        grid.getColumnModel().addListener(Events.HiddenChange,
                new Listener<ColumnModelEvent>() {
                    public void handleEvent(ColumnModelEvent be) {
                        doAutoHeight();
                    }
                });
        
    }
    
    
    public void setData(Set<String> set) {
        if (set == null) return;
        for (String data: set) {
            store.add(new StringModel(data));
        }
    }
    
    public Set<String> getData() {
        Set<String> data = new HashSet<String>();
        for (StringModel model: store.getModels()) {
            data.add(model.getValue());
        }
        return data;
    }
    
}
