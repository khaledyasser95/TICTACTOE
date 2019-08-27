
package finished.tictactoe;


import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Khaled
 */
public class FinishedTICTACTOE {

    static int moveCount = 0;
    static int board[][];
    static int row, col, cons;
    static boolean win = false;
    static int player;
    static Scanner scan = new Scanner(System.in);
    static int flag;

    public static void main(String[] args) {
        // write your code here
        row = 6;
        col = 7;
        cons = 3;
        player = 0;
        flag = 1;
        board = new int[row][col];

        for (int i = 0; i < row; i++) {
            Arrays.fill(board[i], 3);
        }

        while (!win && !isFull()) {
            int i, j;
            if (flag == 1) {
                if (player == 1) {
                    player = 0;
                } else if (player == 0) {
                    player = 1;
                }
            }
            System.out.println("Player " + player + " turn");
            i = scan.nextInt();
            j = scan.nextInt();
            System.out.print("Row:" + i + " Coloumn " + j + "\n");

            makeMove(i, j, player);
            printBoard();
            output(i, j, player);

        }

    }

    public static void output(int i, int j, int player) {

        if (check(i, j, player, 0) == true) {
            win = true;
        } else if (check(i, j, player, 1) == true) {
            win = true;
        } else if (checkRightDiagonal(i, j, player) == true) {
            win = true;
        } else if (checkLeftDiagonal(i, j, player) == true) {
            win = true;
        }

    }

    /**
     * It prints X OR O or Dashes if Index= 1 print X if Index= 0 print O Else
     * Dashes
     */
    public static void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    System.out.print("X ");
                } else if (board[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

    }

    /**
     * Move counter = row* col
     *
     * @return true
     */
    public static boolean isFull() {
        return moveCount == row * col;
    }

    /**
     * Takes index and player number and check overwrite
     *
     * @param i
     * @param j
     * @param player
     */
    public static void makeMove(int i, int j, int player) {

        if (board[i][j] != 3) {
            System.out.println("Choose another place ");
            flag = 0;
        } else {
            board[i][j] = player;
            flag = 1;
            moveCount++;
        }
    }

    /**
     * direction 0 for horizontal 1 for vertical Takes index and check
     * horizontal and vertical for consecutive number
     *
     * @param i
     * @param j
     * @param player
     * @param direction
     * @return
     */
    private static boolean check(int i, int j, int player, int direction) {
        int end = direction == 0 ? col : row;
        int winningSum = cons * (player + 1);
        int sum;
        for (int r = 0; r <= end - cons; r++) {
            sum = 0;
            for (int l = r; l < r + cons; l++) {
                if (direction == 0) {
                    sum += board[i][l] + 1;
                } else {
                    sum += board[l][j] + 1;
                }
            }
            if (sum == winningSum) {
                System.out.println("Player " + player + " won by " + (direction == 0 ? "horizontal" : "vertical"));
                return true;
            }
        }

        return false;

    }

    private static boolean checkRightDiagonal(int i, int j, int player) {
        int sdi = i >= (cons - 1) ? (cons - 1) : i;
        int sdj = j >= (cons - 1) ? (cons - 1) : j;
        int edi = i + (cons - 1) < row ? (cons - 1) : (row - 1) - i;
        int edj = j + (cons - 1) < col ? (cons - 1) : (col - 1) - j;
        int startI = i - Math.min(sdi, sdj);
        int startJ = j - Math.min(sdi, sdj);
        int endI = i + Math.min(edi, edj);
        int endJ = j + Math.min(edi, edj);
        int sum;
        int winningSum = cons * (player + 1);
//        System.out.println("right start: "+startI+","+startJ);
//        System.out.println("right end: "+endI+","+endJ);
//        System.out.println("winning sum is = "+winningSum);

        for (int r = startI, c = startJ; r <= endI - (cons - 1) && c <= endJ - (cons - 1); r++, c++) {
            //System.out.println("in");
            sum = 0;
            if (board[r][c] != player) {
                continue;
            }
            int x = c, y = r;
            for (int n = 0; n < cons; n++) {
                sum += board[y++][x++] + 1;
                //  System.out.println("sum = "+sum);
            }
            if (sum == winningSum) {
                System.out.println("Player " + player + " won by right diagonal and startJ was = " + startJ);
                return true;
            }
        }

        return false;
    }

    private static boolean checkLeftDiagonal(int i, int j, int player) {
        int sdi = i >= (cons - 1) ? (cons - 1) : i;
        int sdj = j + (cons - 1) < col ? (cons - 1) : (col - 1) - j;
        int edi = i + (cons - 1) < row ? (cons - 1) : (row - 1) - i;
        int edj = j >= (cons - 1) ? (cons - 1) : j;
        int startI = i - Math.min(sdi, sdj);
        int startJ = j + Math.min(sdi, sdj);
        int endI = i + Math.min(edi, edj);
        int endJ = j - Math.min(edi, edj);
        int winningSum = cons * (player + 1);
        int sum;
//        System.out.println("left start: "+startI+","+startJ);
//        System.out.println("left end: "+endI+","+endJ);
//        System.out.println("winning sum is = "+winningSum);
        for (int r = startI, c = startJ; r <= endI - (cons - 1) && c >= endJ - (cons - 1); r++, c--) {
            sum = 0;
            int x = c, y = r;
            for (int l = 0; l < cons; l++) {
                sum += board[y++][x--] + 1;
                //System.out.println("sum = "+sum);
            }
            if (sum == winningSum) {
                System.out.println("Player " + player + " won by left diagonal startI was = " + startI);
                return true;
            }
        }

        return false;
    }

}
