/* ConnectFourDriver.java  
 *Version: 
 *     1 
 * 
 */
/** 
 *This program is the driver class for Connect Four Game.
 * 
 *@author      Professor Passino 
 */
public class ConnectFourDriver
{
    public static void main(String[] args)
    {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new HumanPlayer();
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGame();
        game.getStats();
    }
}
