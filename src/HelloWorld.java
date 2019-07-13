import java.util.Scanner;
import java.util.Random;

public class HelloWorld {
    private static void printSost(char[][] mat){
        boolean owin = false;
        boolean xwin = false;
        for(int i = 0; i < 3; i++) {
            if (mat[i][0] == 'X' && mat[i][1] == 'X' && mat[i][2] == 'X' || mat[0][i] == 'X' && mat[1][i] == 'X' && mat[2][i] == 'X') {
                xwin = true;
            } else if (mat[i][0] == 'O' && mat[i][1] == 'O' && mat[i][2] == 'O' || mat[0][i] == 'O' && mat[1][i] == 'O' && mat[2][i] == 'O') {
                owin = true;
            }
        }
        if(mat[0][0] == 'X' && mat[1][1] == 'X' && mat[2][2] == 'X' || mat[0][2] == 'X' && mat[1][1] == 'X' && mat[2][0] == 'X'){
            xwin = true;
        }
        else if(mat[0][0] == 'O' && mat[1][1] == 'O' && mat[2][2] == 'O' || mat[0][2] == 'O' && mat[1][1] == 'O' && mat[2][0] == 'O') {
            owin = true;
        }

        int x = 0, o = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == 'X'){
                    x++;
                }
                else if(mat[i][j] == 'O'){
                    o++;
                }
            }
        }
        if(x - 1 > o || o - 1 > x || xwin && owin){
            System.out.println("Impossible");
        }
        else if(!(xwin || owin)){
            if(x + o == 9){
                System.out.println("Draw");
            }
            else{
                System.out.println("Game not finished");
            }
        }
        else{
            System.out.println(xwin ? "X wins": "O wins");
        }
    }

    private static void printMatrix(char[][] mat){
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void userStap(char[][] mat)
    {
        int x, y;
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            x = scan.nextInt();
            y = scan.nextInt();
            if(x > 0 && x < 4 && y > 0 && y < 4) {
                if(mat[3 - y][x - 1] == ' ') {
                    break;
                }
                else{
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
            else{
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
        mat[3 - y][x - 1] = 'X';
    }

    private static void easyLevel(char[][] mat){
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }while (mat[x][y] != ' ');
        mat[x][y] = 'X';
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String str = scan.nextLine();
        str = str.substring(1, 10);
        System.out.println("---------");
        System.out.println("| " + str.charAt(0) + " " + str.charAt(1) + " " + str.charAt(2) + " |");
        System.out.println("| " + str.charAt(3) + " " + str.charAt(4) + " " + str.charAt(5) + " |");
        System.out.println("| " + str.charAt(6) + " " + str.charAt(7) + " " + str.charAt(8) + " |");
        System.out.println("---------");
        char[][] mat = new char[3][3];

        for(int i = 0, w = 0; i < 3; i++){
            for(int j = 0; j < 3; j++, w++) {
                mat[i][j] = str.charAt(w);
            }
        }
        System.out.println("Making move level \"easy\"");
        easyLevel(mat);
        printMatrix(mat);
    }
}
