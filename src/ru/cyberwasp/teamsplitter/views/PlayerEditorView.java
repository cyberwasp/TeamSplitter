package ru.cyberwasp.teamsplitter.views;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerEditorView extends LinearLayout {

	private TextView nameCaption;
	private TextView nameEdit;
	private TextView metricEdit;
	private TextView metricCaption;
	private Button okButton;
	private TextView cancelButton;
	
	public PlayerEditorView(Context context) {
		super(context);
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); 

		nameCaption = new TextView(context);
		nameCaption.setText("Enter player name: ");
		addView(nameCaption, lp);
		nameEdit = new EditText(context);
		addView(nameEdit, lp);

	    metricCaption = new TextView(context);
		metricCaption.setText("Enter player metric: ");
		addView(metricCaption, lp);
		metricEdit = new EditText(context);
		addView(metricEdit, lp);
		
		okButton = new Button(context);		
		okButton.setText("Ok");
		addView(getOkButton());

		cancelButton = new Button(context);
		cancelButton.setText("Cancel");
		addView(cancelButton);

	}

	public TextView getMetricEdit() {
		return metricEdit;
	}
	
	public TextView getNameEdit(){
		return nameEdit;
	}

	public Button getOkButton() {
		return okButton;
	}

	public TextView getCancelButton() {
		return cancelButton;
	}

}
