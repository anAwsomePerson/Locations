package notDefault;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static int right = 0;
	public static int wrong = 0;
	public static final int mode = 5;
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
	public static ArrayList<String> list504 = new ArrayList<String>();
	public static ArrayList<LocationSet> pokemonErrors = new ArrayList<LocationSet>();
	public static ArrayList<IntString> locationErrors = new ArrayList<IntString>();
	
	public static void main(String[] args) throws Exception{
		switch (mode){
		case 0:
			downloadBulba();
			downloadDb();
			downloadBulbaLocations();
			break;
		case 1:
			downloadBulba();
			break;
		case 2:
			downloadDb();
			break;
		case 3:
			downloadBulbaLocations();
			break;
		case 4:
			comparePokemon();
			break;
		case 5:
			compareBulba();
			break;
		default:
			System.out.println("mode's value is out of bounds!");
		}
		
		for(int i = 0; i < list504.size(); i ++) {
			System.out.println("504 from " + list504.get(i));
		}
	}
	
	public static void downloadBulba() throws Exception{
		// TODO Auto-generated method stub
		Scanner input = new Scanner(new File("pokemonList.txt"));
		int number = 0;
		PrintWriter output = new PrintWriter(new File("bulbaDownload0.txt"));
		
		while(input.hasNext()) {
			String bulbaLocations = "";
            String line = input.nextLine();
            //line = "Suicune";
            number ++;
            URL bulbaUrl = new URL(toBulba(line));
            URLConnection bulbaConnection = bulbaUrl.openConnection();
            bulbaConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );

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
            }catch(java.io.IOException exception) {
            	System.out.println("504 from " + toBulba(line));
            	list504.add(line);
            }
            
            output.println('~' + line + '!' + bulbaLocations);
            System.out.println('~' + line + '!' + bulbaLocations);
		}
	}
	
	public static void downloadDb() throws Exception{
		Scanner input = new Scanner(new File("pokemonList.txt"));
		int number = 0;
		PrintWriter output = new PrintWriter(new File("dbDownload0.txt"));
		
		while(input.hasNext()) {
            String dbLocations = "";
            String line = input.nextLine();
            
            URL dbUrl = new URL(toDb(line));
            URLConnection dbConnection = dbUrl.openConnection();
            dbConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );

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
            }catch(java.io.IOException exception) {
            	System.out.print("504 from " + toDb(line));
            	list504.add(line);
            }
            
            output.println('~' + line + '!' + dbLocations);
            System.out.println('~' + line + '!' + dbLocations);
        
            /*System.out.println("reached0");
            LocationSet set = new LocationSet(line, number);
            pokemonErrors.add(set);
            
            if(mode == 3) {
                set.fillLists(bulbaLocations, dbLocations);
            }else {
            	set.fillBulbaList(bulbaLocations);
            }
            
            if("Ninetales".equals(line)) {
            	break;
            }else if("Poliwag".equals(line)) {
            	//System.out.println(bulbaLocations);
            	break;
            }*/
	    }
		
		/*System.out.println(pokemonErrors.get(20) + " " + pokemonErrors.get(20).bulbaLocations);
		System.out.println(pokemonErrors.get(22) + " " + pokemonErrors.get(22).bulbaLocations);*/
	}
	
	public static void downloadBulbaLocations() throws Exception{
		//int number = 0;
		PrintWriter output = new PrintWriter(new File("locationDownload0.txt"));
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
            
            /*if("Ruins of Conflict".equals(line)) {
            	break;
            }*/

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
            }catch(java.io.IOException exception) {
            	System.out.print("504 from " + toBulbaLocation(line));
            	list504.add(line);
            }
            
            if(willContinue) {
            	System.out.println(line + " will continue");
            	willContinue = false;
            	continue;
            }
            
            output.println('~' + line + '!' + catches);
            System.out.println('~' + line + '!' + catches);
	            
	            /*//break;
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
	}
	
	public static void comparePokemon() throws Exception{
		int number = 0;
		pokemonErrors.add(new LocationSet("filler for index 0", number));
		Scanner pokemonIn = new Scanner(new File("pokemonList.txt"));
		Scanner bulbaIn = new Scanner(new File("bulbaDownload0.txt"));
		Scanner dbIn = new Scanner(new File("dbDownload0.txt"));
		String pokemonLine;
		String bulbaLine = bulbaIn.nextLine();
		String dbLine = dbIn.nextLine();
		String bulbaLocations = "";
		String dbLocations = "";
		
		while(pokemonIn.hasNext()) {
			pokemonLine = pokemonIn.nextLine();
			number ++;
			LocationSet set = new LocationSet(pokemonLine, number);
            pokemonErrors.add(set);
            
            while(bulbaIn.hasNext()) {
            	if(bulbaLine.contains("~") && bulbaLine.contains("!")){
            		if(!bulbaLine.substring(bulbaLine.indexOf('~') + 1, bulbaLine.indexOf('!')).equals(pokemonLine)) {
            			break;
            		}
            	}
            	
            	bulbaLocations = bulbaLocations + bulbaLine;
            	bulbaLine = bulbaIn.nextLine();
            }
            
            while(dbIn.hasNext()) {
            	if(dbLine.contains("~") && dbLine.contains("!")){
            		if(!dbLine.substring(dbLine.indexOf('~') + 1, dbLine.indexOf('!')).equals(pokemonLine)) {
            			break;
            		}
            	}
            	
            	dbLocations = dbLocations + dbLine;
            	dbLine = dbIn.nextLine();
            }
            
            if(bulbaLocations.contains('~' + pokemonLine + '!') && dbLocations.contains('~' + pokemonLine + '!')) {
                set.fillLists(bulbaLocations, dbLocations);
                set.compareLists();
                bulbaLocations = "";
                dbLocations = "";
            }
		}
		
		ArrayList<IntString> inheritanceSucks = new ArrayList<IntString>();
		
		for(int i = 1; i < pokemonErrors.size(); i ++) {
			//pokemonErrors.get(i).compareLists();
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
	
	public static void compareBulba() throws Exception{
		int number = 0;
		pokemonErrors.add(new LocationSet("filler for index 0", number));
		Scanner pokemonListIn = new Scanner(new File("pokemonList.txt"));
		Scanner locationListIn = new Scanner(new File("locations.txt"));
		Scanner bulbaPokemonIn = new Scanner(new File("bulbaDownload0.txt"));
		Scanner bulbaLocationIn = new Scanner(new File("locationDownload0.txt"));
		String listLine;
		String pokemonLine = bulbaPokemonIn.nextLine();
		String locationLine = bulbaLocationIn.nextLine();
		String bulbaPokemon = "";
		String bulbaLocation = "";
		
		while(pokemonListIn.hasNext()) {
			listLine = pokemonListIn.nextLine();
			number ++;
			LocationSet set = new LocationSet(listLine, number);
            pokemonErrors.add(set);
            
            while(bulbaPokemonIn.hasNext()) {
            	if(pokemonLine.contains("~") && pokemonLine.contains("!")){
            		if(!pokemonLine.substring(pokemonLine.indexOf('~') + 1, pokemonLine.indexOf('!')).equals(listLine)) {
            			break;
            		}
            	}
            	
            	bulbaPokemon = bulbaPokemon + pokemonLine;
            	pokemonLine = bulbaPokemonIn.nextLine();
            }
            
            if(bulbaPokemon.contains('~' + listLine + '!')) {
                set.fillBulbaList(bulbaPokemon);
                bulbaPokemon = "";
            }
		}
		
		number = 0;
		
		while(locationListIn.hasNext()) {
			listLine = locationListIn.nextLine();
			number ++;
			
			/*if("Kalos Route 16".equals(listLine)) {
				return;
			}*/
			
			while(bulbaLocationIn.hasNext()) {
				if(locationLine.contains("~") && locationLine.contains("!")){
            		//System.out.println(locationLine + " " + listLine);
            		if(!locationLine.substring(locationLine.indexOf('~') + 1, locationLine.indexOf('!')).equals(listLine)) {
            			//System.out.println("reached0");
            			break;
            		}
            	}
				
				bulbaLocation = bulbaLocation + locationLine + '\n';
            	locationLine = bulbaLocationIn.nextLine();
            }
		
			if(bulbaLocation.contains('~' + listLine + '!')) {
				//System.out.println(listLine + " " + bulbaLocation);
				bulbaLocation = bulbaLocation.replace(" ", "").replace("\n", "").replace("[[route]]s", "").replace("Ã©", "e").trim();
	            listLine = LocationSet.removeBraces(listLine, "(", ")").replace("~", "e");
	            String entryLine = "";
	    		
	    		while(listLine.startsWith("Kanto ") || listLine.startsWith("Johto ") || listLine.startsWith("Hoenn ") || listLine.startsWith("Sinnoh") || listLine.startsWith("Unova ") 
	    				|| listLine.startsWith("Kalos ") || listLine.startsWith("Alola ")) {
	    			listLine = listLine.substring(6);
	    		}
	            
	            while(bulbaLocation.contains("{{catch/entry")) {
	            	bulbaLocation = LocationSet.cutTo(bulbaLocation, "{{catch/");
	            	bulbaLocation = LocationSet.cutTo(bulbaLocation, "entry");
	            	ArrayList<Integer> lookingAtGames = new ArrayList<Integer>();
	            	ArrayList<Integer> lookingAtPokemon = new ArrayList<Integer>();
	            	int[] entry = new int[255];
	            	
	            	if(bulbaLocation.contains("{catch/")) {
	            		//System.out.println(bulbaLocation);
            	        entryLine = bulbaLocation.substring(0, bulbaLocation.indexOf("{{catch/"));
            	    }else {
            		    entryLine = bulbaLocation;
            	    }
	            	
	            	if(entryLine.startsWith("entryusum")) {
	            		entry = entryusum;
	            	}else if(entryLine.startsWith("entry7")) {
	            		entry = entry7;
	            	}else if(entryLine.startsWith("entryoras")) {
	            		entry = entryoras;
	            	}else if(entryLine.startsWith("entry6")) {
	            		entry = entry6;
	            	}else if(entryLine.startsWith("entry5-2")) {
	            		entry = entry5h2;
	            	}else if(entryLine.startsWith("entry5")) {
	            		entry = entry5;
	            	}else if(entryLine.startsWith("entryhs")) {
	            		entry = entryhs;
	            	}else if(entryLine.startsWith("entry4")) {
	            		entry = entry4;
	            	}else if(entryLine.startsWith("entry3a")) {
	            		entry = entry3a;
	            	}else if(entryLine.startsWith("entryfl")) {
	            		entry = entryfl;
	            	}else if(entryLine.startsWith("entry3")) {
	            		entry = entry3;
	            	}else if(entryLine.startsWith("entry2")) {
	            		entry = entry2;
	            	}else if(entryLine.startsWith("entry1")) {
	            		entry = entry1;
	            	}else {
	            		System.out.println(entryLine.substring(0, 18) + " at " + listLine + " didn't start with anything!");
	            		continue;
	            	}
	            	
	            	for(int i = 0; i < entry.length; i ++) {
	            		if("yes".equals(LocationSet.betweenPipes(entryLine, 3 + i).trim())) {
	            			lookingAtGames.add(entry[i]);
	            		}
	            	}
	            	
	            	if(LocationSet.betweenPipes(entryLine, 1).contains("{{")) {
	            	    System.out.println(listLine + " " + LocationSet.betweenPipes(entryLine, 1));
	            	}else if((Integer.parseInt(LocationSet.betweenPipes(entryLine, 1).substring(0, 3)) > 0) 
	            			/*&& (Integer.parseInt(LocationSet.betweenPipes(entryLine, 1).substring(0, 3)) <= number)*/) {
	            	    lookingAtPokemon.add(Integer.parseInt(LocationSet.betweenPipes(entryLine, 1).substring(0, 3)));
	            	    //System.out.println(listLine + " " + pokemonErrors.get(Integer.parseInt(LocationSet.betweenPipes(entryLine, 1).substring(0, 3))));
	            	}
	            	
	            	if(entryLine.contains("allies=")){
	            		entryLine = LocationSet.cutTo(entryLine, "allies=");
	            		//System.out.println(entryLine);
	            		
	            		while(entryLine.contains("{{ms|")) {
	            			entryLine = LocationSet.cutTo(entryLine, "{{ms|");
	            			//System.out.println("adding " + LocationSet.betweenPipes(entryLine, 1).substring(0, 3) + " from " + entryLine);
	            			lookingAtPokemon.add(Integer.parseInt(LocationSet.betweenPipes(entryLine, 1).substring(0, 3)));
	            			entryLine = LocationSet.cutTo(entryLine, "{{ms|").substring(5);
	            		}
	            	}
	            	
	            	for(int i = 0; i < lookingAtPokemon.size(); i ++) {
	            		pokemonErrors.get(lookingAtPokemon.get(i)).add(2, lookingAtGames, listLine.trim());
	            	}
	            }
	            
	            bulbaLocation = "";
			}
			
			//listLine = locationListIn.nextLine();
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
