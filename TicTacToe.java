import java.util.*;

public class TicTacToe{
    public static final int SIZE= 3;
    public static final char PLAYER_X= 'x';
    public static final char PLAYER_0= 'O';

    private char board[][];
    
    private char currentPlayer;
    private Scanner scanner;

    //default constructer, initialising the board and also with numbers
    public TicTacToe(){
        board= new char[SIZE][SIZE];   //default constructer initialising the board 
        
        //now we will initialise the board with digits 0-8
        int boxNumber=0;
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                board[i][j]= (char)('0'+boxNumber);
                boxNumber++;

            }
        }
        currentPlayer=PLAYER_X; //always starting the game with player_x
        scanner= new Scanner(System.in);
    }

    

    //for printing the board
    public void print(){
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                System.out.printf("%s"," "+board[i][j]);
                if(j+1<SIZE){
                    System.out.print(" | ");

                }
            }
            System.out.println();
            if(i+1<SIZE){
                System.out.println("-------------");
            }
        }
    }




    //check if the player has won
    public boolean checkWinner(char player){
        //checking for the horizontal
        for(int i=0;i<SIZE;i++){
           if(board[i][0]== player && board[i][1]==player && board[i][2]==player){
            return true;
           } 
        }
        //checking for the vertical
        for(int j=0;j<SIZE;j++){
            if(board[0][j]== player && board[1][j]==player && board[2][j]==player){
             return true;
            }
        }
        //checking for the diagonals
       if(board[1][1]== player && board[2][2]==player && board[3][3]==player){
             return true;
        }
        //checking for the right diagonals
        if(board[2][2]== player && board[1][1]==player && board[0][0]==player){
            return true;
       }
       return false;

    }



    //method to check if the board is full
    public boolean isBoardFull(){
        //board is full if there is no digit left in the board
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if(Character.isDigit(board[i][j])){
                    return false;
                }
            }
        }
        return true;
    }




    //check if the game is over
    public boolean isGameOver(){
        return checkWinner(PLAYER_0) || checkWinner(PLAYER_X) || isBoardFull();
    }


    //method to make the move asking user to enter box number
    public int makeMove(){
        System.out.println("Player  : "+ currentPlayer + " choose a box number ");
        int boxNumber= scanner.nextInt();
        if(boxNumber<0|| boxNumber>8){
            System.out.println("Invalid Input");
            return makeMove();
        } 
        //check if the input box number is empty
        int i= boxNumber/SIZE;
        int j=boxNumber%SIZE;
        if(board[i][j]== PLAYER_0 || board[i][j]==PLAYER_X){
            System.out.println("Input box already occupied, try again");
            return makeMove();

        }
        return boxNumber;
    }






    //method to play the game 
    public void play(){
        // we play as long as game is not over 
        while(!isGameOver()){
            //print the current board
            print();
            //change the line after printing the board
            System.out.println();

            //ask the cuurent player to make a move
            int boxNumber= makeMove();
            //place the marker in that position
            int i= boxNumber/SIZE;
            int j=boxNumber%SIZE;
            board[i][j] =currentPlayer;
            currentPlayer= currentPlayer== PLAYER_X? PLAYER_0 : PLAYER_X;   
        }
        //finally print the winner
        if(checkWinner(PLAYER_0)){
            System.out.println("Player 0 won!!  ");

        }
        else if (checkWinner(PLAYER_X)){
            System.out.println("Player X won !!  ");
            print();
        }
        else {
            System.out.println("Game Drawn");
        }    

        }
    




    public static void main(String[] args) {
        TicTacToe ob=new TicTacToe();
        ob.print();
        ob.play();
        
    }
}