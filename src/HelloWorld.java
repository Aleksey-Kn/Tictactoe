import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class HelloWorld {
    private static boolean printSost(char[][] mat){
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
        /*if(x - 1 > o || o - 1 > x || xwin && owin){
            System.out.println("Impossible");
        }*/
        if(!(xwin || owin)){
            if(x + o == 9){
                System.out.println("Draw");
                return false;
            }
            else{
                //System.out.println("Game not finished");
                return true;
            }
        }
        else{
            System.out.println(xwin ? "X wins": "O wins");
            return false;
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

    private static void userStap(char[][] mat, char now)
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
        mat[3 - y][x - 1] = now;
    }

    private static void easyLevel(char[][] mat, char now){
        Random rand = new Random();
        System.out.println("Making move level \"easy\"");
        int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }while (mat[x][y] != ' ');
        mat[x][y] = now;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String type;
        char[][] mat = new char[3][3];

        while(true) {
            for(int i = 0; i < 3; i++){
                Arrays.fill(mat[i], ' ');
            }
            System.out.print("Input command: ");
            type = scan.nextLine();
            switch (type) {
                case "start user easy":
                    do {
                        printMatrix(mat);
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                    } while (printSost(mat));
                    break;
                case "start easy user":
                    do {
                        printMatrix(mat);
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                    } while (printSost(mat));
                    break;
                case "start user user":
                    do {
                        printMatrix(mat);
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                    } while (printSost(mat));
                    break;
                case "start easy easy":
                    do {
                        printMatrix(mat);
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                    } while (printSost(mat));
                    break;
                case "exit":
                    return;
                    default:
                        System.out.println("Bed parameters");
            }
        }
    }
}
