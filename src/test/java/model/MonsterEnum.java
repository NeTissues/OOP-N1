package model;

import model.AttackTypes;
import model.Monster;
import model.MonsterType;

/*
Placeholder enum until database is implemented
 */
public enum MonsterEnum {
    M001 (1, "Bulbdinossaur", MonsterType.Grass, 100, 10, 1, 11, new AttackTypes[]{AttackTypes.ABSORB, AttackTypes.TACKLE}),
    M004 (4, "Charredsalamander", MonsterType.Fire, 100, 12, 1, 9, new AttackTypes[]{AttackTypes.EMBER, AttackTypes.SCRATCH}),
    M007 (7, "Tinycrocodile", MonsterType.Water, 100, 11, 1, 10, new AttackTypes[]{AttackTypes.WATER_GUN, AttackTypes.BITE}),
    M016 (16, "Midgetpidgeon", MonsterType.Normal, 60, 8, 1, 12, new AttackTypes[]{AttackTypes.PECK, AttackTypes.HYPER_BEAM}),
    M102 (102, "Eggexecute", MonsterType.Grass, 50, 5, 1, 7, new AttackTypes[]{AttackTypes.METRONOME, AttackTypes.SOLAR_BEAM}),
    M150 (150, "owtweM", MonsterType.Normal, 150, 15, 1, 13, new AttackTypes[]{AttackTypes.WATER_PUMP, AttackTypes.SOLAR_BEAM}),
    ;

    int id;
    String monsterName;
    MonsterType type;
    int hp;
    int atk;
    int def;
    int spd;
    AttackTypes[] moves;

    MonsterEnum(int id, String monsterName, MonsterType type, int hp, int atk, int def, int spd, AttackTypes[] moves) {
        this.id = id;
        this.monsterName = monsterName;
        this.type = type;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.moves = moves;
    }

    public static Monster turnIntoMonster(MonsterEnum monsterEnum){
        return new Monster(monsterEnum);
    }
}
