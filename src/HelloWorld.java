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

    private static void mediumLevel(char[][] mat, char now){
        Random rand = new Random();
        System.out.println("Making move level \"medium\"");
        for(int i = 0, schn, schNon, zero = 0; i < 3; i++){// проверка горизонтали
            schn = 0;
            schNon = 0;
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == now){
                    schn++;
                }
                else if(mat[i][j] == (now == 'X'?'O':'X')){
                    schNon++;
                }
                else{
                    zero = j;
                }
            }
            if((schn == 2 || schNon == 2) &&  mat[i][zero] == ' '){
                mat[i][zero] = now;
                return;
            }
        }
        for(int i = 0, schn, schNon, zero = 0; i < 3; i++){//проверка вертикали
            schn = 0;
            schNon = 0;
            for(int j = 0; j < 3; j++){
                if(mat[j][i] == now){
                    schn++;
                }
                else if(mat[j][i] == (now == 'X'?'O':'X')){
                    schNon++;
                }
                else{
                    zero = j;
                }
            }
            if((schn == 2 || schNon == 2) &&  mat[zero][i] == ' '){
                mat[zero][i] = now;
                return;
            }
        }

        int schn = 0, schNon = 0, zero = 0;
        for(int i = 0; i < 3; i++) {//проверка главной диагонали
            if (mat[i][i] == now) {
                schn++;
            } else if (mat[i][i] == (now == 'X' ? 'O' : 'X')) {
                schNon++;
            } else {
                zero = i;
            }
        }
        if((schn == 2 || schNon == 2) &&  mat[zero][zero] == ' '){
            mat[zero][zero] = now;
            return;
        }

        schn = 0;
        schNon = 0;
        zero = 0;
        for(int i = 0; i < 3; i++) {//проверка побочной диагонали
            if (mat[i][2 - i] == now) {
                schn++;
            } else if (mat[i][2 - i] == (now == 'X' ? 'O' : 'X')) {
                schNon++;
            } else {
                zero = i;
            }
        }
        if((schn == 2 || schNon == 2) &&  mat[zero][2 - zero] == ' '){
            mat[zero][2 - zero] = now;
        }
        else{//если нет выигрышных позиций
            int x, y;
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            }while (mat[x][y] != ' ');
            mat[x][y] = now;
        }
    }

    private static int levelHard(char[][] pmat, char now, int koef ,boolean orig){
        char[][] mat;
        if(orig){
            mat = pmat;
        }
        else {
            mat = new char[3][3];
            for(int i = 0; i < 3; i++){
                mat[i] = pmat[i].clone();
            }
        }
        if(orig) {
            System.out.println("Making move level \"hard\"");
        }
        for(int i = 0, schn, schNon, zero = 0; i < 3; i++){// проверка горизонтали
            schn = 0;
            schNon = 0;
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == now){
                    schn++;
                }
                else if(mat[i][j] == (now == 'X'?'O':'X')){
                    schNon++;
                }
                else{
                    zero = j;
                }
            }
            if((schn == 2 || schNon == 2) &&  mat[i][zero] == ' '){
                mat[i][zero] = now;
                if (schn == 2) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        for(int i = 0, schn, schNon, zero = 0; i < 3; i++){//проверка вертикали
            schn = 0;
            schNon = 0;
            for(int j = 0; j < 3; j++){
                if(mat[j][i] == now){
                    schn++;
                }
                else if(mat[j][i] == (now == 'X'?'O':'X')){
                    schNon++;
                }
                else{
                    zero = j;
                }
            }
            if((schn == 2 || schNon == 2) &&  mat[zero][i] == ' '){
                mat[zero][i] = now;
                if (schn == 2) {
                    return 1;
                }
                else {
                    return 0;
                }            }
        }

        int schn = 0, schNon = 0, zero = 0;
        for(int i = 0; i < 3; i++) {//проверка главной диагонали
            if (mat[i][i] == now) {
                schn++;
            } else if (mat[i][i] == (now == 'X' ? 'O' : 'X')) {
                schNon++;
            } else {
                zero = i;
            }
        }
        if((schn == 2 || schNon == 2) &&  mat[zero][zero] == ' '){
            mat[zero][zero] = now;
            if (schn == 2) {
                return 1;
            }
            else {
                return 0;
            }
        }

        schn = 0;
        schNon = 0;
        zero = 0;
        for(int i = 0; i < 3; i++) {//проверка побочной диагонали
            if (mat[i][2 - i] == now) {
                schn++;
            } else if (mat[i][2 - i] == (now == 'X' ? 'O' : 'X')) {
                schNon++;
            } else {
                zero = i;
            }
        }
        if((schn == 2 || schNon == 2) &&  mat[zero][2 - zero] == ' '){
            mat[zero][2 - zero] = now;
            if (schn == 2) {
                return 1;
            }
            else {
                return 0;
            }
        }

        for (int k = 1; k >= 0; k--){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(mat[i][j] == ' '){
                        mat[i][j] = now;
                        if(levelHard(mat, now == 'X'? 'O': 'X', -koef, false) == k * koef){
                            return koef * k;
                        }
                        mat[i][j] = ' ';
                    }
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < 3 && flag; i++){
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == ' '){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            return 0;
        }
        //если есть только проигрышные позици
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
        }while (mat[x][y] != ' ');
        mat[x][y] = now;
        return -koef;
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
                    printMatrix(mat);
                    do {
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start easy user":
                    printMatrix(mat);
                    do {
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start user user":
                    printMatrix(mat);
                    do {
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start easy easy":
                    printMatrix(mat);
                    do {
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start user medium":
                    printMatrix(mat);
                    do {
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        mediumLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start medium user":
                    printMatrix(mat);
                    do {
                        mediumLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start medium medium":
                    printMatrix(mat);
                    do {
                        mediumLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        mediumLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start medium easy":
                    printMatrix(mat);
                    do {
                        mediumLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start easy medium":
                    printMatrix(mat);
                    do {
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        mediumLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start user hard":
                    printMatrix(mat);
                    do {
                        userStap(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        levelHard(mat, 'O', 1, true);
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start hard user":
                    printMatrix(mat);
                    do {
                        levelHard(mat, 'X', 1, true);
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        userStap(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start hard hard":
                    printMatrix(mat);
                    do {
                        levelHard(mat, 'X', 1, true);
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        levelHard(mat, 'O', 1, true);
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start hard easy":
                    printMatrix(mat);
                    do {
                        levelHard(mat, 'X', 1, true);
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        easyLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start hard medium":
                    printMatrix(mat);
                    do {
                        levelHard(mat, 'X', 1, true);
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        mediumLevel(mat, 'O');
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start easy hard":
                    printMatrix(mat);
                    do {
                        easyLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        levelHard(mat, 'O', 1, true);
                        printMatrix(mat);
                    } while (printSost(mat));
                    break;
                case "start medium hard":
                    printMatrix(mat);
                    do {
                        mediumLevel(mat, 'X');
                        printMatrix(mat);
                        if(!printSost(mat)){
                            break;
                        }
                        levelHard(mat, 'O', 1, true);
                        printMatrix(mat);
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
