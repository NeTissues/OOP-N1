package model;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author NeTissues
 */
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

    /**
     * @return The name of the <code>Monster</code>
     */
    public String getName() {
        return name;
    }

    /**
     * @param name String which will assume as the new name of the <code>Monster</code>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The type of the <code>Monster</code> from the <a href="#{@link}">{@link MonsterType}</a> Enum that
     * this instance possesses
     */
    public MonsterType getType() {
        return type;
    }

    /**
     * @param type <a href="#{@link}">{@link MonsterType}</a> which will assume as the new type of the <code>Monster</code>
     */
    public void setType(MonsterType type) {
        this.type = type;
    }

    /**
     * @return The Integer value of the current hp attribute from this instance of Monster
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param hp Integer value which will assume as the new hp attribute of the <code>Monster</code>
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return The Integer value of the current atk attribute from this instance of Monster
     */
    public int getAtk() {
        return atk;
    }

    /**
     * @param atk Integer value which will assume as the new atk attribute of the <code>Monster</code>
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * @return The Integer value of the current def attribute from this instance of Monster
     */
    public int getDef() {
        return def;
    }

    /**
     * @param def Integer value which will assume as the new def attribute of the <code>Monster</code>
     */
    public void setDef(int def) {
        this.def = def;
    }

    /**
     * @return The Integer value of the current spd attribute from this instance of Monster
     */
    public int getSpd() {
        return spd;
    }

    /**
     * @param spd Integer value which will assume as the new spd attribute of the <code>Monster</code>
     */
    public void setSpd(int spd) {
        this.spd = spd;
    }

    /**
     * @return The Array of <a href="@link"{@link AttackTypes}</a> containing the <code>Monster</code>'s moves
     */
    public AttackTypes[] getMoves() {
        return moves;
    }

    /**
     * @param moves Array of <a href="@link"{@link AttackTypes}</a> which will become the new moves set of this <code>Monster</code>
     */
    public void setMoves(AttackTypes[] moves) {
        this.moves = moves;
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
