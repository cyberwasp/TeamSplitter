package ru.cyberwasp.teamsplitter.views;

import ru.cyberwasp.teamsplitter.R;
import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
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
        setBackgroundColor(getResources().getColor(R.color.background_color));
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        LinearLayout panel = new LinearLayout(context);
        panel.setOrientation(HORIZONTAL);
        panel.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setPadding(4, 4, 4, 4);
        addView(panel);

        
        nameCaption = new TextView(context);
        nameCaption.setText("Enter player name: ");
        addView(nameCaption, lp);
        nameEdit = new EditText(context);
        addView(nameEdit, lp);

        metricCaption = new TextView(context);
        metricCaption.setText("Enter player metric: ");
        addView(metricCaption, lp);
        metricEdit = new EditText(context);
        metricEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        addView(metricEdit, lp);

        okButton = new Button(context);
        okButton.setText("Ok");
        panel.addView(getOkButton(), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));

        cancelButton = new Button(context);
        cancelButton.setText("Cancel");
        panel.addView(cancelButton, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));

    }

    public TextView getMetricEdit() {
        return metricEdit;
    }

    public TextView getNameEdit() {
        return nameEdit;
    }

    public Button getOkButton() {
        return okButton;
    }

    public TextView getCancelButton() {
        return cancelButton;
    }

}
