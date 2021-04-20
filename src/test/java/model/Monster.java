package model;

public class Monster {
    private String name;
    private MonsterType type;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private AttackTypes[] moves; //Each pocket monster needs to have at least 2 unique attacks

    public Monster(String name, MonsterType type, int hp, int atk, int def, int spd, AttackTypes[] moves) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonsterType getType() {
        return type;
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public AttackTypes[] getMoves() {
        return moves;
    }

    public void setMoves(AttackTypes[] moves) {
        this.moves = moves;
    }

    public void useMove(){

    }

}
