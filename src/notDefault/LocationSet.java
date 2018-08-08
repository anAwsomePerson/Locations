package notDefault;

import java.util.ArrayList;

public class LocationSet extends IntString{
	//String name;
    ArrayList<ArrayList<String>> bulbaList;
    ArrayList<ArrayList<String>> dbList;
    ArrayList<ArrayList<String>> bulbaLocations;
    public static final String[] games = {"Ultra Moon", "Ultra Sun", "Moon", "Sun", "Alpha Sapphire", "Omega Ruby", "Y", "X", "White 2", 
    		"Black 2", "White", "Black", "SoulSilver", "HeartGold", "Platinum", "Pearl", "Diamond", "Emerald", "LeafGreen", "FireRed", 
    		"Sapphire", "Ruby", "Crystal", "Silver", "Gold", "Yellow", "Blue", "Red"};
    public static final String[] dividers = {",", " and", ", and", "&lt;br />", "&lt;br>", "&lt;br/>"};
    public static final int sometimesDividers = 3;
    
    public LocationSet(String string, int number) {
    	super(0, string);
    	/*name = string;
    	//ArrayList<String>[] test = new ArrayList<String>[2];*/
    	bulbaList = new ArrayList<ArrayList<String>>();
    	dbList = new ArrayList<ArrayList<String>>();
    	bulbaLocations = new ArrayList<ArrayList<String>>();
    	
    	if((number >= 803) && (number <= 807)) {
            initializeList(2);
    	}else if((number >= 722) && (number <= 803)) {
    		//System.out.println("reached3");
            initializeList(4);
    	}else if((number >= 650) && (number <= 721)) {
            initializeList(8);
    	}else if((number >= 494) && (number <= 649)) {
            initializeList(12);
    	}else if((number >= 387) && (number <= 493)) {
            initializeList(17);
    	}else if((number >= 252) && (number <= 386)) {
            initializeList(22);
    	}else if((number >= 152) && (number <= 251)) {
            initializeList(25);
    	}else if((number >= 1) && (number <= 151)) {
            initializeList(28);
    	}else {
    		System.out.println(string + " was constructed with " + number + " and it was out of bounds.");
    	}
    	
    	//System.out.println(bulbaList.size());
    }
    
    public void fillLists(String bulbaString, String dbString) {
    	fillBulbaList(bulbaString);
    	fillDbList(dbString);
    	//String print = "";
    }
    
    public void compareLists() {
    	ArrayList<ArrayList<String>> other = null;
    	
    	switch(Main.mode) {
    	case 4:
    		other = dbList;
    		break;
    	case 5:
    		other = bulbaLocations;
    		break;
    	default:
    		System.out.println("mode was set wrong!");
    		return;
    	}
    	
    	for(int x = 0; x < bulbaList.size(); x ++) {
    		for(int y = 0; y < bulbaList.get(x).size(); y ++) {
    			//Main.addString(Main.locationErrors, bulbaList.get(x).get(y));
    			if(bulbaList.get(x).get(y).isEmpty()) {
    				bulbaList.get(x).remove(y);
    				y --;
    				continue;
    			}
    			
    			/*if(y >= bulbaList.get(x).size()) {
    				break;
    			}*/
    			
    			for(int z = 0; z < other.get(x).size(); z ++) {
    				//System.out.println(bulbaList.get(x).get(y) + " " + dbList.get(x).get(z));
    				
    				if(bulbaList.get(x).get(y).equals(other.get(x).get(z))) {
    					bulbaList.get(x).remove(y);
    					other.get(x).remove(z);
    					Main.right ++;
    					y --;
    					z --;
    					break;
    				}
    			}
    		}
    		
    		for(int y = 0; y < other.get(x).size(); y ++) {
    			//Main.addString(Main.locationErrors, dbList.get(x).get(y));
    			if(other.get(x).get(y).isEmpty()) {
    				other.get(x).remove(y);
    				y --;
    				continue;
    			}
    			
    			/*if(y >= dbList.get(x).size()) {
    				break;
    			}*/
    			
    			for(int z = 0; z < bulbaList.get(x).size(); z ++) {
    				if(other.get(x).get(y).equals(bulbaList.get(x).get(z))) {
    					other.get(x).remove(y);
    					bulbaList.get(x).remove(z);
    					Main.right ++;
    					y --;
    					z --;
    					break;
    				}
    			}
    		}
    		
    		for(int y = 0; y < bulbaList.get(x).size(); y ++) {
    			plusPlus();
    			Main.wrong ++;
    			Main.gameErrors[x] ++;
    			Main.addString(Main.locationErrors, bulbaList.get(x).get(y));
    			System.out.println(align(x, false, bulbaList.get(x).get(y)));
    		}
    		
    		for(int y = 0; y < other.get(x).size(); y ++) {
    			plusPlus();
    			Main.wrong ++;
    			Main.gameErrors[x] ++;
    			//System.out.println(Main.locationErrors.size() + " " + Main.locationErrors + " " + bulbaList.get(x).get(y));
    			Main.addString(Main.locationErrors, other.get(x).get(y));
    			System.out.println(align(x, true, other.get(x).get(y)));
    		}
    	}
    }
    
    public void fillBulbaList(String string) {
    	/*if(name.equals("Poliwag")){
    		//System.out.println(string);
    	}
    	
    	/*string = ;
    	//System.out.println(string);
    	string = ;
    	//System.out.println(string);*/
    	string = removeBraces(removeBraces(removeBraces(removeBraces(removeBraces(removeBraces(removeBraces(string, 
    			"&lt;!--", "-->"), "{{Sup", "}}"), "&lt;small>", "&lt;/small>"), "{{tt", "}}"), "{{dotw", "}}"), "(", ")"), "{{sup", "}}")
    			.replace("\n", "").replace("[[Route]]s", "").replace("é", "e").trim();
    	
    	/*if(name.equals("Poliwag")){
    		System.out.println(string);
    		//return;
    	}*/
    	
    	while(string.contains("{{Availability/Entry1") || string.contains("{{Availability/Entry2")){
    		ArrayList<Integer> lookingAtGames = new ArrayList<Integer>();
    		string = cutTo(string, "{{Availability/Entry").substring(1);
    		//System.out.println(string.substring(0, 128));
    		//System.out.println("index: " + string.indexOf("None"));
    		
    		if(nearStart(string, "None") || nearStart(string, "ex=")) {
    			continue;
    		}
    		
    		//System.out.println("didn't continue");
    		string = cutTo(string, "Entry");
    		
    		/*if(name.equals("Poliwag")){
        		System.out.println(string);
        	}*/
    		
    		if(((string.indexOf("Entry2") < string.indexOf("Entry1")) && (string.indexOf("Entry2") >= 0)) || (string.indexOf("Entry1") < 0)){
    			//System.out.println("Entry2");
    			string = cutTo(string, "v=");//reminder: don't move this line out of the if block
    			//System.out.println(string.substring(2, string.indexOf('|')) + " " + indexOf(games, string.substring(2, string.indexOf('|'))));
    			lookingAtGames.add(indexOf(games, string.substring(2, string.indexOf('|'))));
    			string = cutTo(string, "v2=");
    			//System.out.println(string.substring(3, string.indexOf('|')));
    			lookingAtGames.add(indexOf(games, string.substring(3, string.indexOf('|'))));
    		}else {
    			if(nearStart(string, "Colosseum") || nearStart(string, "XD") || nearStart(string, "Pal Park") || nearStart(string, "Pokewalker") || nearStart(string, "Dream World")) {
        			continue;
        		}
    			
    			//System.out.println("Entry1");
    			string = cutTo(string, "v=");
    			lookingAtGames.add(indexOf(games, string.substring(2, string.indexOf('|'))));
    		}
    		
    		string = cutTo(string, "area=");
    		/*System.out.println(string);
    		System.out.println(string.substring(5, positiveMin(string.indexOf("}}{{Availability/Entry"), string.indexOf("}}|}|}{{Availability/Footer"))));*/
			ArrayList<String> locations = toBulbaList(string.substring(5, positiveMin(positiveMin(string.indexOf("}}{{Availability/Entry"), string.indexOf("}}|}|}{{Availability/Footer")), 
					string.indexOf("}}|}|}{{Availability/Gen"))));
			/*/System.out.println(lookingAtGames.get(0) + " " + locations);
			
			/*if("Poliwag".equals(name)) {
    			System.out.println(locations.toString());
    		}*/
    		
    		for(int x = 0; x < locations.size(); x ++) {
    			if(locations.get(x).equals("[[Trade]]") || locations.get(x).contains("[[Event]]")) {
    				continue;
    			}
    			
    			//System.out.println(locations.get(x).toLowerCase().contains("received") + " " + locations.get(x));
    			
    			if(locations.get(x).toLowerCase().contains("received") || locations.get(x).contains("Trade") || locations.get(x).startsWith("[[Starter") 
    					|| locations.get(x).contains("Revive") || locations.get(x).contains("Buy")){
    				locations.set(x, locations.get(x).substring(Math.max(Math.max(locations.get(x).lastIndexOf(" in "), locations.get(x).lastIndexOf(" on ")), 
    						locations.get(x).lastIndexOf(" at ")) + 4));
    				/*/System.out.println(locations.get(x));
    				locations.set(x, locations.get(x).substring(0, positiveMin(locations.get(x).indexOf("]]"), locations.get(x).indexOf("}}"))));
    				/*System.out.println(locations.get(x));
    				if(locations.get(x).endsWith("friendship") || (locations.get(x).indexOf("Badge") > Math.max(locations.get(x).length() - 16, 0))) {
    					locations.set(x, locations.get(x).substring(0, locations.get(x).indexOf("]]")));
    				}*/
    			}
    			
    			if(locations.get(x).contains(" or")) {
    				locations.add(locations.get(x).substring(0, locations.get(x).indexOf(" or")));
    				locations.add(cutTo(locations.get(x), " or").substring(3));
    				locations.remove(x);
    				x --;
    				continue;
    			}
    			
    			if(locations.get(x).contains(" after ")) {
    				locations.set(x, locations.get(x).substring(0, locations.get(x).indexOf(" after ")));
    			}
    			
    			if(locations.get(x).contains(" if ")) {
    				locations.set(x, locations.get(x).substring(0, locations.get(x).indexOf(" if ")));
    			}
    			
    			if(locations.get(x).contains("|Gym")) {
    				continue;
    			}
    			
    			if(locations.get(x).startsWith("Evol") || locations.get(x).startsWith("Breed")) {
    				continue;
    				//break;
    			}
    			
    			if(locations.get(x).contains("[[") || locations.get(x).contains("{{")) { 
    			    locations.set(x, locations.get(x).substring(positiveMin(locations.get(x).indexOf("[["), locations.get(x).indexOf("{{")), 
    					Math.max(locations.get(x).lastIndexOf("]]"), locations.get(x).lastIndexOf("}}")) + 2));
    			}else {
    				System.out.println("doesn't contain '[[' or '{{': " + locations.get(x));
    			}
    			
    			locations.set(x, locations.get(x).replace("[[", "").replace("]]s", "").replace("]]", "")
    					.replace("Game Corner", "City").replace("Department Store", "City").replace("Gym", "City").replace("Condominiums", "City").trim());
    			
    			if(locations.get(x).isEmpty() || locations.get(x).contains("Pokéwalker") || locations.get(x).contains("Friend Safari") || locations.get(x).contains("Dream Radar")) {
					continue;
			    }
    			
    			if(locations.get(x).startsWith("Evol") || locations.get(x).contains("pkmn|breeding") || locations.get(x).contains("Breed")) {
    				continue;
    				//break;
    			}
    			
    			if(locations.get(x).contains("{{color2")) {
    				//System.out.println(locations.get(x));
    				locations.set(x, locations.get(x).substring(0, locations.get(x).indexOf("{{color2")));
    			}
    			
    			if(locations.get(x).contains("Roaming")) {
    				locations.set(x, "Roaming Pokemon");
    			}
    				
    			if(locations.get(x).contains("{{rt")){
    				locations.set(x, "Route " + betweenPipes(cutTo(locations.get(x), "{{rt"), 1));
    			}else if(locations.get(x).toLowerCase().startsWith("{{safari")) {
    				locations.set(x, "Safari Zone");
    			}else if(locations.get(x).startsWith("{{FB")) {
    				locations.set(x, betweenPipes(locations.get(x), 2));
    			}else if(locations.get(x).startsWith("{{")) {
    				/*String test = betweenPipes(locations.get(x), 1);
    				System.out.println(x + " " + locations.toString() + " " + test);*/
    				locations.set(x, betweenPipes(locations.get(x), 1));
    				//System.out.println(x + " " + locations.toString());
    			}
    			
    			if(locations.get(x).contains("|")) {
    				locations.set(x, betweenPipes(locations.get(x), 1));
    			}
    			
    			if(locations.get(x).trim().isEmpty()) {
    				continue;
    			}
    				
    			add(0, lookingAtGames, locations.get(x).replace("}}", "").trim());
    		}
    		
    		//System.out.println(bulbaList.toString());
    	}
    	
    	/*if(name.equals("Poliwag")){
    		System.out.println(bulbaList.toString());
    	}*/
    }
    
    public void fillDbList(String string) {
    	string = string.replace("\n", "").replace("é", "e");
    	
    	while(string.contains("span class=\"igame")){
    		ArrayList<Integer> lookingAtGames = new ArrayList<Integer>();
    		
    		while(string.indexOf("span class=\"igame") == positiveMin(string.indexOf("span class=\"igame"), string.indexOf("<td>"))) {
    			//System.out.println(string.substring(0, 64));
    		    string = cutTo(cutTo(string, "span class=\"igame"), ">").substring(1);
    		    //System.out.println(string.substring(0, 64) + " \n");
    		    lookingAtGames.add(indexOf(games, (string.substring(0, string.indexOf("<")))));
    		    //System.out.println(string.substring(0, string.indexOf("<")));
    		}
    		
    		//System.out.println(string.substring(0, 64));
    		string = cutTo(string, "<td>").substring(4);
    		
    		if(string.indexOf("<small>") == 0) {
    			//string = cutTo(string, "span class=\"igame");
    			continue;
    		}
    		
    		ArrayList<String> locations = toDbList(removeBraces(string.substring(0, string.indexOf("</td>")), "<", ">"));
    		
    		for(int x = 0; x < locations.size(); x ++) {
    			//System.out.println("reached3");
    			locations.set(x, locations.get(x).trim());
    			
    			if(locations.get(x).length() <= 3) {
    				locations.set(x, "Route " + locations.get(x));
    			}
    			
    			add(1, lookingAtGames, locations.get(x).trim());
    		}
    		
    		//System.out.println("reached2");
    	}
    	
    	//System.out.println(dbList);
    }
    
    public String align(int game, boolean positive, String location) {
    	String out = toString();
    	
    	while(out.length() < 13) {
    		out = out + " ";
    	}
    	
    	out = out + games[game];
    	
    	while(out.length() < 28) {
    		out = out + " ";
    	}
    	
    	if(positive) {
    		out = out + "+";
    	}else {
    		out = out + "-";
    	}
    	
    	return(out + location);
    }
    
    public void add(int listSelector, ArrayList<Integer> indexList, String string) {
    	if(listSelector == 0) {
    		staticAdd(bulbaList, indexList, string);
    	}else if(listSelector == 1) {
    		staticAdd(dbList, indexList, string);
    	}else if (listSelector == 2){
    		staticAdd(bulbaLocations, indexList, string);
    	}else {
    		System.out.println(listSelector + " is making me do nothing!");
    	}
    }
    
    public void initializeList(int length) {
    	for(int i = 0; i < length; i ++) {
    		bulbaList.add(new ArrayList<String>());
    		dbList.add(new ArrayList<String>());
    		bulbaLocations.add(new ArrayList<String>());
    	}
    }
    
    public static ArrayList<String> toBulbaList(String string){
    	ArrayList<String> lines = new ArrayList<String>();
    	String firstDivider = "&lt;br />";
		
		while(containsOne(string, dividers, sometimesDividers)) {
			for(int i = sometimesDividers; i < dividers.length; i ++) {				
				if(string.contains(dividers[i]) && (positiveMin(string.indexOf(dividers[i]), string.indexOf(firstDivider)) == string.indexOf(dividers[i]))){
					firstDivider = dividers[i];
				}
			}
			
			/*if((string.indexOf(firstDivider) > string.indexOf("[[")) && (string.indexOf(firstDivider) < string.indexOf("]]"))){
				string = string.replaceFirst(firstDivider, "");
				continue;
			}
			if((string.indexOf(firstDivider) > string.indexOf("{{")) && (string.indexOf(firstDivider) < string.indexOf("}}"))){
				string = string.replaceFirst(firstDivider, "");
				continue;
			}*/
			
			if(!string.substring(0, string.indexOf(firstDivider)).contains("Evol") && !string.substring(0, string.indexOf(firstDivider)).toLowerCase().contains("breed")) {
				lines.add(string.substring(0, string.indexOf(firstDivider)).trim());
			}
			
			string = string.substring(string.indexOf(firstDivider) + firstDivider.length()).trim();
		}
    	
		/*System.out.println(string + " " + firstDivider + " " + string.indexOf(firstDivider));
		System.out.println(string.substring(0, string.indexOf(firstDivider)));*/
		
		if(!string.contains("Evol") && !string.toLowerCase().contains("breed")) {
		    lines.add(string.trim());
		}
		
		ArrayList<String> out = new ArrayList<String>();
		firstDivider = ",";
		
		for(int x = 0; x < lines.size(); x ++) {
		    while(containsOne(lines.get(x), dividers, 0)) {
			    for(int y = 0; y < dividers.length; y ++) {				
				    if(lines.get(x).contains(dividers[y]) && (positiveMin(lines.get(x).indexOf(dividers[y]), lines.get(x).indexOf(firstDivider)) == lines.get(x).indexOf(dividers[y]))){
					    firstDivider = dividers[y];
				    }
			    }
			
			    //System.out.println(lines.get(x) + " " + firstDivider);
			    
			    if(numberContained(lines.get(x), firstDivider) > numberContained(removeBraces(lines.get(x), "[[", "]]"), firstDivider)){
			    	//System.out.println(lines.get(x));
			        lines.set(x, lines.get(x).substring(0, lines.get(x).indexOf("[[")) + cutTo(lines.get(x), "[[").replaceFirst(firstDivider, ""));
			        continue;
			    }
			//System.out.println("reached0");
			    if(numberContained(lines.get(x), firstDivider) > numberContained(removeBraces(lines.get(x), "{{", "}}"), firstDivider)){
			    	//System.out.println(lines.get(x) + " " + firstDivider);
			        lines.set(x, lines.get(x).substring(0, lines.get(x).indexOf("{{")) + cutTo(lines.get(x), "{{").replaceFirst(firstDivider, ""));
			        continue;
			    }
			
			/*if(!string.contains(firstDivider)) {
				//System.out.println(string);
				continue;
			}
			
			/*if(test) {
				System.out.println("didn't break, " + firstDivider + " " + string.indexOf(firstDivider));
			}*/
			
			    out.add(lines.get(x).substring(0, lines.get(x).indexOf(firstDivider)).trim());
			//System.out.println(string.substring(0, string.indexOf(firstDivider)).trim());
			    lines.set(x, lines.get(x).substring(lines.get(x).indexOf(firstDivider) + firstDivider.length()).trim());
			/*System.out.println("reached0 " + string);
			
			/*for(int i = 0; i < dividers.length; i ++) {				
				if(string.contains(dividers[i]) && (positiveMin(string.indexOf(dividers[i]), string.indexOf(firstDivider)) == string.indexOf(dividers[i]))){
					firstDivider = dividers[i];
					//System.out.println("reached2");
				}
				
				/*if(test) {
					System.out.println(firstDivider + " " + string.indexOf(firstDivider) + " " + string.indexOf("[[") + " " + string.indexOf("]]"));
				}
			}*/
			}
		
		//System.out.println("reached1 " + string.trim());
		    out.add(lines.get(x).trim());
		}
		
		return(out);
	}
    
    public static ArrayList<String> toDbList(String string){
		ArrayList<String> out = new ArrayList<String>();
		
		while(string.contains(",")) {
			out.add(string.substring(0, string.indexOf(",")).trim());
			string = string.substring(string.indexOf(",") + ",".length()).trim();
		}
		
		out.add(string.trim());
		return(out);
	}
    
    public static int numberContained(String container, String contained) {
    	int out = 0;
    	
    	while(container.contains(contained)) {
    		out ++;
    		container = cutTo(container, contained).substring(contained.length());
    	}
    	
    	return(out);
    }
    
    public static boolean containsOne(String string, String[] array, int start) {
    	for(int i = start; i < array.length; i ++) {
    		if(string.contains(array[i])) {
    			return(true);
    		}
    	}
    	
    	return(false);
    }
    
    public static void staticAdd(ArrayList<ArrayList<String>> list, ArrayList<Integer> indexList, String string) {
    	/*if("Caterpie".equals(super.string) && (list == bulbaLocations)) {
    		System.out.println("\"static\" adding " + string + " to " + indexList);
    	}*/
    	
    	for(int x = 0; x < indexList.size(); x ++) {
    		if(list.size() < indexList.get(x)) {
    			System.out.println("out of bounds: " + list + " " + indexList + " " + string);
    			continue;
    		}
    		
    		boolean repeated = false;
    		
    	    for(int y = 0; y < list.get(indexList.get(x)).size(); y ++) {
    		    if(list.get(indexList.get(x)).get(y).equals(string)){
    			    repeated = true;
    			    break;
    		    }
    	    }
    	    
    	    if(repeated) {
    	    	continue;
    	    }
    	
    	    list.get(indexList.get(x)).add(string);
    	    
    	    /*if("Caterpie".equals(super.string) && (list == bulbaLocations)) {
        		System.out.println("\"static\" added " + string + " to " + indexList);
        	}*/
    	}
    }
    
    public boolean nearStart(String string, String target) {
    	return((string.indexOf(target) < 32) && (string.indexOf(target) >= 0));
    }
    
    public static String betweenPipes(String string, int number) {
		//System.out.println(string);
		
		for(int i = 0; i < number; i ++) {
			string = string.substring(string.indexOf('|') + 1);
			//System.out.println(string);
		}
		
		if(!string.contains("|")) {
			return(string);
		}
		
		//System.out.println("returning " + string.substring(0, string.indexOf('|')));
		return(string.substring(0, string.indexOf('|')));
	}
    
    public static String cutTo(String string, String newStart) {
    	if(!string.contains(newStart) && "&lt;/small>".equals(newStart)) {
    		return(string.substring(string.indexOf("}}")));
    	}
    	
    	return(string.substring(string.indexOf(newStart)));
    }
    
    public static String removeBraces(String string, String start, String end) {
    	while(string.contains(start)) {
    		string = string.substring(0, string.indexOf(start)) + cutTo(cutTo(string, start), end).substring(end.length());
    	}
    	
    	return(string);
    }
    
    public static String removeBrace(String string, String start, String end) {
    	return(string.substring(0, string.indexOf(start)) + cutTo(cutTo(string, start), end).substring(end.length()));
    }
    
    public static int indexOf(String[] array, String string) {
    	//System.out.println(string);
    	
		for(int i = 0; i < array.length; i ++) {
			/*String maybeSubstring = string;
			String maybeSubarray = array[i];
			
			/*if((array.length > 14) && (array.length < 20)) {
				maybeSubstring = string.substring(0, 3);
				maybeSubarray = array[i].substring(0, 3);
			}*/
			
			if(string.equals(array[i])) {
				return(i);
			}
		}
    
		System.out.println(string + " is making me return -1!");
		return(-1);
	}
    
    public static int positiveMin(int a, int b) {
    	if(Math.min(a, b) < 0) {
    		return(Math.max(a, b));
    	}
    	
    	return(Math.min(a, b));
    }
}
