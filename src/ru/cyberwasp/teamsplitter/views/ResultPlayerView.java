package ru.cyberwasp.teamsplitter.views;

import android.content.Context;
import android.widget.TextView;
import ru.cyberwasp.teamsplitter.Player;

public class ResultPlayerView extends TextView {

    private Player player;

    public ResultPlayerView(Context context) {
        super(context);
    }

    public void setPlayer(Player player) {
        this.player = player;
        setText(player.getInfo());
    }

    public Player getPlayer() {
        return player;
    }

}
