/**
 * @author Peter Xu peterxu422@gmail.com
 */
public class Tuple implements Comparable<Tuple> {
	private int terms[];
	private String binCode;
	private boolean checked;
	private int num1s;
	private int numInp;
	
	public Tuple(int[] terms, String binCode) {
		this.terms = terms;
		this.checked = false;
		if(terms.length == 1)
			this.binCode = String.format("%" + Generator.numInputs + "s", Integer.toBinaryString(terms[0])).replace(' ', '0');
		else
			this.binCode = binCode;
	}

	//Getter/Setter
	public int[] getTerms()				{return terms;}
	public String getBinCode()			{return binCode;}
	public boolean getChecked()			{return checked;}
	public int getNum1s() {
		int ctr = 0;
		for(int i=0; i < binCode.length(); i++)
			if(binCode.charAt(i) == '1')
				ctr++;
		return ctr;
	}
	
	public void setTerms(int terms[])			{this.terms = terms;}
	public void setBinCode(String binCode)		{this.binCode = binCode;}
	public void setChecked()					{this.checked = true;}
	public void setNum1s(int num1s)				{this.num1s = num1s;}
	
	public String toString() {
		String expr = "";
		for(int i=0; i < binCode.length(); i++) {
			String lit = "" + (char)('A' + i);
			
			if(binCode.charAt(i) == 'x')
				continue;
			else if(binCode.charAt(i) == '0')
				expr += lit + "\'";
			else
				expr += lit;
		}
		
		return expr;
	}

	//See reference: http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
	@Override
	public int compareTo(Tuple compTup) {
		// TODO Auto-generated method stub
		return this.getNum1s() - compTup.getNum1s();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.binCode.equals(((Tuple) o).getBinCode());
	}
}
