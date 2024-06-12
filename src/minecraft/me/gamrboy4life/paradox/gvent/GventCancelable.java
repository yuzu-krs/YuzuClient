package me.gamrboy4life.paradox.gvent;

public class GventCancelable extends Gvent {
    private boolean cancelled = false;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}