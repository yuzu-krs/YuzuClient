package me.gamrboy4life.paradox.gvent;


import java.util.ArrayList;

public class Gvent {
    public Gvent call() {
        final ArrayList<GventData> datalist = GventManager.get(this.getClass());
        if (datalist != null) {
            for (GventData data : datalist) {
                try {
                    data.target.invoke(data.source, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }
}