package ata.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.StackPanel;

public class TelaAta extends Composite {

	private static TelaAtaUiBinder uiBinder = GWT.create(TelaAtaUiBinder.class);
	public static StackPanel panel;
	@UiField 
	ScrollPanel scrollAta;
	@UiField 
	HTMLPanel htmlPanel;
	
	//LayoutPanel layout = new LayoutPanel();

	interface TelaAtaUiBinder extends UiBinder<Widget, TelaAta> {
	}

	public TelaAta() {
	//	panels.add(inicial);
		//layoutAta.add(inicial);
		initWidget(uiBinder.createAndBindUi(this));
		panel = new StackPanel();
		PanelTopico p = new PanelTopico();
		panel.add(p, "Topico 1", true);
		panel.showStack(0);
		scrollAta.add(panel);
	}

}
