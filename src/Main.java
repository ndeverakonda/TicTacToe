
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Main {
    //Result
    static char AI;
    static int Xwin=0;
    static int Owin=0;
    static boolean finished(char[][] arr,boolean silent){
        Xwin=0;
        Owin=0;

//Horizontal
        for(int i=0;i<3;i++){
            int markO=0;
            int markX=0;
            for(int j=0;j<3;j++){
                if(arr[i][j]=='X'){
                    markX++;
                }
                else if(arr[i][j]=='O'){
                    markO++;
                }

            }
            if(markX==3){
                Xwin=1;
            }
            if(markO==3){
                Owin=1;
            }
        }

//Vertical
        for(int i=0;i<3;i++){
            int markO=0;
            int markX=0;
            for(int j=0;j<3;j++){
                if(arr[j][i]=='X'){
                    markX++;
                }
                else if(arr[j][i]=='O'){
                    markO++;
                }

            }
            if(markX==3){
                Xwin=1;
            }
            if(markO==3){
                Owin=1;
            }
        }


//Diagonal L to R
        int markO=0;
        int markX=0;
        for(int i=0;i<3;i++){

            if(arr[i][i]=='X'){
                markX++;
            }
            else if(arr[i][i]=='O'){
                markO++;
            }


            if(markX==3){
                Xwin=1;
            }
            if(markO==3){
                Owin=1;
            }
        }

//Diagonal R to L
        markX = 0;
        markO = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i][2 - i] == 'X') markX++;
            if (arr[i][2 - i] == 'O') markO++;
        }

        if (markX == 3) {
            Xwin = 1;
        }
        if (markO == 3) {
            Owin = 1;
        }


// Draw Logic
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' ') {
                    hasEmpty = true;
                }
            }
        }


//PRINT RESULT


        if(Xwin==1&&Owin==0){

            if(!silent) System.out.println("X wins");

        }
        else if(Owin==1&&Xwin==0){

            if(!silent) System.out.println("O wins");
        }
        else if(!hasEmpty){

            if(!silent)  System.out.println("Draw");
        }
        else{
            return false;
        }
        return true;
    }



    static void printGrid(char[][] arr){
        System.out.println("---------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("|");

        }
        System.out.println("---------");
    }


    static void userMove(char[][] arr,char ch,Scanner sc){
        //Make a move
        int a;
        int b;

//Check input
        while (true) {
            System.out.println("Enter the coordinates: ");
            if (!sc.hasNextInt()) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            }
            a = sc.nextInt();

            if (!sc.hasNextInt()) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            }
            b = sc.nextInt();

            if (a < 1 || a > 3 || b < 1 || b > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (arr[a - 1][b - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;

        }


        //User Move
        arr[a-1][b-1]=ch;



    }

    static void compMove(char[][] arr,char ch){
        System.out.println("Making move level \"easy\"");
        //Computer Move
        Random rnd = new Random();

        int x = rnd.nextInt(3);
        int y = rnd.nextInt(3);

        while (arr[x][y] != ' ') {
            x = rnd.nextInt(3);
            y = rnd.nextInt(3);
        }

        arr[x][y] = ch;
    }

    static void medium(char[][] arr,char move){
        System.out.println("Making move level \"medium\"");
        //HORIZONTAL
        int blocki=-1;
        int blockj=-1;
        boolean winmove=false;
        for(int i=0;i<3;i++){
            int xcnt=0;
            int ocnt=0;
            int iemp=0;
            int jemp=0;


            for(int j=0;j<3;j++){
                if(arr[i][j]=='X'){
                    xcnt++;
                }
                else if(arr[i][j]=='O'){
                    ocnt++;
                }
                else{
                    iemp=i;
                    jemp=j;
                }
            }
            if(move=='X'&&xcnt==2&&ocnt==0){
                arr[iemp][jemp]='X';
                winmove=true;
                return;
            }
            else if(move=='O'&&xcnt==0&&ocnt==2){
                arr[iemp][jemp]='O';
                winmove=true;
                return;
            }
            if(move=='X'&&xcnt==0&&ocnt==2){
                blocki=iemp;
                blockj=jemp;
            }
            if(move=='O'&&xcnt==2&&ocnt==0){
                blocki=iemp;
                blockj=jemp;
            }

        }

        //VERTICAL
        for(int i=0;i<3;i++){
            int xcnt=0;
            int ocnt=0;
            int iemp=0;
            int jemp=0;
            for(int j=0;j<3;j++){
                if(arr[j][i]=='X'){
                    xcnt++;
                }
                else if(arr[j][i]=='O'){
                    ocnt++;
                }
                else{
                    iemp=j;
                    jemp=i;
                }
            }
            if(move=='X'&&xcnt==2&&ocnt==0){
                arr[iemp][jemp]='X';
                winmove=true;
                return;
            }
            if(move=='O'&&xcnt==0&&ocnt==2){
                arr[iemp][jemp]='O';
                winmove=true;
                return;
            }
            if(move=='X'&&xcnt==0&&ocnt==2){
                blocki=iemp;
                blockj=jemp;
            }
            if(move=='O'&&xcnt==2&&ocnt==0){
                blocki=iemp;
                blockj=jemp;
            }
        }

        //DIAGONAL L to R
        int xcnt=0;
        int ocnt=0;
        int iemp = 0;
        int jemp = 0;
        for(int i=0;i<3;i++) {



            if (arr[i][i] == 'X') {
                xcnt++;
            } else if (arr[i][i] == 'O') {
                ocnt++;
            } else {
                iemp = i;
                jemp = i;
            }
        }
        if(move=='X'&&xcnt==2&&ocnt==0){
            arr[iemp][jemp]='X';
            winmove=true;
            return;
        }
        if(move=='O'&&xcnt==0&&ocnt==2){
            arr[iemp][jemp]='O';
            winmove=true;
            return;
        }
        if(move=='X'&&xcnt==0&&ocnt==2){
            blocki=iemp;
            blockj=jemp;
        }
        if(move=='O'&&xcnt==2&&ocnt==0){
            blocki=iemp;
            blockj=jemp;
        }

//DIAGONAL R TO L
        xcnt=0;
        ocnt=0;
        iemp=0;
        jemp=0;
        for(int i=0;i<3;i++) {


            if (arr[i][2 - i] == 'X') {
                xcnt++;
            } else if (arr[i][2 - i] == 'O') {
                ocnt++;
            } else {
                iemp = i;
                jemp = 2 - i;
            }
        }
        if(move=='X'&&xcnt==2&&ocnt==0){
            winmove=true;
            arr[iemp][jemp]='X';
            return;
        }
        if(move=='O'&&xcnt==0&&ocnt==2){
            winmove=true;
            arr[iemp][jemp]='O';
            return;
        }
        if(move=='X'&&xcnt==0&&ocnt==2){
            blocki=iemp;
            blockj=jemp;
        }
        if(move=='O'&&xcnt==2&&ocnt==0){
            blocki=iemp;
            blockj=jemp;
        }
//blocking move
        if(winmove==false&&blocki!=-1){

            arr[blocki][blockj]=move;
            return;
        }
        //Fallback move
        else if(blocki==-1){
            compMove(arr,move);
            return;
        }




    }


    static void hard(char[][] arr,char move){
        int bestScore = Integer.MIN_VALUE;
        int bestMovei=0;
        int bestMovej=0;
        AI = move;
        System.out.println("Making move level \"hard\"");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int score=Integer.MIN_VALUE;
                char nxtMove;
                if(move=='X'){nxtMove='O';}
                else{nxtMove='X';}

                if(arr[i][j]==' '){
                    arr[i][j]=move;
                    //check endgame

                    if (finished(arr, true)) {
                        if ((Xwin == 1 && AI == 'X') || (Owin == 1 && AI == 'O'))
                            score = 1;
                        else if (Xwin == 1 || Owin == 1)
                            score = -1;
                        else
                            score = 0;
                    }else {
                        score = minimax(arr, nxtMove, nxtMove == AI);
                    }

                    arr[i][j]=' ';
                }
                if(score>bestScore){
                    bestScore=score;
                    bestMovej=j;
                    bestMovei=i;
                }

            }
        }
        arr[bestMovei][bestMovej]=move;

    }

    static int minimax(char[][] arr,char move,boolean isMaximising){
        int bestscore=Integer.MIN_VALUE;

        if(isMaximising){
            bestscore=Integer.MIN_VALUE;
        }
        else {
            bestscore=Integer.MAX_VALUE;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' ') {
                    arr[i][j] = move;

                    int score;
                    char nxtMove;
                    if(move=='X'){nxtMove='O';}
                    else nxtMove='X';


                    if (finished(arr, true)) {
                        if ((Xwin == 1 && AI == 'X') || (Owin == 1 && AI == 'O'))
                            score = 1;
                        else if (Xwin == 1 || Owin == 1)
                            score = -1;
                        else
                            score = 0;
                    }else {
                        score = minimax(arr, nxtMove, !isMaximising);
                    }

                    arr[i][j] = ' ';

                    if (isMaximising)
                        bestscore = Math.max(bestscore, score);
                    else
                        bestscore = Math.min(bestscore, score);
                }
            }
        }
        return bestscore;
    }





    public static void main(String[] args) {


        Scanner sc=new Scanner(System.in);

        char[][] arr=new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }
        printGrid(arr);
        //User input

        while(true) {

//Menu
            System.out.println("Input command:");
            String line = sc.nextLine();

            if (line.equals("exit")) {
                break;
            }

            String[] menu = line.split(" ");
            if (!menu[0].equals("start")) {
                System.out.println("Bad parameters!");
                continue;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = ' ';
                }
            }
            printGrid(arr);


            if (menu.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

// input 4 cases
            if(menu[1].equals("easy") &&menu[2].equals("easy")){
                while(true){
                    compMove(arr,'X');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                    compMove(arr,'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }
            else if(menu[1].equals("user") &&menu[2].equals("easy")){
                while(true){
                    userMove(arr,'X',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                    compMove(arr,'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }
            else if(menu[1].equals("easy") &&menu[2].equals("user")){
                while(true){
                    compMove(arr,'X');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                    userMove(arr,'O',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }
            else if(menu[1].equals("user") &&menu[2].equals("user")){
                while(true){
                    userMove(arr,'X',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                    userMove(arr,'O',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("medium")&&menu[2].equals("medium")) {
                while (true) {
                    medium(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    medium(arr, 'O');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                }
            }

            else if(menu[1].equals("medium")&&menu[2].equals("user")){
                while (true) {
                    medium(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    userMove(arr,'O',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("user")&&menu[2].equals("medium")){
                while (true) {
                    userMove(arr, 'X', sc);
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    medium(arr, 'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("easy")&&menu[2].equals("medium")){
                while (true) {
                    compMove(arr,'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    medium(arr, 'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("medium")&&menu[2].equals("easy")){
                while (true) {
                    medium(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    compMove(arr,'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("hard")&&menu[2].equals("hard")) {
                while (true) {
                    hard(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    medium(arr, 'O');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                }
            }

            else if(menu[1].equals("hard")&&menu[2].equals("user")){
                while (true) {
                    hard(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    userMove(arr,'O',sc);
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("user")&&menu[2].equals("hard")){
                while (true) {
                    userMove(arr, 'X', sc);
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    hard(arr, 'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("easy")&&menu[2].equals("hard")){
                while (true) {
                    compMove(arr,'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    hard(arr, 'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else if(menu[1].equals("hard")&&menu[2].equals("easy")){
                while (true) {
                    hard(arr, 'X');
                    printGrid(arr);
                    if (finished(arr,false)) {
                        break;
                    }
                    compMove(arr,'O');
                    printGrid(arr);
                    if(finished(arr,false)){
                        break;
                    }
                }
            }

            else{
                System.out.println("Invalid Input");
            }





        }


    }
}
