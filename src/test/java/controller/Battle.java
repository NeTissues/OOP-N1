package controller;

import model.AttackTypes;
import model.Monster;
import model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author NeTissues
 */
public class Battle extends MonsterChoice{

    /**
     * Invokes the cmd instance the program is running in and
     * tells it to execute the "/c cls" command, using <code>ProcessBuilder</code> to directly
     * connect the its output channel to the Java process’ output channel,
     * which works starting with Java 7, using <code>inheritIO()</code>.
     *
     * @throws IOException If an input or output exception occurred.
     * @throws InterruptedException if the process thread is interrupted while sleeping, waiting or otherwise occupied.
     */
    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();// clears screen, will not clear it inside an IDE
    }

    /**
     * Checks if the given <code>ArrayList</code> of <a href="#{@link}">{@link Monster}</a> is empty, if not, iterates
     * over the <code>ArrayList</code> checking if there's at least one monster alive in the given ArrayList.
     *
     * @param team ArrayList of Monster , forming the player's team
     * @return false if empty <code>ArrayList</code> or no monsters with hp greater than 0,
     * true if there's at least one monster alive on the ArrayList
     */
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

    //TODO: Fix IndexOutOfBoundsException when trying player.get(0) while player has no pokemon left
    //TODO: Make this show effectiveness of moves in relation to the damage dealt
    /**
     * Gets the battling(active) <a href="#{@link}">{@link Monster}</a> of each of the Players, with the index of 0,
     * as well as the Array of moves from the attacking player's active Monster. Stores the damage
     * from the returnMoveDamage method from <a href="#{@link}">{@link AttackTypes}</a>
     * of the chosen attackOption and sets the remaining hp of the targetPlayer's Monster to be its former hp - the damage
     * and afterwards checks if the monster is dead && if the Player's team isEmpty, if true, removes the current active
     * Monster from the targetPlayer and then checks again if there are any other alive Monsters from the targetPlayer's team,
     * using the isAlive() method printing a message if they're out of monsters to battle.
     *
     * @param currentPlayer Player instance whose Monster is realizing the attack
     * @param targetPlayer Player instance whose Monster is suffering the effects of the attack
     * @param attackOption The corresponding Integer of the index of the attack from which the player chose from
     */
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

    /**
     * Gets the battling(active) <a href="#{@link}">{@link Monster}</a> of each of the Players, with the index of 0,
     * lists all the moves from the <code>currentPlayer</code>'s active Monster and asks which of them they wish to
     * use.
     *
     * @param currentPlayer Attacking Player performing the move
     * @param targetPlayer Defending Player whose Monster is suffering the move's effect
     */
    public static void attack(Player currentPlayer, Player targetPlayer){
        int moveChoice;
        try {
            Monster attackingMonster = currentPlayer.getTeam().get(0);
            Scanner scanner = new Scanner(System.in);

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
        }catch (IndexOutOfBoundsException e){e.printStackTrace();}
    }

    /**
     * Lists <code>currentPlayer</code>'s team, prints the active <a href="#{@link}">{@link Monster}</a>'s name and HP
     * and lists the battle options and sets the <a href="#{@link}">{@link Player}</a>'s <code>isSwitching</code>
     * based on the choice.
     *
     * @param currentPlayer Player choosing that phase's action
     */
    public static void battlePhase(Player currentPlayer){
        int action;
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

    /**
     * Runs the battle loop, checking all possible scenarios from the combination of player choices and
     * executes them in the right order, according to the priority rule.
     *
     * @param player1 First of the players involved in the battle
     * @param player2 Second player involved in the battle
     */
    public static void startBattle(Player player1, Player player2){
        try{
            do{
                battlePhase(player1);
                battlePhase(player2);

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
