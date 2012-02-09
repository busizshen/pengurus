package com.pengurus.crm.client;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.EntryPoint;

public class PengurusCRM implements EntryPoint {
	public void onModuleLoad() {
		MessageBox.info("Message", "Hello World!!", null);
	}

	/*
	 * private static final int REFRESH_INTERVAL = 5000;
	 * 
	 * private VerticalPanel mainPanel = new VerticalPanel(); private FlexTable
	 * stocksFlexTable = new FlexTable(); private HorizontalPanel addPanel = new
	 * HorizontalPanel(); private TextBox newSymbolTextBox = new TextBox();
	 * private Button addStockButton = new Button("Add"); private Label
	 * lastUpdatedLabel = new Label(); private List<String> stocks = new
	 * ArrayList<String>();
	 * 
	 * public void onModuleLoad() { createTableForStockData();
	 * assembleAddStockPanel(); assembleMainPanel();
	 * RootPanel.get("stockList").add(mainPanel);
	 * newSymbolTextBox.setFocus(true); addClickHandlerToAddStockButton();
	 * addKeyPressHandlerForNewSymbolTextBox(); createRefreshTimer(); }
	 * 
	 * private void createRefreshTimer() { Timer refreshTimer = new Timer() {
	 * 
	 * @Override public void run() { refreshWatchList(); } };
	 * refreshTimer.scheduleRepeating(REFRESH_INTERVAL); }
	 * 
	 * private void addKeyPressHandlerForNewSymbolTextBox() {
	 * newSymbolTextBox.addKeyPressHandler(new KeyPressHandler() { public void
	 * onKeyPress(KeyPressEvent event) { if (event.getCharCode() ==
	 * KeyCodes.KEY_ENTER) { addStock(); } } }); }
	 * 
	 * private void addClickHandlerToAddStockButton() {
	 * addStockButton.addClickHandler(new ClickHandler() { public void
	 * onClick(ClickEvent event) { addStock(); } }); }
	 * 
	 * protected void addStock() { final String symbol =
	 * newSymbolTextBox.getText().toUpperCase().trim();
	 * if(!isSymbolValid(symbol)) { Window.alert("'" + symbol +
	 * "' is not a valid symbol."); newSymbolTextBox.selectAll(); return; }
	 * if(stocks.contains(symbol)) { return; } addSymbolToTable(symbol);
	 * refreshWatchList(); }
	 * 
	 * private void refreshWatchList() { final double MAX_PRICE = 100.; final
	 * double MAX_PRICE_CHANGE = 0.02;
	 * 
	 * StockPrice[] prices = new StockPrice[stocks.size()]; for (int i = 0; i <
	 * stocks.size(); ++i) { double price = MAX_PRICE * Random.nextDouble();
	 * double change = price * MAX_PRICE_CHANGE * (Random.nextDouble() * 2. -
	 * 1.); prices[i] = new StockPrice(stocks.get(i), price, change); }
	 * updateTable(prices); }
	 * 
	 * private void updateTable(StockPrice[] prices) { for (int i = 0; i <
	 * prices.length; ++i) { updateTable(prices[i]); }
	 * lastUpdatedLabel.setText("Last update : " +
	 * DateTimeFormat.getMediumDateTimeFormat().format(new Date())); }
	 * 
	 * private void updateTable(StockPrice price) { if
	 * (!stocks.contains(price.getSymbol())) { return; }
	 * 
	 * int row = stocks.indexOf(price.getSymbol()) + 1;
	 * 
	 * String priceText =
	 * NumberFormat.getFormat("#,##0.00").format(price.getPrice()); NumberFormat
	 * changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00"); String
	 * changeText = changeFormat.format(price.getChange()); String
	 * changePercentText = changeFormat.format(price.getChangePercent());
	 * 
	 * stocksFlexTable.setText(row, 1, priceText); stocksFlexTable.setText(row,
	 * 2, changeText + " (" + changePercentText + "%)"); }
	 * 
	 * private void addSymbolToTable(final String symbol) { int row =
	 * stocksFlexTable.getRowCount(); stocks.add(symbol);
	 * stocksFlexTable.setText(row, 0, symbol); Button removeStockButton = new
	 * Button("x"); addClickHandlerToRemoveStockBUtton(symbol,
	 * removeStockButton); stocksFlexTable.setWidget(row, 3, removeStockButton);
	 * Window.alert("Symbol has been added"); }
	 * 
	 * private void addClickHandlerToRemoveStockBUtton(final String symbol,
	 * Button removeStockButton) { removeStockButton.addClickHandler(new
	 * ClickHandler() {
	 * 
	 * @Override public void onClick(ClickEvent arg0) { int removedIndex =
	 * stocks.indexOf(symbol); stocks.remove(removedIndex);
	 * stocksFlexTable.removeRow(removedIndex + 1); } }); }
	 * 
	 * private boolean isSymbolValid(final String symbol) { return
	 * symbol.matches("^[0-9A-Z\\.]{1,10}$"); }
	 * 
	 * private void assembleMainPanel() { mainPanel.add(stocksFlexTable);
	 * mainPanel.add(addPanel); mainPanel.add(lastUpdatedLabel); }
	 * 
	 * private void assembleAddStockPanel() { addPanel.add(newSymbolTextBox);
	 * addPanel.add(addStockButton); }
	 * 
	 * private void createTableForStockData() { stocksFlexTable.setText(0, 0,
	 * "Symbol"); stocksFlexTable.setText(0, 1, "Price");
	 * stocksFlexTable.setText(0, 2, "Change"); stocksFlexTable.setText(0, 3,
	 * "Remove"); }
	 */
}
