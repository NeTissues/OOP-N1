package controller;

import model.AttackTypes;
import model.Monster;
import model.Player;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Battle extends MonsterChoice{

    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// clears screen, will not clear it inside an IDE
    }

    public static boolean hasAliveMonster(ArrayList<Monster> team){
        boolean isAlive = false;
        for (Monster monster : team) {
            if (monster.getHp() > 0) {
                isAlive = true;
                break;
            }
        }
        return isAlive;
    }

    public static Monster changeMonster(ArrayList<Monster> team, int monsterIndex){
        Monster currentMonster, nextMonster;

        currentMonster = team.get(0);
        nextMonster = team.get(monsterIndex);

        team.remove(0);
        team.add(0, nextMonster);
        for (int i = 1; i < team.size(); i++){
            if (team.get(i).equals(team.get(0)))
                team.remove(team.get(i));
        }
        team.add(monsterIndex, currentMonster);

        if (hasAliveMonster(team) && team.get(0).getHp() > 0)
            return team.get(0);
        return currentMonster;
    }

    public static void attack(Player currentPlayer, Player targetPlayer){
        int moveChoice;
        Monster attackingMonster = currentPlayer.getTeam().get(0); Monster targetMonster = targetPlayer.getTeam().get(0);

        Scanner scanner = new Scanner(System.in);
        AttackTypes[] moves = attackingMonster.getMoves();

        int enemyHP = targetMonster.getHp();

        for (int i = 0; i < attackingMonster.getMoves().length; i++){
            System.out.println(i + ": " + attackingMonster.getMoves()[i]);
        }

        System.out.println("Select the move you wish to use");
        moveChoice = scanner.nextInt();
        switch (moveChoice){
            case 0:
                targetMonster.setHp(enemyHP -= moves[0].returnMoveDamage(moves[0].getAttackPower(), attackingMonster, targetMonster));
                if (!(targetPlayer.getTeam().isEmpty())){
                    if (targetMonster.getHp() <= 0)
                        targetPlayer.getTeam().remove(0);
                    else
                        System.out.println(targetPlayer.getName() + " is out of pocket monsters to battle!");
                    break;
                }
                break;
            case 1:
                targetMonster.setHp(enemyHP -= moves[1].returnMoveDamage(moves[1].getAttackPower(), attackingMonster, targetMonster));
                if (targetMonster.getHp() <= 0) {
                    targetPlayer.getTeam().remove(0);
                    if (targetPlayer.getTeam().isEmpty())
                        System.out.println(targetPlayer.getName() + " is out of pocket monsters to battle!");
                    break;
                }
            default:
                break;
        }
    }

    public static void battlePhase(Player currentPlayer, Player targetPlayer){
        int action, monsterIndex;
        Scanner scanner = new Scanner(System.in);

        listMonsters(currentPlayer.getTeam());
        System.out.println("Current pocket monster: " + currentPlayer.getTeam().get(0).getName() + " HP: " + currentPlayer.getTeam().get(0).getHp());
        System.out.println("Choose an action:\n\t1 - Attack\n\t2 - Change pocket monster");
        action = scanner.nextInt();
        switch (action){
            case 1:
                //Attack
                attack(currentPlayer, targetPlayer);
                break;
            case 2:
                //Change
                System.out.println("Select the pocket monster you want to switch to: ");
                monsterIndex = scanner.nextInt();
                changeMonster(currentPlayer.getTeam(), monsterIndex);
                break;
            default:
                System.out.println("invalid option");
        }
    }

    public static void startBattle(Player player1, Player player2){
        int starterMonster;
        Scanner scanner = new Scanner(System.in);

        try{
            do{
                battlePhase(player1, player2);
                clearScreen();
                battlePhase(player2, player1);
            }while (!player1.getTeam().isEmpty() || !player2.getTeam().isEmpty());
        }catch (Exception e){e.printStackTrace();}
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Monster> oof = new ArrayList<>();
        Player player1 = new Player("Player1", oof);
        Player player2 = new Player("Player2", oof);

        choosingMonsters(player1);
        clearScreen();
        choosingMonsters(player2);

        startBattle(player1, player2);
    }
}
