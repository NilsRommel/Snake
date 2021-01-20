import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		int key_id = e.getKeyCode();
		
		if(key_id == KeyEvent.VK_A && Game.Richtung != 2) 
		{
			Game.Richtung = 2;
		}
		else if(key_id == KeyEvent.VK_W && Game.Richtung != 0) 
		{
			Game.Richtung = 0;
		}
		else if(key_id == KeyEvent.VK_S && Game.Richtung != 1) 
		{
			Game.Richtung = 1;
		}
		else if(key_id == KeyEvent.VK_D && Game.Richtung != 3) 
		{
			Game.Richtung = 3;
		}
	}
}
