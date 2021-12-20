package me.osanci.com;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FreezeManager {
    ArrayList<Player> fagyasztva;

    public FreezeManager() {
        this.fagyasztva  = new ArrayList<>();
    }

    public boolean isFrozen(Player p) {
        return  this.fagyasztva.contains(p);
    }

    public void setFrozen(Player p, Boolean b) {
        if (b) {
            this.fagyasztva.add(p);
        } else {
            this.fagyasztva.remove(p);
        }
    }

}
