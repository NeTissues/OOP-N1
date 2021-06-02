package controller;

import model.Monster;
import model.MonsterEnum;
import view.ConsoleInterface;

import java.util.ArrayList;

public class MonsterFactory {

    //TODO: Placeholder! This might not be optimal, better to just instantiate the Monsters chosen using create()
    static ArrayList<Monster> initializeMonsterArrayList(){
        ArrayList<Monster> allMonsters = new ArrayList<>();

        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M001));
        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M004));
        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M007));
        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M016));
        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M102));
        allMonsters.add(MonsterEnum.turnIntoMonster(MonsterEnum.M150));

        return allMonsters;
    }

    public Monster create(final int monsterId){
        switch (monsterId){
            case 1:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M001);
            case 4:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M004);
            case 7:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M007);
            case 16:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M016);
            case 102:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M102);
            case 150:
                return MonsterEnum.turnIntoMonster(MonsterEnum.M150);
            default:
                throw new IllegalArgumentException("Wrong monsterId: " + monsterId);
        }
    }
}
