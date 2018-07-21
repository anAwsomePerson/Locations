package notDefault;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static int right = 0;
	public static int wrong = 0;
	public static final boolean compareDb = false;
	public static int[] gameErrors = new int[28];
	public static final int[] entryusum = {1, 0};
	public static final int[] entry7 = {3, 2};
	public static final int[] entryoras = {5, 4};
	public static final int[] entry6 = {7, 6};
	public static final int[] entry5h2 = {9, 8};
	public static final int[] entry5 = {11, 10};
	public static final int[] entryhs = {13, 12};
	public static final int[] entry4 = {16, 15, 14};
	public static final int[] entry3a = {19, 18, 17};
	public static final int[] entryfl = {19, 18};
	public static final int[] entry3 = {21, 20, 17};
	public static final int[] entry2 = {24, 23, 22};
	public static final int[] entry1 = {27, 26, 25};
	public static ArrayList<LocationSet> pokemonErrors = new ArrayList<LocationSet>();
	public static ArrayList<IntString> locationErrors = new ArrayList<IntString>();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner input = new Scanner(new File("pokemonList.txt"));
		int number = 0;
		pokemonErrors.add(new LocationSet("filler for index 0", 0));
		//while(input.hasNext() && !input.nextLine().equals("Golduck")) {}
		
		while(input.hasNext()) {
			String bulbaLocations = "";
            String line = input.nextLine();
            //line = "Suicune";
            number ++;
            URL bulbaUrl = new URL(toBulba(line));
            URLConnection bulbaConnection = bulbaUrl.openConnection();
            bulbaConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );

            while(true) {
            	try {
            		bulbaConnection.getContentLength();
                    bulbaConnection.getContent();
                    bulbaConnection.getContentType();
                    InputStream bulbaStream = bulbaConnection.getInputStream();
                    int bulbaInt;
                
                    while( (bulbaInt=bulbaStream.read()) != -1 ) {
                	    //System.out.print((char)c);
                        bulbaLocations = bulbaLocations + (char)bulbaInt;
                        
                        //if(bulbaLocations.length() > 63) {
                        if((bulbaLocations.indexOf("G") < bulbaLocations.length() - 14) && !bulbaLocations.contains("Game locations")){
                       	    bulbaLocations = "";
                        }
                        //}
                    		
                    	if(bulbaLocations.contains("In side games")) {
                    		//System.out.println(bulbaLocations);
                    		bulbaLocations = LocationSet.cutTo(bulbaLocations, "Game locations");
                    		//System.out.println("begin debug\n" + print);
                    		break;
                    	}
                    }
                    
            		break;
            	}catch(java.io.IOException exception) {
            		System.out.print(line);
            	}
            }
        
            String dbLocations = "";
            
            if(compareDb) {
                URL dbUrl = new URL(toDb(line));
                URLConnection dbConnection = dbUrl.openConnection();
                dbConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );

                while(true) {
                	try {
                		dbConnection.getContentLength();
                        dbConnection.getContent();
                        dbConnection.getContentType();
                        InputStream dbStream = dbConnection.getInputStream();
                        int dbInt;
                    //String dbPreLocations = "";
                    //seeingLocations = false;
                
                        while( (dbInt=dbStream.read()) != -1 ) {
                            dbLocations = dbLocations + (char)dbInt;
                    	
                    	//if(dbLocations.length() > 63) {
                            if((dbLocations.indexOf("W") < dbLocations.length() - 14) && !dbLocations.contains("Where to find ")){
                                dbLocations = "";
                            }
                    	//}
                		
                            if(dbLocations.contains("Answers to ")) {
                                dbLocations = LocationSet.cutTo(dbLocations, "Where to find ");
                    		//System.out.println("begin debug\n" + print);
                                break;
                            }
                        }
                        
                        break;
                    }catch(java.io.IOException exception) {
                    	System.out.print(line);
                    }
                }
            }
            
            if(number % 16 == 0) {
            	System.out.println("found " + number);
            }
        
            //System.out.println("reached0");
            LocationSet set = new LocationSet(line, number);
            pokemonErrors.add(set);
            
            if(compareDb) {
                set.fillLists(bulbaLocations, dbLocations);
            }else {
            	set.fillBulbaList(bulbaLocations);
            }
            
            if("Ninetales".equals(line)) {
            	break;
            }/*else if("Poliwag".equals(line)) {
            	//System.out.println(bulbaLocations);
            	break;
            }*/
	    }
		
		/*System.out.println(pokemonErrors.get(20) + " " + pokemonErrors.get(20).bulbaLocations);
		System.out.println(pokemonErrors.get(22) + " " + pokemonErrors.get(22).bulbaLocations);*/
		
		if(!compareDb){
			//System.out.println("reached1");
			Scanner locationIn = new Scanner(new File("locations.txt"));
			//ArrayList<IntString> pageSizes = new ArrayList<IntString>();
			int progress = 0;
			
			while(locationIn.hasNext()) {
				String line = locationIn.nextLine();
				//System.out.println(progress + " " + line);
	            URL bulbaUrl = new URL(toBulbaLocation(line));
	            URLConnection bulbaConnection = bulbaUrl.openConnection();
	            bulbaConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
	            String catches = "";
	            progress ++;
	            boolean willContinue = false;

	            while(true) {
		            try {
		                bulbaConnection.getContentLength();
		                bulbaConnection.getContent();
		                bulbaConnection.getContentType();
		                InputStream bulbaStream = bulbaConnection.getInputStream();
		                int bulbaInt;
		                //int pageSize = 0;
		        
		                while( (bulbaInt=bulbaStream.read()) != -1 ) {
		                	catches = catches + (char)bulbaInt;
	                        
	                        if((catches.indexOf("{") < catches.length() - 14) && !catches.toLowerCase().contains("{{catch/header")){
	                        	catches = "";
	                        }
		                }
		                
		                catches = catches.toLowerCase();
		            
		                if(catches.contains("{{catch/footer")) {
		                    catches = catches.substring(0, catches.lastIndexOf("{{catch/footer"));
		                }else {
		                	willContinue = true;
		                }
		                
		                break;
		            }catch(java.io.IOException exception) {
		            	System.out.print(line);
		            }
	            }
	            
	            if(willContinue) {
	            	System.out.println(line + " will continue");
	            	willContinue = false;
	            	continue;
	            }
	            
	            if(progress % 16 == 0) {
            	    System.out.println("found " + progress);
            	//System.out.println(toBulbaLocation(line));
                }
	            
	            catches = catches.replace(" ", "").replace("\n", "").replace("[[route]]s", "").replace("Ã©", "e").trim();
	            line = LocationSet.removeBraces(line, "(", ")");
        		
        		while(line.startsWith("Kanto ") || line.startsWith("Johto ") || line.startsWith("Hoenn ") || line.startsWith("Sinnoh") || line.startsWith("Unova ") 
        				|| line.startsWith("Kalos ") || line.startsWith("Alola ")) {
        			line = line.substring(6);
        		}
	            
	            while(catches.contains("{{catch/entry")) {
	            	catches = LocationSet.cutTo(catches, "{{catch/").substring(1);
	            	ArrayList<Integer> lookingAtGames = new ArrayList<Integer>();
	            	int[] entry = new int[255];
	            	catches = LocationSet.cutTo(catches, "entry");
	            	
	            	if(catches.startsWith("entryusum")) {
	            		entry = entryusum;
	            	}else if(catches.startsWith("entry7")) {
	            		entry = entry7;
	            	}else if(catches.startsWith("entryoras")) {
	            		entry = entryoras;
	            	}else if(catches.startsWith("entry6")) {
	            		entry = entry6;
	            	}else if(catches.startsWith("entry5-2")) {
	            		entry = entry5h2;
	            	}else if(catches.startsWith("entry5")) {
	            		entry = entry5;
	            	}else if(catches.startsWith("entryhs")) {
	            		entry = entryhs;
	            	}else if(catches.startsWith("entry4")) {
	            		entry = entry4;
	            	}else if(catches.startsWith("entry3a")) {
	            		entry = entry3a;
	            	}else if(catches.startsWith("entryfl")) {
	            		entry = entryfl;
	            	}else if(catches.startsWith("entry3")) {
	            		entry = entry3;
	            	}else if(catches.startsWith("entry2")) {
	            		entry = entry2;
	            	}else if(catches.startsWith("entry1")) {
	            		entry = entry1;
	            	}else {
	            		System.out.println(catches.substring(0, 18) + " didn't start with anything!");
	            	}
	            	
	            	for(int i = 0; i < entry.length; i ++) {
	            		if("yes".equals(LocationSet.betweenPipes(catches, 3 + i).trim())) {
	            			lookingAtGames.add(entry[i]);
	            		}
	            	}
	            	
	            	if(LocationSet.betweenPipes(catches, 1).contains("{{")) {
	            	    System.out.println(line + " " + LocationSet.betweenPipes(catches, 1));
	            	}else if((Integer.parseInt(LocationSet.betweenPipes(catches, 1).substring(0, 3)) > 0) 
	            			&& (Integer.parseInt(LocationSet.betweenPipes(catches, 1).substring(0, 3)) <= number)) {
	            	    pokemonErrors.get(Integer.parseInt(LocationSet.betweenPipes(catches, 1).substring(0, 3))).add(2, lookingAtGames, line.trim());
	            	}
	            }
	            
	            //break;
			}
	
			/*pokemonErrors.get(41).compareLists();
			pokemonErrors.get(42).compareLists();
			pokemonErrors.get(54).compareLists();
			pokemonErrors.get(55).compareLists();
			pokemonErrors.get(74).compareLists();
			pokemonErrors.get(129).compareLists();
			pokemonErrors.get(130).compareLists();
			pokemonErrors.get(339).compareLists();
			pokemonErrors.get(340).compareLists();*/
		}
		
		ArrayList<IntString> inheritanceSucks = new ArrayList<IntString>();
		
		for(int i = 1; i < pokemonErrors.size(); i ++) {
			pokemonErrors.get(i).compareLists();
			inheritanceSucks.add((IntString)pokemonErrors.get(i));
		}
		
	    sort(inheritanceSucks);
	    sort(locationErrors);
	    System.out.println("\nStats\nRight: " + right + "\nWrong: " + wrong + "\nAccuracy: " + ((double)right / (double)(right + wrong)) + "\nTop 28 most problematic Pokemon: ");
	    printList(inheritanceSucks, 28);
	    System.out.println("Top 28 most problematic games: ");
	
	    for(int x = 0; x < 28; x ++) {
    		int maxIndex = 0;
	    	String print = "";
		
		    for(int y = 0; y < 28; y ++) {
			    if(gameErrors[y] > gameErrors[maxIndex]) {
				    maxIndex = y;
			    }
		    }
		
		    while(LocationSet.games[maxIndex].length() + print.length() < 15) {
			    print = print + " ";
		    }
		
		    System.out.println(LocationSet.games[maxIndex] + print + gameErrors[maxIndex]);
		    gameErrors[maxIndex] = -1;
	    }
	
	    System.out.println("Top 28 most problematic locations: ");
	    printList(locationErrors, 28);
	}
	
	public static LocationSet getSet(String name) {
		for(int i = 1; i < pokemonErrors.size(); i ++) {
			if(name.equals(pokemonErrors.get(i).toString())) {
				return(pokemonErrors.get(i));
			}
		}
		
		System.out.println(name + " is making me return null!");
		return(null);
	}
	
	public static String toBulbaLocation(String string) {
		return("https://bulbapedia.bulbagarden.net/w/index.php?title=" + string.replace("'", "%27").replace(" ", "_").replace("~", "é") + "&action=edit");
	}
	
	public static String toBulba(String string) {
		return("https://bulbapedia.bulbagarden.net/w/index.php?title=" + 
	        string.replace("0", "♀").replace("1", "♂").replace("'", "%27").replace(" ", "_").replace("3", "é") + "_(Pok%C3%A9mon)&action=edit");
	}
	
	public static String toDb(String string) {
		return("https://pokemondb.net/pokedex/" + 
	        string.toLowerCase().replace("0", "-f").replace("1", "-m").replace("'", "").replace(".", "").replace(" ", "-").replace("3", "e").replace(":", ""));
	}
	
	public static void addString(ArrayList<IntString> list, String string) {
		for(int i = 0; i < list.size(); i ++){

    		if(string.equalsIgnoreCase(list.get(i).toString())){
    			list.get(i).plusPlus();
    			return;
    		}
    	}
    	
    	list.add(new IntString(1, string));
    	
    	/*if(list.size() % 32 == 0) {
    		System.out.println("Found " + list.size());
    	}*/
	}
	
	public static void printList(ArrayList<IntString> list, int number) {
		for(int i = 0; i < Math.min(list.size(), number); i ++) {
			System.out.println(list.get(i).toAlignedString());
		}
	}
	
	public static void sort(ArrayList<IntString> list){
    	for(int i = list.size() - 1; i >= 0; i --){
    		trickle(list, i, 0);
    	}
    	
    	/*for(int i = 0; i < list.size(); i ++) {
    		System.out.print(list.get(i).getInt() + " ");
    	}
    	
    	System.out.println();*/
    	
    	for(int i = 1; i < list.size(); i ++){
    		swap(list, 0, list.size() - i);
    		trickle(list, 0, i);
    	}
    	
    	/*for(int i = 0; i < list.size(); i ++) {
    		System.out.print(list.get(i).getInt() + " ");
    	}
    	
    	System.out.println();*/
    }
    
    public static void trickle(ArrayList<IntString> list, int target, int sorted){
    	while(2 * target + 1 < list.size() - sorted){
    		//System.out.println("reached01 " + target + " " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt());
    		
    		if((list.get(target).compareTo(list.get(2 * target + 1)) <= 0) && (list.size() - sorted <= 2 * target + 2)){
    			//System.out.println("reached11 " + target + " " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt());
    			return;
    		}
    		
    		if(2 * target + 2 >= list.size() - sorted){
    			//System.out.println("reached2 " + target + " " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt());
    			swap(list, target, 2 * target + 1);
    			target = 2 * target + 1;
    			continue;
    		}
    		
    		if((list.get(target).compareTo(list.get(2 * target + 1)) <= 0) 
    				&& (list.get(target).compareTo(list.get(2 * target + 2)) <= 0)){
    			//System.out.println("reached3 " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt() + " " + list.get(2 * target + 2).getInt());
    			return;
    		}
    		
    		if(list.get(2 * target + 1).compareTo(list.get(2 * target + 2)) <= 0){
    			//System.out.println("reached4 " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt() + " " + list.get(2 * target + 2).getInt());
    			swap(list, target, 2 * target + 1);
    			target = 2 * target + 1;
    		}else{
    			//System.out.println("reached5 " + list.get(target).getInt() + " " + list.get(2 * target + 1).getInt() + " " + list.get(2 * target + 2).getInt());
    			swap(list, target, 2 * target + 2);
    			target = 2 * target + 2;
    		}
    	}
    }
    
    public static void sortStrings(ArrayList<IntString> list){
    	for(int i = list.size() - 1; i >= 0; i --){
    		trickleStrings(list, i, 0);
    	}
    	
    	for(int i = 1; i < list.size(); i ++){
    		swap(list, 0, list.size() - i);
    		trickleStrings(list, 0, i);
    	}
    }
    
    public static void trickleStrings(ArrayList<IntString> list, int target, int sorted){
    	while(2 * target + 1 < list.size() - sorted){
    		if((list.get(target).toString().compareTo(list.get(2 * target + 1).toString()) <= 0) && (list.size() - sorted <= 2 * target + 2)){
    			return;
    		}
    		
    		if(2 * target + 2 >= list.size() - sorted){
    			swap(list, target, 2 * target + 1);
    			target = 2 * target + 1;
    			continue;
    		}
    		
    		if((list.get(target).toString().compareTo(list.get(2 * target + 1).toString()) <= 0) 
    				&& (list.get(target).toString().compareTo(list.get(2 * target + 2).toString()) <= 0)){
    			return;
    		}
    		
    		if(list.get(2 * target + 1).toString().compareTo(list.get(2 * target + 2).toString()) <= 0){
    			swap(list, target, 2 * target + 1);
    			target = 2 * target + 1;
    		}else{
    			swap(list, target, 2 * target + 2);
    			target = 2 * target + 2;
    		}
    	}
    }
    
    public static void swap(ArrayList<IntString> list, int i, int j) {
        IntString temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}