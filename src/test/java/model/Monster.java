package model;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return getHp() == monster.getHp() && getAtk() == monster.getAtk() && getDef() == monster.getDef() && getSpd() == monster.getSpd() && Objects.equals(getName(), monster.getName()) && getType() == monster.getType() && Arrays.equals(getMoves(), monster.getMoves());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getType(), getHp(), getAtk(), getDef(), getSpd());
        result = 31 * result + Arrays.hashCode(getMoves());
        return result;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", spd=" + spd +
                ", moves=" + Arrays.toString(moves) +
                '}';
    }
}
