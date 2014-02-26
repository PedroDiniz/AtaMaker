package ata.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;

public class test extends Composite{

	private static testUiBinder uiBinder = GWT.create(testUiBinder.class);
	@UiField Button criarAta;
	@UiField Button addPresentes;

	@UiField Button addAusentes;
	@UiField ListBox listBoxPresentes;
	@UiField ListBox listBoxPresidente;
	@UiField ListBox listBoxAteiro;
	@UiField ListBox listBoxAtraso;
	@UiField Button addAtrasado;
	@UiField ListBox listBoxAusentes;
	@UiField ListBox atrasosListBox;
	@UiField ListBox ausentesListBox;
	@UiField ListBox presentesListBox;

	interface testUiBinder extends UiBinder<Widget, test> {
	}

	public test() {
		initWidget(uiBinder.createAndBindUi(this));
		//carregarDados();
		
	}
	
	public void carregarDados(){
		listBoxPresidente.setEnabled(false);
		listBoxAtraso.setEnabled(false);
		listBoxAusentes.setEnabled(true);
		listBoxAteiro.setEnabled(false);
		addAusentes.setEnabled(false);
		addAtrasado.setEnabled(false);
		for(String s : Ata.listaReuniao){
			listBoxPresentes.addItem(s);
		}
	}
	@UiHandler("presentesListBox")
	void onListBoxAusentesClick(ClickEvent event) {
		listBoxPresidente.setEnabled(true);
		listBoxAtraso.setEnabled(true);
		listBoxAteiro.setEnabled(true);
		addAusentes.setEnabled(true);
		addAtrasado.setEnabled(true);
		for(int i=0; i<listBoxPresentes.getItemCount(); i++){
			listBoxAusentes.addItem(listBoxPresentes.getItemText(i));
		}
		for(int i=0; i<presentesListBox.getItemCount(); i++){
			listBoxPresidente.addItem(presentesListBox.getItemText(i));
			listBoxAtraso.addItem(presentesListBox.getItemText(i));
			listBoxAteiro.addItem(presentesListBox.getItemText(i));
		}
	}
	
	@UiHandler("criarAta")
	void onCriarAtaClick(ClickEvent event) {
		if(presentesListBox.getItemCount()==0){
			Window.alert("Nenhuma pessoa adicionada na lista de presentes");
		}else if(listBoxPresidente.getSelectedIndex()==-1){
			Window.alert("Nenhum presidente selecionado");
		}else if(listBoxAteiro.getSelectedIndex()==-1){
			Window.alert("Nenhum ateiro selecionado");
		}else{
			if(Ata.stack.getWidgetCount()==2){
				TelaAta t = new TelaAta();
				Ata.stack.add(t, "Criar Ata", true);
			}
			Ata.stack.showStack(2);
		}
	}
	@UiHandler("addPresentes")
	void onAddPresentesClick(ClickEvent event) {
		String itemSelecionado = listBoxPresentes.getValue(listBoxPresentes.getSelectedIndex());
		presentesListBox.addItem(itemSelecionado);
		listBoxPresentes.removeItem(listBoxPresentes.getSelectedIndex());
	}
	@UiHandler("addAusentes")
	void onAddAusentesClick(ClickEvent event) {
		String itemSelecionado = listBoxAusentes.getValue(listBoxAusentes.getSelectedIndex());
		ausentesListBox.addItem(itemSelecionado);
		listBoxAusentes.removeItem(listBoxAusentes.getSelectedIndex());
	}
	@UiHandler("addAtrasado")
	void onAddAtrasadoClick(ClickEvent event) {
		String itemSelecionado = listBoxAtraso.getValue(listBoxAtraso.getSelectedIndex());
		atrasosListBox.addItem(itemSelecionado);
		listBoxAtraso.removeItem(listBoxAtraso.getSelectedIndex());
	}
	
	public String getPresentes(){
		String retorno = ""; 
		for(int i=0; i<presentesListBox.getItemCount(); i++){
			if(i==0){
				retorno = retorno + presentesListBox.getItemText(i);
			}else{
				retorno = retorno + ", " + presentesListBox.getItemText(i);
			}
		}
		return retorno;
	}
	
	public String getAusentes(){
		String retorno = ""; 
		for(int i=0; i<ausentesListBox.getItemCount(); i++){
			if(i==0){
				retorno = retorno + ausentesListBox.getItemText(i);
			}else{
				retorno = retorno + ", " + ausentesListBox.getItemText(i);
			}
		}
		return retorno;
	}
	
	public String getAtrasos(){
		String retorno = ""; 
		for(int i=0; i<atrasosListBox.getItemCount(); i++){
			if(i==0){
				retorno = retorno + atrasosListBox.getItemText(i);
			}else{
				retorno = retorno + ", " + atrasosListBox.getItemText(i);
			}
		}
		return retorno;
	}
	
	public String getPresidente(){
		return listBoxPresidente.getItemText(listBoxPresidente.getSelectedIndex());
	}
	
	public String getAteiro(){
		return listBoxAteiro.getItemText(listBoxAteiro.getSelectedIndex());
	}
}
