import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.management.StringValueExp;
import javax.swing.text.Highlighter;




public class HighScore { 
	
	
	private FileReader fr;
	private FileWriter fw;
	private BufferedWriter bw;
	private BufferedReader br;
	
	private String line;
	
	private String[] List = new String[10];
	
	private Scanner sc;
	private File datei;
	
	public HighScore () 
	{
		datei = new File("test.txt");
		try {
			sc = new Scanner(datei);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fr = new FileReader("test.txt");
			fw = new FileWriter("test.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	br = new BufferedReader(fr);
	bw = new BufferedWriter(fw);
	
	
		
	}
	
	public String[] neuerHighscore(){
		/*try {
			for(int i=0;i<9;i++) {
				//bw.write(String.valueOf(3));
				//bw.newLine();
			}
			//bw.write(String.valueOf(3));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		int anzahl = 0;
			for(int i=0;i<10;i++) {
				try {
					List[i] = String.valueOf(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
        
        try {
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
          return List;                                                    
	}
	
	
	public int[] Bubblesort(int[] List) {
		int temp;
		for(int i=List.length -1; i>0; i--) {
			for(int a=0; a<List.length-1; a++) {
				if(List[a] < List[a+1]) {
					temp = List[a];
					List[a] = List[a+1];
					List[a+1] = temp;
				}
			}
		}
		return List;
	}
	
}
