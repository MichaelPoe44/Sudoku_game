import java.util.Random;

public class Maker {
    private static final int GRID_SIZE = 9;
    static boolean solvable = false;
    int[][] board = {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0}
    };
    
    public Maker() {
        

        Random random = new Random();

        

        
        
        makeSolvedBoard(board,random);
        printBoard(board);
        
        
    }
    
    public static void makeSolvedBoard(int[][] blank, Random random){
        while (!solvable){
            //to create more randomness
            blank[0][7] = random.nextInt(1,10);
            for (int i=0,j=0; i < 9; i++,j++){
                while (blank[i][j] == 0){
                    int number = random.nextInt(1,10);
                    if (isValidSpot(blank,number,i,j)){
                        blank[i][j] = number;
                    }
                }
            }
            

            if (solveBoard(blank)){
                solvable = true;
            }
            else {
                solvable = false;
                makeBlank(blank);
            }   
        }
    }


    private static void printBoard(int[][] board) {

        for (int row=0; row < 9; row++){
            for (int column=0; column < 9; column++){
                System.out.print(board[row][column]);
            }
            System.out.println();
        }

    }


    

    public int[][] getBoard(){
        return board;
    }






    private static void makeBlank(int[][] blank){
        for (int row=0; row < GRID_SIZE; row++){
            for (int column=0; column < GRID_SIZE; column++){
                blank[row][column] = 0;
            }
        }
    }

    private static boolean isNumberInRow(int[][] board,int number,int row){
        for (int i=0; i < GRID_SIZE ;i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;

    }

    private static boolean isNumberInColumn(int[][] board,int number,int column){
        for (int i=0; i < GRID_SIZE; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board,int number,int column,int row){
        int localBoxRow = row - (row % 3);
        int localBoxColumn = column - (column % 3);

        for (int i = localBoxRow; i < (localBoxRow + 3); i++){
            for (int j = localBoxColumn; j < (localBoxColumn + 3); j++){
                if (board[i][j] == number){
                    return true;
                }
            }

        }
        return false;
    }

    private static boolean isValidSpot(int[][] board, int number, int row, int column){
        return !isNumberInRow(board,number,row) && 
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, column, row);
    }

    private static boolean solveBoard(int[][] board){
        for (int row=0; row < GRID_SIZE; row++){
            for (int column=0; column < GRID_SIZE; column++){
                if (board[row][column] == 0){
                    for (int testNumber = 1; testNumber <= GRID_SIZE; testNumber++){
                        if (isValidSpot(board, testNumber, row, column)){
                            board[row][column] = testNumber;
                            if (solveBoard(board)){
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }











}
