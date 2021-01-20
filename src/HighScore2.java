import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HighScore2 implements Serializable{
	
	private int[] HighScoreInt = new int[] {0,0,0,0,0,0,0,0,0,0};
	private List<String> HigheScore;
	
	
	public HighScore2() {
		HigheScore = new ArrayList<String>();
		setHigheScore(HighScoreInt);
		
		
	}
	
	public int[] getHighScore() {
		return HighScoreInt;
	}
	public void setHighScore(int highScore) {
		if(HighScoreInt[9] < highScore) {
			HighScoreInt[9] = highScore;
		}
		HighScoreInt = Bubblesort(HighScoreInt);
	}
	private int[] Bubblesort(int[] List) {
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

	public List<String> getHigheScore() {
		return HigheScore;
	}
	public void setHigheScore(int[] HighScoreliste) {
		HigheScore.clear();
		for(int Stelle : HighScoreliste) {
			HigheScore.add(String.valueOf(Stelle));
		}
	}
	
	
	
}
