package game;
public class GameLoop implements Runnable {

    private Game game; //game object which game loop applies to.

    private boolean running; //initializes to false
    public final double updateRate = 1.0d / 60.0d; //d suffix is unnecessary, just clarifies double status. Seconds per frame

    //temp instance vars for measurement purposes
    private long nextStatTime;
    private int fps, ups;


    public GameLoop (Game game) {
        this.game = game;
    }

    /*
     * This method runs continuously since the beginning of the game. The game effectively is piloted from here.
     * The two operations by which the game is operated are update and render, both of which are passed to game.
     */

    @Override
    public void run() {
        running = true;
        double accumulator = 0; //tracks time since last update
        long currentTime, lastUpdate = System.currentTimeMillis(); //difference in milisec between now and midnight
        nextStatTime = System.currentTimeMillis() + 1000;

        while(running) {
            currentTime = System.currentTimeMillis(); //recalculates time each loop iteration
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d; //time since last loop in seconds
            accumulator += lastRenderTimeInSeconds; //adds time elapsed since last loop to the accumulator.
            lastUpdate = currentTime;
            /* if time elapsed (accumulator) is greater than time per frame, 
             * reduce time elapsed by time per frame, and update.
             */
            if (accumulator >= updateRate) { //innards run 60 times a second
                while(accumulator > updateRate) { 
                    update(); //inside the while to ensure updates per second is ALWAYS consistent, if there is lag, a block of updates will be run to catch up if the number of updates falls behind 60/second
                    accumulator -= updateRate; //1/60th
                }
                render(); //outside the loop so we aim for 60 times a second, but don't run more than 60 a second to compensate for previous lag.
            }
            printStats(); //can be obliterated at your leisure,.
        }
    }

    public void printStats() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    public void update() { game.update(); ups++; }
    public void render() { game.render(); fps++; }
}
