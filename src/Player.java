/**
* This class represents a player class
* @author Anthony
 */

public class Player {
    /**
     * The HP of the Player.
     */
    private int HP;
    /**
     * The max HP of the player.
     */
    private int maxHP;
    /**
     * The minimum base damage the player can deal.
     */
    private int minDmg;
    /**
     * The maximum base damage the player can deal.
     */
    private int maxDmg;
    /**
     * The class of the player.
     */
    private String playerClass;
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The # of pots the player has.
     */
    private int potCounter;
    /**
     * The Inventory of the player.
     */
    private String inventory;
    /**
     * The itemlist where all items are referenced.
     */
    ItemList list = new ItemList();

    /**
     * Instantiates a player object
     * <p>
     *     PRECONDITION: pClass = 1, 2, or 3
     * </p>
     * @param name Name of the user
     * @param pClass The int corresponding to player class
     */
    public Player(String name, int pClass) {
        this.name = name;
        inventory = "";
        if (pClass == 1) {
            playerClass = "Warrior";
            HP = 30;
            maxHP = 30;
            minDmg = 5;
            maxDmg = 6;
            addInventory(list.getLight());
        } else if (pClass == 2) {
            playerClass = "Archer";
            HP = 20;
            maxHP = 20;
            minDmg = 7;
            maxDmg = 9;
        } else if (pClass==3) {
            playerClass = "Knight";
            HP = 40;
            maxHP = 40;
            minDmg = 3;
            maxDmg = 5;
        }
        potCounter = 1;
    }


    /**
     * Returns the HP of the player.
     * @return HP of the player
     */
    public int getHP() {
        return HP;
    }

    /**
     * Returns the Max HP of the player.
     * @return Max HP of the player
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the name of the player.
     * @return The name of the player
     */
    public String getName () {
        return name;
    }

    /**
     * Returns # of health pots in possession.
     * @return # of health pots the player has
     */
    public int getPots () {
        return potCounter;
    }

    /**
     * Returns the Inventory of the Player.
     * @return The inventory of the player
     */
    public String getInventory() {
        return inventory;
    }

    /**
     * Returns the ItemList object referenced by the Player object.
     * @return the ItemList object
     */
    public ItemList getList() {
        return list;
    }

    /**
     * Doubles Max HP.
     */
    public void doubleMaxHP() {
        maxHP *= 2;
    }

    /**
     * Adds an item (String) to the inventory.
     * @param item The item being added to the inventory
     */
    public void addInventory(String item) {
        inventory += item;
    }

    /**
     * Changes the # of HP pots the player has.
     * @param num How the number is changed, + adds, - subtracts.
     */
    public void changePot (int num) {
        potCounter += num;
    }

    /**
     * Determines the damage taken for the player.
     * <p>
     *     Inherent dodge chance for any class, 10% for Warrior and Knight, 30% for Archer.
     *     If Player has the Herme's Boots in their inventory, dodge chance is doubled.
     * </p>
     * @param num How much damage was done to the player.
     */
    public void loseHP(int num) {
        double dodgeRoll = Math.random();
        double dodgeChance = .1;
        if(playerClass.equals("Archer")) {
            dodgeChance= .3;
        }
        if(list.haveBoots()) {
            dodgeChance *=2;
        }
        if (dodgeRoll<=dodgeChance) {
            System.out.println(" " + getName() + " evaded the attack!");
        }
        else {
            HP -= num;
            System.out.println("" + getName() + " loses " + num + " HP!");
        }
    }

    /**
     * Returns the status of the player as a String.
     */
    public void getInfo() {
        System.out.println("Name: " + name + "\n" +
                "Class: " + playerClass + "\n" +
                "HP: " + getHP() + "/" + getMaxHP() +
                "\nATK: " + minDmg + "-" + maxDmg);
        if (playerClass.equals("Knight") && getHP() <= getMaxHP()/2) {
            System.out.println("You are enraged! (1.5x DMG) ");
        }
        if (playerClass.equals("Archer")) {
            System.out.println("You are blessed by the winds. (30% Evasion) ");
        }
    }

    /**
     * Determines the amount of damage the Player does.
     * <p>
     *     Base Damage is a random # between the min and max DMG of the player class.
     *     If the player has the Blacksmith's Blessing, damage is doubled.
     *     If the player is a Knight and is under half HP, damage is doubled.
     * </p>
     * @return The damage after calculations
     */
    public int dmgOutput() {
        int dmg = 0;
        dmg = (int) (Math.random() * (maxDmg-minDmg + 1))+minDmg;
        if (playerClass.equals("Knight") && getHP() <= getMaxHP()/2) {
            dmg *= 2;
        }
        if (list.haveBlessing()) {
            dmg *=2 ;
        }
        return (int) dmg;
    }

    /**
     * Determines whether the player is dead. If HP <= 0, the player is dead.
     * If the player is a Warrior and has the Warrior's Light in their inventory, HP is restored to max and the Warrior's Light is removed from their inventory.
     * @return Whether the player is dead or not.
     */
    public boolean dead() {
        if((inventory.indexOf(list.getLight())!=-1) && HP <= 0) {
            System.out.println("The gods admire your determination and grant you a second life. ");
            inventory = inventory.substring(0,inventory.indexOf(list.getLight())) + inventory.substring(inventory.indexOf(list.getLight()) + list.getLight().length());
            HP = maxHP;
        }
        return HP<=0;
    }

    /**
     * Heals the player by changing the HP variable by adding different amounts.
     * <p>
     *     IF the player has the Priest's Cross in their inventory, healing is doubled.
     *     If the player heals over their max HP, their HP is set to their max HP.
     * </p>
     * @param num
     */
    public void heal(int num) {
        if (list.haveCross()) {
            System.out.println("The Priest's cross glows faintly. (x2 healing)");
            HP += num*2;
        } else {
            HP += num;
        }
        if (HP > maxHP) {
            HP = maxHP;
        }

    }

    /**
     * Overloaded heal() method, heals by a base of 1.
     */
    public void heal() {
        heal(1);
    }
}