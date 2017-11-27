package jade.wumpus.enviroments;

import java.util.ArrayList;
import jade.util.Logger;
import jade.wumpus.agents.CaveModelListener;
import jade.wumpus.agents.NavigatorAgent;
import jade.wumpus.agents.NavigatorListener;

import java.util.List;
import java.util.Random;


public class Cave {
    private Logger log = Logger.getMyLogger(this.getClass().getName());

    private CaveNodes[][] CaveMap = null;
    private List<CaveModelListener> listenersCave;

    private List<NavigatorAgent> hunters;
    private List<NavigatorListener> listenersHunter;

    public Cave() {

        CaveMap = new CaveNodes[4][4];
        listenersCave = new ArrayList<CaveModelListener>();

        hunters = new ArrayList<>();
        listenersHunter = new ArrayList<>();

        initCave();
    }

    private void initCave() {

        for (int i = 0; i < CaveMap.length; i++ ) {
            for (int j = 0; j < CaveMap[i].length; j++) {
                CaveMap[i][j] = new CaveNodes(i, j);
            }
        }

        Random r = new Random();
        int x, y;
        boolean placed = false;
        while(!placed){
            x = r.nextInt(CaveMap.length);
            y = r.nextInt(CaveMap[1].length);
            if(x != 0 || y != 0){
                CaveMap[x][y].setVampus(true);
                placed = true;
            }
        }

        placed = false;
        while (!placed) {
            x = r.nextInt(CaveMap.length);
            y = r.nextInt(CaveMap[1].length);
            if ((x != 0 || y != 0) && CaveMap[x][y].hasNoVampus()) {//isEmpty()) {
                CaveMap[x][y].setGold(true);
                placed = true;
            }
        }

        for (x = 0; x < CaveMap.length; x++ ) {
            for (y = 0; y < CaveMap[x].length; y++) {
                if ((x != 0 || y != 0) && CaveMap[x][y].isEmpty()){
                    if (Math.random() <= 0.2) {
                        CaveMap[x][y].setPit(true);
                    }
                }
            }
        }

        notifyCaveModelListeners();
    }

    public CaveNodes[][] getCaveMap() {
        return this.CaveMap;
    }


    public void addCaveModelListener(final CaveModelListener listener) {
        if (listener != null && !listenersCave.contains(listener)) {
            this.listenersCave.add(listener);
        }
    }

    public void removeCaveModelListener(final CaveModelListener listener) {
        this.listenersCave.remove(listener);
    }

    public void notifyCaveModelListeners() {
        for (final CaveModelListener listener : this.listenersCave) {
            notifyCaveModelListener(listener);
        }
    }

    public void notifyCaveModelListener(final CaveModelListener listener) {
        listener.CaveModelChanged(this.getCaveMap());
    }

    public void addHunterListListener(final NavigatorListener listener) {
        if (listener != null && !listenersHunter.contains(listener)) {
            this.listenersHunter.add(listener);
        }
    }

    public void removeHunterListListener(final NavigatorListener listener) {
        this.listenersHunter.remove(listener);
    }

    public void notifyHunterListListeners() {
        for (final NavigatorListener listener : this.listenersHunter) {
            listener.navigatorListModelChanged(this.hunters);
        }
    }


}