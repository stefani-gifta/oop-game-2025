package Monster;

public static class AddingMonster {

	public static ArrayList<Monster> monsterList = new ArrayList<>();

	public ArrayList<Item> getMonsters() {
        return monsterList;
    }
	
    public AddingMonster() {
        addStrengthMonster();
        addIntelligenceMonster();
        addAgilityMonster();
    }

    private static void addStrengthMonster() {
		ArrayList<String> Strength = new ArrayList<>(Arrays.asList("Earthshaker", "Tiny", "Biggy", "Beastmaster", "Dragon Knight"));
		for (String string : Strength) {
			double damage = Randomizer.random(20, 30);
			double health = Randomizer.random(200, 210);
			double armor = Randomizer.random(20, 40);
			Strength strength = new Strength(string, damage, health);
			strength.setArmor(armor);
			monsterList.add(strength);
		}
    }

    private static void addIntelligenceMonster() {
		ArrayList<String> Intelligence = new ArrayList<>(Arrays.asList("Enchantress", "Tinker", "Zeus", "Nature's Prophet", "Crystal Maiden"));
		for (String string : Intelligence) {
			double damage = Randomizer.random(10, 20);
			double health = Randomizer.random(100, 110);
			Intelligence intelligence = new Intelligence(string, damage, health);
			monsterList.add(intelligence);
		}
    }

    private static void addAgilityMonster() {
		ArrayList<String> Agility = new ArrayList<>(Arrays.asList("Juggernaut", "Sniper", "Vengeful Spirit", "Phantom Lancer", "Firanha"));
		for (String string : Agility) {
			double damage = Randomizer.random(40, 50);
			double health = Randomizer.random(100, 110) - damage;
			double critical = Randomizer.random(1, 3);
			Agility agility = new Agility(string, damage, health);
			agility.setCritical(critical);
			monsterList.add(agility);
		}
	}

}
