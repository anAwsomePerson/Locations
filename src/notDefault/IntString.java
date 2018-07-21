package notDefault;

public class IntString implements Comparable<IntString>{
    int integer;
    String string;
    
    public IntString(int inInt, String inString) {
    	integer = inInt;
    	string = inString;
    }
    
    public String toAlignedString() {
    	String out = "";
    	
    	while(string.length() + out.length() + ((Integer)integer).toString().length() < 32) {
    		out = out + " ";
    	}
    	
    	return(string + out + integer);
    }
    
    @Override
    public String toString() {
    	return(string);
    }
    
    public int getInt() {
    	return(integer);
    }
    
    public void plusPlus() {
    	integer ++;
    }
    
    public int compareTo(IntString other) {
    	return(integer - other.integer);
    }
    
    public boolean equals(IntString other) {
    	return(integer == other.integer);
    }
}
