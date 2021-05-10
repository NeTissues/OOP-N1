/*
    1- Create monsters
       1.1- Each monster has to have at least 2 unique attacks
       1.2- Each monster has to have different values to each stat attribute
    2- Player 1 chooses their 3 monsters
    3- Player 2 chooses their 3 monsters
     */
package controller;

import model.AttackTypes;
import model.Monster;
import model.MonsterType;

import java.util.ArrayList;

public class MonsterChoice {

    Monster grass_starter = new Monster("Bulbdinossaur", MonsterType.Grass, 100, 10, 1, 11, new AttackTypes[]{AttackTypes.ABSORB, AttackTypes.TACKLE});
    Monster fire_starter = new Monster("charredsalamander", MonsterType.Fire, 100, 12, 1, 9, new AttackTypes[]{AttackTypes.EMBER, AttackTypes.SCRATCH});
    Monster water_starter = new Monster("Tinycrocodile", MonsterType.Water, 100, 11, 1, 10, new AttackTypes[]{AttackTypes.WATER_GUN, AttackTypes.BITE});
    Monster first_encounter = new Monster("Midgetpidgeon", MonsterType.Normal, 60, 8, 1, 12, new AttackTypes[]{AttackTypes.PECK, AttackTypes.HYPER_BEAM});



}