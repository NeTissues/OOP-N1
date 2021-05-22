package controller;

import model.AttackTypes;
import model.Monster;
import model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Battle extends MonsterChoice{

    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// clears screen, will not clear it inside an IDE
    }

    public static boolean hasAliveMonster(ArrayList<Monster> team){
        boolean isAlive = false;

        if (team.isEmpty())
            return false;
        else
            for (Monster monster : team) {
                if (monster.getHp() > 0) {
                    isAlive = true;
                    break;
                }
            }
        return isAlive;
    }

    //TODO: Make this show effectiveness of moves in relation to the damage dealt
    public static void damageCalculation(Player currentPlayer, Player targetPlayer, int attackOption) {

        Monster attackingMonster = currentPlayer.getTeam().get(0);
        Monster targetMonster = targetPlayer.getTeam().get(0);
        AttackTypes[] moves = attackingMonster.getMoves();

        int enemyHP = targetMonster.getHp();
        double damage = moves[attackOption].returnMoveDamage(moves[attackOption].getAttackPower(), attackingMonster, targetMonster);

        System.out.println(attackingMonster.getName() + " dealt " + damage + " to " + targetPlayer.getName() + "'s " + targetMonster.getName());
        targetMonster.setHp(enemyHP -= damage);

        if (targetMonster.getHp() <= 0 && !(targetPlayer.getTeam().isEmpty()))
            targetPlayer.getTeam().remove(0);
        if (!hasAliveMonster(targetPlayer.getTeam())) {
            System.out.println(targetPlayer.getName() + " is out of pocket monsters to battle!");
        }
    }

    public static void attack(Player currentPlayer, Player targetPlayer){
        int moveChoice;
        try {

            Monster attackingMonster = currentPlayer.getTeam().get(0);
            Monster targetMonster = targetPlayer.getTeam().get(0);

            Scanner scanner = new Scanner(System.in);
            AttackTypes[] moves = attackingMonster.getMoves();

            int enemyHP = targetMonster.getHp();

            for (int i = 0; i < attackingMonster.getMoves().length; i++) {
                System.out.println(i + ": " + attackingMonster.getMoves()[i]);
            }

            System.out.println("Select the move you wish to use");
            moveChoice = scanner.nextInt();
            switch (moveChoice) {
                case 0:
                    damageCalculation(currentPlayer, targetPlayer, 0);
                    break;
                case 1:
                    damageCalculation(currentPlayer, targetPlayer, 1);
                    break;
                default:
                    break;
            }
        }catch (Exception e){e.printStackTrace();}
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
                currentPlayer.toggleSwitchingOff();
                break;
            case 2:
                //Change
                currentPlayer.isSwitching();
                break;
            default:
                System.out.println("invalid option");
        }
    }
    //TODO: Fix IndexOutOfBoundsException when trying player.get(0) while player has no pokemon left
    public static void startBattle(Player player1, Player player2){
        Scanner scanner = new Scanner(System.in);

        try{
            do{
                battlePhase(player1, player2);
                battlePhase(player2, player1);

                if(player1.isSwappingMonster() && !player2.isSwappingMonster()){//1 is switching, 2 isn't;
                    changeMonster(player1.getTeam());
                    clearScreen();
                    attack(player2, player1);
                }else if (player2.isSwappingMonster() && !player1.isSwappingMonster()){//1 isn't switching, 2 is;
                    changeMonster(player2.getTeam());
                    clearScreen();
                    attack(player1, player2);
                }else if (player1.isSwappingMonster() && player2.isSwappingMonster()){//both are switching
                    changeMonster(player1.getTeam());
                    clearScreen();
                    changeMonster(player2.getTeam());
                }else{//neither are switching
                    if (player1.comparePriority(player2) > player2.comparePriority(player1)) {
                        attack(player1, player2);
                        clearScreen();
                        attack(player2, player1);
                    }else{
                        attack(player2, player1);
                        clearScreen();
                        attack(player1, player2);
                    }
                }


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
