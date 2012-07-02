package ru.cyberwasp.teamsplitter;

public class SelectedPlayer extends Player {

    private boolean selected;

    public SelectedPlayer(Player player, boolean selected) {
        super(player);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static SelectedPlayer[] makeSelected(Player[] players, boolean selected) {
        SelectedPlayer res[] = new SelectedPlayer[players.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = new SelectedPlayer(players[i], selected);
        }
        return res;
    }
}