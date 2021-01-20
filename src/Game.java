import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
	public static int Richtung;
	private int wight = 500;
	private int high = 500;
	
	private File datei;
	
	private Image Tail;
	private Image Head;
	private Image Eat;
	
	private JButton Button;
	private Timer t;
	private HighScore2 HS;
	private HighScore2 HSgelesen;
	
	private int Eat_x;
	private int Eat_y;
	private int Eat_wight;
	private int Snake_wight = 10;
	private int Snake_x[] = new int[wight*high/Snake_wight*Snake_wight];
	private int Snake_y[] = new int[wight*high/Snake_wight*Snake_wight];
	private int Tail_lenght = 3;
	private boolean running;
	private int delay = 200;
	private int Counter = 0;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Game() {
		
		
		addKeyListener(new KeyListener());
		setPreferredSize(new Dimension(wight, high));
		setFocusable(true);
		setBackground(Color.DARK_GRAY);
		ImageIcon Tail_Icon = new ImageIcon("D:\\eclipse-workspace\\Snake\\Bilder\\Tail.png");
		ImageIcon Eat_Icon = new ImageIcon("D:\\eclipse-workspace\\Snake\\Bilder\\Eat.png");
		ImageIcon Head_Icon = new ImageIcon("D:\\eclipse-workspace\\Snake\\Bilder\\Head.png");
		
		Tail = Tail_Icon.getImage();
		Head = Head_Icon.getImage();
		Eat = Eat_Icon.getImage();
		
		for(int i=0;i<Tail_lenght;i++) {
			Snake_x[i] = wight/2 + 10*i;
			Snake_y[i] = high/2;
		}
		
	    running = true;
		
	    t = new Timer(delay, this);
	    t.start();
	    
	    
		Essenspawn();
		HS = new HighScore2();
		try {
			datei = new File("D:\\eclipse-workspace\\Snake\\HigheScore\\HigheScore.lol");
			
			ois = new ObjectInputStream(new FileInputStream(datei));
			/*oos.writeObject(HS);
			oos.flush();
			oos.close();*/
			HSgelesen = (HighScore2) ois.readObject();
			ois.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button)
		{
			running = true;
			t.restart();
			Tail_lenght = 3;
			for(int i=0;i<Tail_lenght;i++) {
				Snake_x[i] = wight/2 + 10*i;
				Snake_y[i] = high/2;
			}
			System.out.println(running);
			Counter = 0;
			Button.setVisible(false);
		}
		if(running == true) {
			Eat_Check();
			Kollision_Check();
			move();	
		}
		repaint();
	}
	private void move() {
		for(int i=Tail_lenght-1; i > 0; i--) 
		{
		    Snake_x[i] = Snake_x[i-1];
		    Snake_y[i] = Snake_y[i-1];
		}
		// 0=links   1=rechts   2=oben   3=unten
		switch(Richtung) 
		{
		case 0:
			Snake_y[0] -= 10;
			break;
		case 1:
			Snake_y[0] += 10;
			break;
		case 2:
			Snake_x[0] -= 10;
			break;
		case 3:
			Snake_x[0] += 10;
			break;
		}
		
	}
	private void Kollision_Check() {
		//Gegen die Wand
       if(Snake_x[0] > wight || Snake_x[0] < 0 || Snake_y[0] < 0 || Snake_y[0] > high) 
       {
    	 running = false;  
	   }
       for(int i=Tail_lenght-1; i > 3; i--) 
       {
    	   if(Snake_x[0] == Snake_x[i] && Snake_y[0] == Snake_y[i]) 
    	   {
    		   running = false; 
    	   }
       }
       if(running == false) 
       {
    	   t.stop();
       }

       }
	private void Eat_Check() {
		if(Eat_x == Snake_x[0] && Eat_y == Snake_y[0]) {
			t.setDelay(delay - 5*Counter);                                                  //Hier ist noch das Problem das der delay 0 werden kann
			Counter++;
			Tail_lenght ++;
			Essenspawn();
			for(int i=Tail_lenght; i > 0; i--)
				if(Eat_x == Snake_x[i] && Eat_y == Snake_y[i]) {
				Essenspawn();
				i=Tail_lenght + 1;
			}
		}
		
		
	}
	public void Essenspawn(){
			Eat_x = 10*(int)(Math.random()*(wight/10));
			Eat_y = 10*(int)(Math.random()*(high/10));
			
		
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if(running == true) 
		{
			g.drawImage(Eat,Eat_x,Eat_y,this);
			g.drawImage(Head, Snake_x[0], Snake_y[0], this);
			for(int i = 1; i< Tail_lenght; i++) 
			{
				g.drawImage(Tail, Snake_x[i], Snake_y[i], this);
			}
			Toolkit.getDefaultToolkit().sync();
			Font o = new Font("Calibri", Font.BOLD, 16);
			FontMetrics alpha = getFontMetrics(o);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(o);
			g.drawString("Länge ist: " + (Counter+3) , 10, 20);
			
		}
		else 
		{
			Font f = new Font("Calibri", Font.BOLD, 16);
			FontMetrics metrics = getFontMetrics(f);
			
	
			
		    Button = new JButton("Try Again");
			Button.addActionListener(this);  
			Button.setBackground(Color.LIGHT_GRAY);
			Button.setLocation(wight/2 - 50, high-100);
			Button.setSize(100, 30);
			this.add(Button);
			
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(f);
			//Score.neuerHighscore(Tail_lenght);
			
			HSgelesen.setHighScore(Counter+3);
			for(int i=0;i<10;i++) {
				System.out.println(HSgelesen.getHighScore()[i]);
			}
			HSgelesen.setHigheScore(HSgelesen.getHighScore());
			
			
			g.drawString("Game Over", (wight/2 - metrics.stringWidth("Game Over")/2),100);
			g.drawString("Highscore List", (wight/2 - metrics.stringWidth("Highscore List")/2), 120);
			int i = 0;
			System.out.println(HSgelesen.getHigheScore());
			for(String Wort : HSgelesen.getHigheScore()) {
				g.drawString(Wort, (wight/2 - metrics.stringWidth(Wort)/2), 140+i*20);
				i++;
			}
			
			
		}
	}

	public void Exit() {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(datei));
			oos.writeObject(HSgelesen);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
