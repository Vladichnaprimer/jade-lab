package jade.wumpus.agents;

import jade.wumpus.enviroments.CaveNodes;

public interface CaveModelListener {
    void CaveModelChanged(CaveNodes[][] caveMap);
}