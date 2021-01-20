import java.awt.Container;
import java.awt.EventQueue;


import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class main extends JFrame{
	Game gm = new Game();
	public main()
	{
		add(gm);
		
		setResizable(false);
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	gm.Exit();
		    }
		});
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame m = new main();
				
				m.setVisible(true);
				
			}
		});
		

	}

}
