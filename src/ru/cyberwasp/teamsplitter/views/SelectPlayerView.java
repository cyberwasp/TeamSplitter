package ru.cyberwasp.teamsplitter.views;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import ru.cyberwasp.teamsplitter.SelectedPlayer;

public class SelectPlayerView extends CheckBox {

    @Override
    public void setChecked(boolean checked) {
        if (player != null)
            player.setSelected(checked);
        super.setChecked(checked);
    }

    private SelectedPlayer player = null;

    public SelectPlayerView(Context context) {
        super(context);
        setChecked(true);
        setLongClickable(true);
    }

    public void setPlayer(SelectedPlayer player) {
        this.player = player;
        setChecked(player.isSelected());
        setText(player.getInfo());
    }

    public SelectedPlayer getPlayer() {
        return player;
    }
}
