package jade.wumpus.agents;

import java.util.List;

public interface NavigatorListener {
    void navigatorListModelChanged(List<NavigatorAgent> hunters);
}