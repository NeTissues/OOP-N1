/*
    1- Create monsters
       1.1- Each monster has to have at least 2 unique attacks
       1.2- Each monster has to have different values to each stat attribute
    2- Player 1 chooses their 3 monsters
    3- Player 2 chooses their 3 monsters
     */
package controller;

import model.Monster;
import model.Player;
import view.ConsoleInterface;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author NeTissues
 */
public interface BattleInterface {

     static void listMonsters(ArrayList<Monster> monsterArrayList){
        for (int i = 0; i < monsterArrayList.size(); i++){
           ConsoleInterface.printOnConsole(i + ": " + monsterArrayList.get(i).getName());
        }
    }

     static void changeMonster(ArrayList<Monster> team){
        int monsterIndex;
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface.printOnConsole("Select the pocket monster you want to switch to: ");
        listMonsters(team);
        monsterIndex = scanner.nextInt();
        Monster nextMonster = team.get(monsterIndex);

        team.add(0, nextMonster);
        team.remove(monsterIndex + 1);
    }

     //TODO: Make this allow changes in the team choosing.
     static void choosingMonsters(Player player){
        Scanner scanner= new Scanner(System.in);
        int playerChoice, aux=3;
        ArrayList<Monster> allMonsters = new ArrayList<>(MonsterFactory.initializeMonsterArrayList());
        ArrayList<Monster> newTeam = new ArrayList<>();

         ConsoleInterface.printOnConsole("Hi " + player.getName() +
                 ", please choose three(3) of the following pocket monsters for your team by typing their " +
                 "respective numbers");
        listMonsters(MonsterFactory.initializeMonsterArrayList());
        try {
            do {
                playerChoice = scanner.nextInt();

                if (playerChoice<=allMonsters.size()) {
                    newTeam.add(allMonsters.get(playerChoice));
                    aux--;
                }else
                    ConsoleInterface.printOnConsole("Invalid pocket monster! try again");

                ConsoleInterface.printOnConsole(aux + " picks remaining");
            } while (newTeam.size() < 3);
        }catch (Exception e){e.printStackTrace();}
        player.setTeam(newTeam);

         ConsoleInterface.printOnConsole("\nI see! so this is the team you choose: " );
        listMonsters(player.getTeam());
         ConsoleInterface.printOnConsole("\nGreat taste");
    }

}