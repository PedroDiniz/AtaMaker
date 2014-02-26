package ata.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.ListBox;

public class TelaCadastro extends Composite {

	public test telaInformacao;
	private static TelaCadastroUiBinder uiBinder = GWT
			.create(TelaCadastroUiBinder.class);
	@UiField ListBox list;
	@UiField TextBox cadastroCaixa;
	@UiField Button okBotao;
	@UiField DatePicker datePicker;
	@UiField TextBox dataCaixa;
	@UiField Button cadastrarBotao;
	public static String dateString;

	interface TelaCadastroUiBinder extends UiBinder<Widget, TelaCadastro> {
	}

	public TelaCadastro() {
		initWidget(uiBinder.createAndBindUi(this));
		dataCaixa.setEnabled(false);
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
		        dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(date);
		        dataCaixa.setText(dateString);
			}
		});
		//list.setVisible(true);
	}
	
	public void carregarLista(){
		ArrayList<String> retorno = new ArrayList<String>();
		for(int i=0; i<list.getItemCount(); i++){
			retorno.add(list.getItemText(i));
		}
		Ata.listaReuniao = retorno;	
	}
	
	
	@UiHandler("okBotao")
	void onOkBotaoClick(ClickEvent event) {
		if(list.getItemCount()==0){
			Window.alert("Nenhuma pessoa cadastrada. Cadastre pessoas para prosseguir!");
		}else if(dataCaixa.getText().length()<2){
			Window.alert("Selecione uma data para prosseguir!");
		}else{
			carregarLista();
		
			if(Ata.stack.getWidgetCount()==1){
				telaInformacao = new test();
				Ata.stack.add(telaInformacao, "Editar Participantes", true);
			}
			telaInformacao.carregarDados();
			Ata.stack.showStack(1);
		}
	}
	@UiHandler("cadastrarBotao")
	void onCadastrarBotaoClick(ClickEvent event) {
		if(cadastroCaixa.getText().length()<1){
			Window.alert("Digite o nome da pessoa a ser cadastrada.");
		}else{
			list.addItem(cadastroCaixa.getText());
			cadastroCaixa.setText("");
		}
	}
	
}
