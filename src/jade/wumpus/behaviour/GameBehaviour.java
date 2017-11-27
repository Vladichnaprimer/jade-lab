package jade.wumpus.behaviour;

import jade.core.behaviours.FSMBehaviour;


public class GameBehaviour extends FSMBehaviour {
    private static final long serialVersionUID = 1L;

    private static String STATE_REGISTER 	= "Register";
    private static String STATE_PLAY 		= "Play";

    protected static int TRANS_GAME_REGISTERED 	= 0;
    protected static int TRANS_GAME_OVER 		= 1;
    protected static int TRANS_GAME_DEREGISTERED 	= 2;


    public void onStart() {

    }

    public int onEnd() {
        return 0;
    }

}