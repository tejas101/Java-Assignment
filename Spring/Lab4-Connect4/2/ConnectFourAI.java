/* ConnectFourAI.java  
 *Version: 
 *     1 
 * 
 */
/** 
 *This program is the driver class for Connect Four Game with computer player.
 * 
 *@author      Professor Passino 
 */
public class ConnectFourAI
{
    public static void main(String[] args)
    {
        ConnectFourPlayerInterface player1 = new HumanPlayer("Bob");
        ConnectFourPlayerInterface player2 = new ComputerPlayer();
        ConnectFourGameInterface game = new ConnectFourGame(player1, player2);
        game.playGame();
        game.getStats();
    }
}
