# Locations
I think this works now. 

This project basically does 2 things. It downloads Pokemon location information and finds inconsistencies among them. If you download this project, then you can decide which one it does by changing the number after "public static final int mode = " in Main.java . Note that it only cares about Pokemon, games, and locations, so it won't find inconsistencies in things like encounter methods, chances, and encounter levels. 

Modes 1 through 3 are download modes. They download certain webpages onto a certain file. For some reason, the last few lines will always be missing on the file. To get a complete file, you have to look at the file, figure out which lines are missing, and copy-paste those lines from the console. (the console should always have the last few lines) Additionally, the console might print something like "504 from <a webpage>" when the program finishes running. This means those pages didn't download properly and you have to re-download them. 

Modes 4 through 5 are compare modes. They will print inconsistencies into the console. Each inconsistency is formatted as a Pokemon, a game, a + or - sign, and a location. + means it's not in Bulbapedia's Pokedex page but is in the other file that's being compared, and - means the opposite. It will also print an overview of the inconsistencies at the end. 

0 mode was originally intended to download Pokemon DB Pokedex pages, Bulbapedia Pokedex pages, and Bulbapedia location pages, but it doesn't work. 

1 mode downloads Bulbapedia Pokedex pages onto bulbaDownload0.txt . 

2 mode downloads Pokemon DB Pokedex pages onto dbDownload0.txt . 

3 mode downloads Bulbapedia location pages onto bulbaLocations.txt . 

4 mode compares bulbaDownload0.txt to dbDownload0.txt . 

5 mode compares bulbaDownload0.txt to dbDownload.txt . 

By the way, you should probably make sure pokemonList.txt and locations.txt are up-to-date when using this. 
