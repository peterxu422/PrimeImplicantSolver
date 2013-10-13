/**
 * @author Peter Xu peterxu422@gmail.com
 */
import java.util.ArrayList;
import java.util.Vector;
import java.util.Collections;
import java.util.List;

public class Column {
	private Vector<Tuple> tups;
	private boolean noneChecked;
	
	public Column() {
		tups = new Vector<Tuple>();
		noneChecked = false;
	}
	
	public Vector<Tuple> getTuples()			{return tups;}
	
	public boolean getNoneChecked()	{
		for(Tuple t : tups)
			if(t.getChecked())
				return false;
		
		return true;
	}
	
	public void addTuple(Tuple t) {
		//Tuple t = new Tuple(terms, binCode); 
		if(!tups.contains(t))
			tups.add(t);
	}
	
	public void sortTuples() {
		Collections.sort(tups);
	}
	
	//Assumes tups is sorted by num1s
	public List<Integer> getNum1Indices() {
		List<Integer> l = new ArrayList<Integer>();
		int prevNum1s = -1;
		int c;
		
		for(int i=0; i < tups.size(); i++) {
			if((c=tups.get(i).getNum1s()) != prevNum1s) {
				l.add(i);
				prevNum1s = c;
			}
		}
		
		return l;
	}

	public void removeDuplicates()	{
		
	}
	
	public String toString() {
		String col = "";
		int maxlen = 0;
		for(Tuple t : tups) {
			int c;
			if((c=t.getTerms().length) > maxlen)
				maxlen = c;
		}
	
		String tmp = "";
		maxlen = maxlen*3 + 2;
		for(Tuple t : tups) {
			int terms[] = t.getTerms();
			String mTerms = "(";
			for(int i=0; i < terms.length; i++)
				mTerms += terms[i] + (tmp = (i == terms.length-1) ? ")" : ",");
			
			String format = "%" + maxlen + "s %s %s";
			col += String.format(format, mTerms, t.getBinCode(), (tmp = (t.getChecked()) ? "x" : "_")) + "\n";
		
		}
		return col;
	}



}
