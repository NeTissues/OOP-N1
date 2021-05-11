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
import model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MonsterChoice {

    public static ArrayList<Monster> initializeMonsterAList(){
        Monster grass_starter = new Monster("Bulbdinossaur", MonsterType.Grass, 100, 10, 1, 11, new AttackTypes[]{AttackTypes.ABSORB, AttackTypes.TACKLE});
        Monster fire_starter = new Monster("charredsalamander", MonsterType.Fire, 100, 12, 1, 9, new AttackTypes[]{AttackTypes.EMBER, AttackTypes.SCRATCH});
        Monster water_starter = new Monster("Tinycrocodile", MonsterType.Water, 100, 11, 1, 10, new AttackTypes[]{AttackTypes.WATER_GUN, AttackTypes.BITE});
        Monster first_encounter = new Monster("Midgetpidgeon", MonsterType.Normal, 60, 8, 1, 12, new AttackTypes[]{AttackTypes.PECK, AttackTypes.HYPER_BEAM});

        ArrayList<Monster> allMonsters = new ArrayList<>();

        allMonsters.add(grass_starter);
        allMonsters.add(fire_starter);
        allMonsters.add(water_starter);
        allMonsters.add(first_encounter);

        return allMonsters;
    }

    public static void listMonsters(ArrayList<Monster> monsterArrayList){
        for (int i = 0; i < monsterArrayList.size(); i++){
            System.out.println(i + ": " + monsterArrayList.get(i).getName());
        }
    }

    public static void choosingMonsters(Player player){
        Scanner scanner= new Scanner(System.in);
        int playerChoice, aux=3;
        ArrayList<Monster> allMonsters = new ArrayList<>(initializeMonsterAList());
        ArrayList<Monster> newTeam = new ArrayList<>();

        System.out.println("Hi " + player.getName() + ", please choose three(3) of the following pocket monsters for your team by typing their respective numbers");
        listMonsters(initializeMonsterAList());
        try {
            do {
                playerChoice = scanner.nextInt();

                if (playerChoice<=allMonsters.size()) {
                    newTeam.add(allMonsters.get(playerChoice));
                    aux--;
                }else
                    System.out.println("Invalid pocket monster! try again");

                System.out.println(aux + " picks remaining");
            } while (newTeam.size() < 3);
        }catch (Exception e){e.printStackTrace();}
        player.setTeam(newTeam);

        System.out.println("\nI see! so this is the team you choose: " );
        listMonsters(player.getTeam());
        System.out.println("\nGreat taste");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Monster> oof = new ArrayList<>();
        Player player1 = new Player("Ash", oof);
        Player player2 = new Player("Player2", oof);

        choosingMonsters(player1);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// clears screen, will not clear it inside an IDE
        choosingMonsters(player2);
        //listMonsters(initializeMonsterAList());
    }

}