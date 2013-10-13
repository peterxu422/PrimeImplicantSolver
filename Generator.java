/**
 * Prime Implicant Solver
 * @author Peter Xu peterxu422@gmail.com
 */
import java.util.Vector;
import java.util.List;

public class Generator {

	public static int numInputs;
	public static Vector<Column> cols;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Check if input argmuents are correct
		String str_minTerms[] = null;
		int[] minTerms = null;
		String errormsg = "Input error. Program args: <num input vars> <minterm list>\n"
				+ "<num input vars>: integer\n" + "<minterm list>: List of minterms enclosed in \"\" separated by space. E.g. \"2 3 5 7 ...\"";
		try {
			numInputs = Integer.parseInt(args[0]);
			
			//System.out.println(args[1].matches("[\\d+][,\\s\\d+]*"));
			str_minTerms = args[1].split("[\\s]");
			
			minTerms = new int[str_minTerms.length];
			for(int i=0; i < minTerms.length; i++)
				minTerms[i] = Integer.parseInt(str_minTerms[i]);
			
		} catch(NumberFormatException nfe) {
			System.out.println(errormsg);
			System.exit(1);
		}
		
		Column c1 = new Column();
		
		for(int i=0; i < minTerms.length; i++) {
			Tuple t = new Tuple(new int[]{minTerms[i]}, "");
			c1.addTuple(t);	
		}
		
		cols = new Vector<Column>();
		c1.sortTuples();
		
		Column c2 = makeNextCol(c1);
		while(!c1.getNoneChecked()) {
			cols.add(c1);
			c1 = c2;
			c2 = makeNextCol(c1);
		}
		cols.add(c1);
		Vector<Tuple> pis = new Vector<Tuple>();
		
		int i = 1;
		for(Column c : cols) {
			for(Tuple t : c.getTuples())
				if(!t.getChecked())
					pis.add(t);
			
			System.out.println("Column " + i++ + " \n");
			System.out.println(c);
			System.out.println();
		}
		
		System.out.println("Prime Implicants");
		String str_pis = "";
		for(Tuple t : pis) {
			str_pis += t.toString() + "  ";
		}
		
		System.out.println(str_pis);
	}
	
	//Assumes s1 and s2 are equal lengths
	public static boolean diffByOne(String s1, String s2) {
		boolean dbo = true;
		boolean firstDiff = true;
		
		for(int i=0; i < s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				if(!firstDiff) {
					dbo = false;
					break;
				}
				firstDiff = false;
			}
		}
		
		return dbo;
	}
	
	//Assumes s1 and s2 differ only by 1 char
	public static Tuple mergePair(Tuple t1, Tuple t2) {
		int[] terms = new int[t1.getTerms().length + t2.getTerms().length];
		int j = 0;
		for(int i=0; i < t1.getTerms().length; i++) {
			terms[i] = t1.getTerms()[i];
			j++;
		}
		
		
		for(int i=0; i < t2.getTerms().length; i++) {
			terms[j] = t2.getTerms()[i];
			j++;
		}
		
		String s = "";
		String s1 = t1.getBinCode();
		String s2 = t2.getBinCode();
		for(int i=0; i < s1.length(); i++) {
			if(s1.charAt(i) == s2.charAt(i))
				s += s1.charAt(i);
			else
				s += "x";
		}
		
		return new Tuple(terms, s);
	}
	
	public static Column makeNextCol(Column prev) {
		Column nextCol = new Column();
		prev.sortTuples();
		List<Integer> n1Idxs =  prev.getNum1Indices();
		Vector<Tuple> t = prev.getTuples();
		
		int j = (n1Idxs.get(0) == 0 && n1Idxs.size() > 1) ? n1Idxs.get(1) : 0;
		int k;

		for(int i=0; i < n1Idxs.get(n1Idxs.size()-1); i++) {
			//Take tups from cur1SecIdx
			Tuple cur = t.get(i);
			try 	{k = n1Idxs.get(j+1);}
			catch (IndexOutOfBoundsException iob) {
				k = t.size();
			}
			
			for(int m=j; m < k; m++) {
				Tuple next = t.get(m);				
				//Check if they diffbyOne with any tups from nex1SecIdx
				if(diffByOne(cur.getBinCode(), next.getBinCode())) {
					//If yes, Create a new tup (merge the Terms for the pair, new binCode including the dc's)
					//Add the new tup to newCol
					nextCol.addTuple(mergePair(cur, next));
					//Mark the two original tuples as checked
					cur.setChecked();
					next.setChecked();
				}
				
			}
			j++;

		}
		
		return nextCol;
	}

}
