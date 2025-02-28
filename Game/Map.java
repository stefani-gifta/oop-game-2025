package Game;

import java.util.ArrayList;

import Util.Randomizer;

public class Map {

	// generate 300x300 empty map
	
	private char[][] generateEmptyBigMap() {
		char[][] bigMap = new char[300][300];
		for(int i = 0; i < 300; i++) {
            for(int j = 0; j < 300; j++) {
                bigMap[i][j] = ' ';
            }
		}
		return bigMap;
	}
	
	// generate 3x3 to fill the big map
	
	public ArrayList<String> generateSmallMap(String optionString) {
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0; i < 3; i++) {
			array.add("");
			String currentString = array.get(i);
			for(int j = 0; j < 3; j++) {
				currentString += Randomizer.getRandomCharFrom(optionString);
			}
			array.set(i, currentString);
//			System.out.println(array.get(i));
		}
		return array;
	}

	public char[][] generateFilledBigMap() {
		char[][] bigMap = generateEmptyBigMap();
		fillEmptyBigMap(bigMap);
		putCoinsRandomly(bigMap);
		return bigMap;
	}

	String grassOptionString = " vV";
	String wallOptionString = " #";

	private void fillEmptyBigMap(char[][] bigMap) {
		for(int j = 0; j < 300; j++) {
			for(int i = 0; i < 300; i++) {
				if(!isSidebySide(bigMap, i, j)) {
					// randomize fill with grass/wall/empty
					int random = Randomizer.randomInt(1, 3);
					switch(random) {
						case 1:
							// 3x3 grass
							ArrayList<String> grass = generateSmallMap(grassOptionString);
							for(int x = 0; x < 3; x++) {
								for(int y = 0; y < 3; y++) {
									if(i+x < 300 && j+y < 300) {
										bigMap[i+x][j+y] = grass.get(x).charAt(y);
//										System.out.print(grass.get(x).charAt(y));
//										System.out.print(bigMap[i][j]);
									}
								}
//								System.out.println();
							}
//							System.out.println();
							break;
						case 2:
							// 3x3 wall
							ArrayList<String> wall = generateSmallMap(wallOptionString);
							for(int x = 0; x < 3; x++) {
								for(int y = 0; y < 3; y++) {
									if(i+x < 300 && j+y < 300) {
										bigMap[i+x][j+y] = wall.get(x).charAt(y);
//										System.out.print(bigMap[i][j]);
									}
								}
//								System.out.println();
							}
//							System.out.println();
							break;
						default:
							break;
					}
				}
//				System.out.print(bigMap[i][j]);
			}
//			System.out.println();
		}
	}
	
	private boolean isSidebySide(char[][] bigMap, int j, int i) {
		for(int a = Math.max(0, i-3); a < Math.min(300, i+3); a++) {
			for(int b = Math.max(0, j-3); b < Math.min(300, j+3); b++) {
				if(bigMap[b][a] == 'v' || bigMap[b][a] == 'V' || bigMap[b][a] == '#') {
					return true;
				}
			}
		}
		return false;
	}
	
	// randomize 200 coins in the filled big map
	
	private void putCoinsRandomly(char[][] bigMap) {
		for(int i = 0; i < 200; i++) {
			int indexX = 0, indexY = 0;
			do {
				indexX = Randomizer.randomInt(0, 299);
				indexY = Randomizer.randomInt(0, 299);
			} while(bigMap[indexY][indexX] != ' ');
			bigMap[indexY][indexX] = 'O';
		}
	}

}
