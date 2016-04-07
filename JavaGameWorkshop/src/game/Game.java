package game;

import Display.Display;
import graphics.Assets;
import graphics.ImageLoader;
import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    //
    private int col = 0;
    private int y = 0;
    //
    private SpriteSheet player;
    private  boolean isRunning;
    private  String name;
    private int width;
    private int height;

    private  Thread thread;
    private  Display display;
    private BufferStrategy bs;
    private Graphics graphics;
    private SpriteSheet sh;
    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }
    public  void init() {
        this.display = new Display(this.name, this.width, this.height);
        Assets.init();
        this.player = Assets.player;
    }
    public void tick() {
        this.col++;
        this.col %= 4;
        this.y++;
    }
    public  void  render() {
        this.bs = this.display.getCanvas().getBufferStrategy();
        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }
        this.graphics = bs.getDrawGraphics();
        this.graphics.clearRect(0, 0, this.width, this.height);
        this.graphics.drawImage(ImageLoader.LoadImage("/background.jpg"), 0, 0, 1366, 768, null);
        int imgWidth = 64;
        int imgHeight = 64;
        this.graphics.drawImage(Assets.player.crop(this.col * imgWidth, 0, imgWidth, imgHeight), 0, 446, null);
        //start drawing
        //this.graphics.drawImage(Assets.background, 0, 0, null);
        //end drawing

        this.graphics.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();
        int fps = 60;
        long timePerTick = 1_000_000_000 / fps;
        int delta = 0;
        long now;
        long lastTimeTicked = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTimeTicked) / timePerTick;
            lastTimeTicked = now;
            System.out.println(delta);
            if (delta >= 1) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tick();
                render();
                delta--;
            }
        }
        this.stop();

    }
    public  synchronized  void  start() {
        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    public  synchronized  void stop() {
        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
