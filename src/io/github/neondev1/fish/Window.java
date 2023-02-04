package io.github.neondev1.fish;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	public Window(Renderer r) {
		r.setPreferredSize(new Dimension(960, 540));
		r.setMaximumSize(getPreferredSize());
		r.setMinimumSize(getPreferredSize());
		JFrame frame = new JFrame("大鱼吃小鱼");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(r);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		r.start();
	}
	
}
