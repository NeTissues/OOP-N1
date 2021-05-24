package model;

public enum AttackTypes {
    WATER_GUN ("Water gun", 40, MonsterType.Water),
    EMBER ("Ember", 40, MonsterType.Fire),
    ABSORB ("Absorb", 20, MonsterType.Grass),
    BITE ("Bite", 20, MonsterType.Normal),
    PECK ("Peck", 20, MonsterType.Normal),
    METRONOME ("Metronome", 0, MonsterType.Normal),
    TACKLE ("Tackle", 20, MonsterType.Normal),
    SCRATCH ("Scratch", 20, MonsterType.Normal),
    WATER_PUMP ("Water pump", 120, MonsterType.Water),
    FIRE_PUNCH ("Fire punch", 120, MonsterType.Fire),
    SOLAR_BEAM ("Solar beam", 120, MonsterType.Grass),
    HYPER_BEAM ("Hyper beam", 120, MonsterType.Normal),
    ;

    private String attackName;
    private int attackPower;
    private MonsterType attackType;

    AttackTypes(String attackName, int attackPower, MonsterType attackType) {
        this.attackName = attackName;
        this.attackPower = attackPower;
        this.attackType = attackType;
    }

    /*
    *   Getters
    */

    /**
     * Returns the name of the attack
     * @return attackName
     */
    public String getAttackName() { return attackName; }

    /**
     * Returns the power of the attack
     * @return attackPower
     */
    public int getAttackPower() { return attackPower; }

    /**
     * Returns the type of the attack
     * @return attackType
     */
    public MonsterType getAttackType(){ return this.attackType; }

    /*
    *   Setters
    */
    public void setAttackName(String attackName) { this.attackName = attackName; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public void setAttackType(MonsterType attackType) { this.attackType = attackType; }

    public double returnTypeMultiplier(MonsterType attackingMonsterType, MonsterType targetMonsterType){
        double multiplier;
        switch (targetMonsterType){
            case Grass:
                if (attackingMonsterType == MonsterType.Water)
                    multiplier = 0.25;
                else if (attackingMonsterType == MonsterType.Grass)
                    multiplier = 0.5;
                else if (attackingMonsterType == MonsterType.Fire)
                    multiplier = 2;
                else multiplier = 1;
                break;

            case Water:
                if (attackingMonsterType == MonsterType.Fire)
                    multiplier = 0.25;
                else if (attackingMonsterType == MonsterType.Water)
                    multiplier = 0.5;
                else if (attackingMonsterType == MonsterType.Grass)
                    multiplier = 2;
                else multiplier = 1;
                break;

            case Fire:
                if (attackingMonsterType == MonsterType.Grass)
                    multiplier = 0.25;
                else if (attackingMonsterType == MonsterType.Fire)
                    multiplier = 0.5;
                else if (attackingMonsterType == MonsterType.Water)
                    multiplier = 2;
                else multiplier = 1;
                break;
            default:
                multiplier = 1;
        }
        return multiplier;
    }

    public double returnMoveDamage(int moveAttackPower, Monster monster, Monster targetMonster){
        int postDefense = monster.getAtk()/targetMonster.getDef();
        double damage;

        damage = (((moveAttackPower * postDefense)/50)+2) * returnTypeMultiplier(monster.getType(), targetMonster.getType());
        return damage;
    }
}
