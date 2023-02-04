package io.github.neondev1.fish;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas implements Runnable {

	private Boolean run = false;
	private Thread thread;
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1"); 
		new Renderer();
	}
	
	public Renderer() {
		 new Window(this);
	}
	
	@Override
	public void run() {
		long ltime = System.nanoTime();
		double target = 60.0;
		double ns = 1_000_000_000 / target;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (run) {
			long ctime = System.nanoTime();
			delta += (ctime - ltime) / ns;
			ltime = ctime;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (run)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1_000) {
				timer += 1_000;
				System.out.println("FPS=" + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		run = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			run = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		
	}

}
