package model;

public class Monster {
    private String name;
    private MonsterType type;
    private int hp;
    private int atk;
    private int def;
    private int spd;

    public Monster(String name, MonsterType type, int hp, int atk, int def, int spd) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }



}
