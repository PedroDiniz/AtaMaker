package ata.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.LayoutPanel;

public class PanelTopico extends Composite {

	private static TelaAtaUiBinder uiBinder = GWT.create(TelaAtaUiBinder.class);
	@UiField LayoutPanel layoutAta;
	@UiField Button addTopico;@UiField TextArea caixaTopico;
	static String[] topicos = new String[10];
	int contTopicos = 0;
	static String subTopicos = "";

	@UiField Button gerarAta;
	@UiField TextArea caixaSubtopico;
	public PanelTopico filho;



	interface TelaAtaUiBinder extends UiBinder<Widget, PanelTopico> {
	}

	public PanelTopico() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public String getTextoTopico(){
		return caixaTopico.getText();
	}
	
	public String getTextoSubtopico(){
		return caixaSubtopico.getText();
	}

	@UiHandler("addTopico")
	void onAddTopicoClick(ClickEvent event) {		
		//carregarTopico();
		if(caixaTopico.getText().length()<1){
			Window.alert("Texto do Topico vazio.");
		}else if(caixaSubtopico.getText().length()<1){
			Window.alert("Texto do Subtopico vazio.");
		}else{
		filho = new PanelTopico();
		TelaAta.panel.add(filho, "Topico " + (TelaAta.panel.getSelectedIndex()+2));
		TelaAta.panel.showStack(TelaAta.panel.getSelectedIndex()+1);
		addTopico.setVisible(false);
		addTopico.setEnabled(false);
		gerarAta.setEnabled(false);
		gerarAta.setVisible(false);
		}
	}
	@UiHandler("gerarAta")
	void onGerarAtaClick(ClickEvent event) {
		if(caixaTopico.getText().length()<1){
			Window.alert("Texto do Topico vazio.");
		}else if(caixaSubtopico.getText().length()<1){
			Window.alert("Texto do Subtopico vazio.");
		}else{
		ScrollPanel p = new ScrollPanel();
		TelaAta ta = (TelaAta) Ata.stack.getWidget(2);
		test informacoes = (test) Ata.stack.getWidget(1);
		String codigoHtml = criarAta(TelaCadastro.dateString,informacoes.getPresentes(), informacoes.getAusentes(), informacoes.getAtrasos(),
				informacoes.getPresidente(), informacoes.getAteiro(), ta.panel);

		HTML html = new HTML(codigoHtml);
		p.add(html);
		if(Ata.stack.getWidgetCount()==3){
			Ata.stack.add(p, "Ata Criada", true);
		}else{
			Ata.stack.remove(3);
			Ata.stack.add(p, "Ata Criada", true);
			System.out.println("tamanho nova stack " + Ata.stack.getWidgetCount());
		}
		Ata.stack.showStack(3);
		}
//		addTopico.setVisible(false);
//		addTopico.setEnabled(false);
//		gerarAta.setEnabled(false);
//		gerarAta.setVisible(false);
	}

	public String criarAta(String data, String presentes, String ausentes, String atrasos,
			String presidente, String ateiro, StackPanel topicos){
		String retorno = "";
		String topic = "";
		
		for(int i = 0; i < topicos.getWidgetCount(); i++){
			PanelTopico p = (PanelTopico) topicos.getWidget(i);
			topic += "<h2 style=\"line-height:0.5em\" style=\"margin-top: 25\">" + p.getTextoTopico() + "</h2>";
			String quebrar = p.getTextoSubtopico();
			System.out.println("quebrar");
			String[] broken = quebrar.split("--");
			for (int j = 1; j < broken.length; j++) {
				System.out.println(broken[j]);
				topic += "<li style=\"margin-left: 22\">" + broken[j] + "</li><br><br><br><br>";
			}
			topic+= "<br><br>";
		}
		System.out.println(topic);
		retorno = "<html>" +
				"<head>" +
				"<meta charset=\"utf-8\"></meta>" +
				"<title>AtaMaker</title>" +
				"</head>" +
				"<body style=\"font-family:tahoma\">" +
				"<h1>Dia " + data + "</h1>" +
				"<div style=\"line-height:0.3em; min-height:200px; min-width:1240px;\">" +
				"<p style=\"font-size:15px\"><b>Presentes: </b>" + presentes + "</p>" +
				"<p style=\"font-size:15px\"><b>Ausentes: </b>" + ausentes + "</p>" +
				"<p style=\"font-size:15px\"><b>Atrasos: </b>" + atrasos + "</p>" +
				"<p style=\"font-size:15px\"><b>Presidente: </b>" + presidente + "</p>" +
				"<p style=\"font-size:15px\"><b>Ateiro: </b>" + ateiro + "</p>" +
				"<br><br>"+
				topic+
				"</div>";
		return retorno;
	}

}
