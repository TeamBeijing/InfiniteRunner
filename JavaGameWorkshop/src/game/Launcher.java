package game;

import Display.Display;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("JRunner", 1366, 768);
        game.start();
    }
}
