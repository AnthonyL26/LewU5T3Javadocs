public class ItemList{
    private String light;
    private String blessing;
    private String boots;
    private String shield;
    private String cross;
    private boolean haveBlessing;
    private boolean haveBoots;
    private boolean haveShield;
    private boolean haveCross;
    private String[] items;

    public ItemList() {
        light = "Warrior's Light: Revive on First Death\n";
        blessing = "Blacksmith's Blessing: Deal Double Damage\n";
        boots = "Hermes's Boots: Double Evasion Chance\n";
        shield = "Heart's Shield: Double Max HP\n";
        cross = "Priest's Cross: Double Incoming Healing\n";
        items = new String[4];
        items[0] = blessing;
        items[1] = boots;
        items[2] = shield;
        items[3] = cross;
        haveBlessing = haveBoots = haveShield = haveCross = false;
    }
    //Getter Methods
    public boolean haveBlessing() {
        return haveBlessing;
    }
    public boolean haveBoots() {
        return haveBoots;
    }
    public boolean haveShield() {
        return haveShield;
    }
    public boolean haveCross() {
        return haveCross;
    }
    public String getLight() {
        return light;
    }
    //Gives a random item, if player already has the rolled item, roll again. Return nothing if player has all items.
    public String randomItem() {
        boolean newItem = false;
        int num = 0;
        while (!newItem) {
            num = (int) (Math.random()*(items.length));
            if (items[num].equals(blessing) && haveBlessing == false) {
                haveBlessing = true;
                newItem = true;
                return blessing;
            } else if (items[num].equals(boots) && haveBoots == false) {
                haveBoots = true;
                newItem = true;
                return boots;
            } else if (items[num].equals(shield) && haveShield == false) {
                haveShield = true;
                newItem = true;
                return shield;
            } else if (items[num].equals(cross) && haveCross == false) {
                haveCross = true;
                newItem = true;
                return cross;
            } else if (haveBlessing == true && haveBoots == true && haveCross == true && haveShield == true) {
                return "";
            } else {
                num = (int) (Math.random()*(items.length));
            }
        }
        return "";
    }
}