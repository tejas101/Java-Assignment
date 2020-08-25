interface ConnectFourPlayerInterface
{
    int takeTurn();
    String getName();
    int getNumberOfWins();
    void addWin();
    char getGamePiece();
    void setGamePiece(char gamePiece);
    void setPlayerNumber(int num);
    int getPlayerNumber();
    String getRegex();
}
