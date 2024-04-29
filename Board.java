package test;

import java.util.ArrayList;

public class Board {
    Tile[][] board;            // 2D array representing the game board
    String[][] color;          // 2D array representing colors of each cell
    private static Board item; // Singleton instance of the Board
    private static int numofwordsonboard;  // Number of words currently on the board

    // Constructor for Board
    public Board() {
        numofwordsonboard = 0;
        board = new Tile[15][15];
        color = new String[15][15];

        // Initialize color array with empty strings
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) {
                this.color[i][j] = "";
            }
        this.color[7][7] = "white";
        this.color[0][0] = "blue";
        this.color[7][0] = "blue";
        this.color[14][0] = "blue";
        this.color[7][0] = "blue";
        this.color[14][7] = "blue";
        this.color[14][0] = "blue";
        this.color[14][14] = "blue";
        this.color[1][1] = "white";
        this.color[2][2] = "white";
        this.color[3][3] = "white";
        this.color[4][4] = "white";
        this.color[13][1] = "white";
        this.color[12][2] = "white";
        this.color[11][3] = "white";
        this.color[10][4] = "white";
        this.color[10][10] = "white";
        this.color[11][11] = "white";
        this.color[12][12] = "white";
        this.color[13][13] = "white";
        this.color[4][10] = "white";
        this.color[1][1] = "white";
        this.color[3][11] = "white";
        this.color[2][12] = "white";
        this.color[1][13] = "white";
        this.color[3][0] = "black";
        this.color[11][0] = "black";
        this.color[6][2] = "black";
        this.color[8][2] = "black";
        this.color[0][3] = "black";
        this.color[14][3] = "black";
        this.color[6][6] = "black";
        this.color[8][6] = "black";
        this.color[12][6] = "black";
        this.color[3][7] = "black";
        this.color[11][7] = "black";
        this.color[2][8] = "black";
        this.color[6][8] = "black";
        this.color[8][8] = "black";
        this.color[12][8] = "black";
        this.color[0][11] = "black";
        this.color[14][11] = "black";
        this.color[6][12] = "black";
        this.color[3][14] = "black";
        this.color[11][14] = "black";
        this.color[7][7] = "Pink";
        this.color[5][1] = "green";
        this.color[9][1] = "green";
        this.color[5][1] = "green";
        this.color[1][5] = "green";
        this.color[5][5] = "green";
        this.color[9][5] = "green";
        this.color[13][5] = "green";
        this.color[1][9] = "green";
        this.color[5][9] = "green";
        this.color[9][9] = "green";
        this.color[13][9] = "green";
        this.color[5][13] = "green";
        this.color[9][13] = "green";
    }

    public static Board getBoard() {
        if (item == null)
            item = new Board();
        return item;
    }

    public Tile[][] getTiles() {
        Tile[][] clonedBoard = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                clonedBoard[i][j] = board[i][j];
            }
        }
        return clonedBoard;
    }

    public boolean boardLegal(Word word) {
        if (board[7][7] == null) { // if board empty
            if (On_Board(word) && On_Star(word))
                return true;
            return false;
        } else if (On_Board(word) && Over_Lap(word) && (On_Side(word) || Cover(word)))
            return true;
        return false;
    }

    boolean On_Board(Word word) {
        int len = word.tiles.length;
        if (!word.vertical)
            if (word.row >= 0 && word.col >= 0 && word.row <= 14 && word.col + len - 1 <= 14)
                return true;
        if (word.vertical)
            if (word.col >= 0 && word.col <= 14 && word.row >= 0 && word.row + len - 1 <= 14)
                return true;
        return false;
    }

    boolean On_Star(Word word) {
        if (!word.vertical)
            if (word.row == 7 && word.col <= 7 && word.col + word.tiles.length - 1 >= 7)
                return true;
        if (word.vertical)
            if (word.col == 7 && word.row <= 7 && word.row + word.tiles.length - 1 >= 7)
                return true;
        return false;
    }

    boolean On_Side(Word word) {
        if (!word.vertical) {
            int col = word.col;
            if (Right(word.row, word.col) || Left(word.row, (word.col) + (word.tiles.length - 1)))
                return true;
            for (int i = 0; i < word.tiles.length; i++) {
                if (Up(word.row, col) || Down(word.row, col))
                    return true;
                col++;
            }
        } else {
            int row = word.row;
            if (Up(word.row, word.col) || Down((word.row) + (word.tiles.length - 1), word.col))
                return true;
            for (int i = 0; i < word.tiles.length; i++) {
                if (Right(row, word.col) || Left(row, word.col))
                    return true;
                row++;
            }
        }
        return false;
    }

    boolean Over_Lap(Word word){
        int row=word.row;
        int col=word.col;
        if(!word.vertical){
            for (int i = 0; i < word.tiles.length; i++) {
                if (board[word.row][col] == null && word.tiles[i] == null)
                    return false;
                if(board[word.row][col]!=null && word.tiles[i]!=null)
                    return false;
                col++;
            }
        }
        else{
            for (int i = 0; i < word.tiles.length; i++){
                if (board[row][word.col] == null && word.tiles[i] == null)
                    return false;
                if (board[row][word.col]!=null && word.tiles[i]!=null)
                    return false;
                row++;
            }
        }return true;
    }
    boolean Cover(Word word){
        if(!word.vertical){
            for (int i = 0; i < word.tiles.length; i++) {
                if (board[word.row][word.col] != null && word.tiles[i] == null)
                    return true;
            }
        }
        else{
            for (int i = 0; i < word.tiles.length; i++){
                if (board[word.row][word.col] != null && word.tiles[i] == null)
                    return true;
            }
        }return false;
    }
    boolean Left(int i, int j){//ifthereistileonleft
        if(i>=0 && i<=14 && j-1>=0 && j-1<=14 && board[i][j-1]!=null)
            return true;
        return false;
    }
    boolean Right(int i, int j){//ifthereistileonright
        if(i>=0 && i<=14 && j+1>=0 && j+1<=14 && board[i][j+1]!=null)
            return true;
        return false;
    }
    boolean Up(int i, int j){//ifthereistileup
        if(i-1>=0 && i-1<=14 && j>=0 && j<=14 && board[i-1][j]!=null)
            return true;
        return false;
    }
    boolean Down(int i, int j){//ifthereistiledown
        if(i+1>=0 && i+1<=14 && j>=0 && j<=14 && board[i+1][j]!=null)
            return true;
        return false;
    }

    public boolean dictionaryLegal(Word word) {
        return true;
    }
    public ArrayList<Word> getWords(Word word){
        Tile[][] tempboard=getTiles();
        putonboard(tempboard,word);
        ArrayList<Word> arrwords=new ArrayList<Word>();
        Word w,ww; Tile[]tiles=new Tile[word.tiles.length];
        if(!word.vertical){
            int col1=word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[word.row][col1]; col1++;
            } ww=new Word(tiles,word.row,word.col,false); arrwords.add(ww);
            if(Left(word.row, word.col)) {
                w = Get_From_Left(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Right(word.row, word.col+word.tiles.length-1)) {
                w= Ger_Right(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int col=word.col;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Up(word.row,col) && word.tiles[k]!=null) {
                    w1 = Get_From_Up(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                else if(Down(word.row, col) && word.tiles[k]!=null) {
                    w1 = Get_Down(tempboard,word.row, col);
                    arrwords.add(w1);
                }
                col++;
            }
        }
        else{
            int row1=word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                tiles[i]=tempboard[row1][word.col]; row1++;
            } ww=new Word(tiles,word.row,word.col,true); arrwords.add(ww);
            if(Up(word.row, word.col)) {
                w= Get_From_Up(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            else if(Down(word.row+word.tiles.length-1, word.col)) {
                w=Get_Down(tempboard,word.row, word.col);
                arrwords.add(w);
            }
            int row=word.row;
            for(int k=0; k<word.tiles.length; k++){
                Word w1;
                if(Left(row,word.col) && word.tiles[k]!=null) {
                   w1=Get_From_Left(tempboard,row, word.col);
                   arrwords.add(w1);
                }
                else if(Right(row, word.col)&& word.tiles[k]!=null) {
                    w1=Ger_Right(tempboard,row, word.col);
                    arrwords.add(w1);
                }
                row++;
            }
        }return arrwords;
   }

    Word Get_From_Up(Tile[][] tempboard,int i, int j){
        int row=i, countup=0,countdown=0, row1=i;
        while(Up(i,j)){
            countup++;
            i--;
        }
        while(Down(row,j)){
            countdown++;
            row++;
        }
        int first=row1-countup;
        int nrow= row1-countup;
        Tile[] tiles=new Tile[countdown+countup+1];
        for(int k=0; k<countdown+countup+1; k++) {
            tiles[k] = tempboard[nrow][j];
            nrow++;
        } Word word= new Word(tiles, first,j,true);
        return word;
    }
    Word Get_Down(Tile[][] tempboard, int i, int j){
        int countdown=0;
        int first=i;
        while(Down(i,j)){
            countdown++;
            i++;
        }
        i=i-countdown;
        Tile[] tiles=new Tile[countdown+1];
        for(int k=0; k<countdown+1; k++){
            tiles[k]=tempboard[i][j];
            i++;
        }Word word=new Word(tiles,first,j,true);
        return word;
    }
    Word Get_From_Left(Tile[][] tempbaord,int i, int j){
        int col=j;
        int countleft=0;
        int countright=0;
        while(Left(i,j)){
            countleft++;
            j--;
        }int first=j;
        int ncol=j;
        while(Right(i,col)){
            countright++;
            col++;
        }
        Tile[] tiles=new Tile[countleft+countright+1];
        for(int k=0; k<countleft+countright+1; k++){
            tiles[k]=tempbaord[i][ncol];
            ncol++;
        } Word word=new Word(tiles,i,first,false);
        return word;
    }
    Word Ger_Right(Tile[][] tempboard ,int i, int j){
        int col=j;
        int first=j;
        int counterright=0;
        while (Right(i,j)){
            counterright++;
            j++;
        }Tile[] tiles=new Tile[counterright+1];
        for(int k=0; k<counterright+1; k++){
            tiles[k]= tempboard[i][col];
            col++;
        }
        Word word=new Word(tiles,i,first,false);
        return word;
    }
    int getScore(Word word) {
        int score = 0, green = 0, red = 0;
        if (!word.vertical) {
            int col = word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                if (color[word.row][col] == "black")
                    score += (word.tiles[i].score * 2);
                else if (color[word.row][col] == "green")
                    score += (word.tiles[i].score * 3);
                else if (color[word.row][col] == "white" && numofwordsonboard != 1) {
                    green++;
                    score += word.tiles[i].score;
                } else if (color[word.row][col] == "blue") {
                    red++;
                    score += word.tiles[i].score;
                    ;
                } else score += word.tiles[i].score;
                col++;
            }
        } else {
            int row = word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                if (color[row][word.col] == "black")
                    score += (word.tiles[i].score * 2);
                else if (color[row][word.col] == "green")
                    score += (word.tiles[i].score * 3);
                else if (color[row][word.col] == "white" && numofwordsonboard != 1) {
                    green++;
                    score += word.tiles[i].score;
                } else if (color[row][word.col] == "blue") {
                    red++;
                    score += word.tiles[i].score;
                } else score += word.tiles[i].score;
                row++;
            }
        }
        if (green > 0) score = (green * (score * 2));
        if (red > 0) score = (red * (score * 3));
        if (numofwordsonboard == 1)
            score = score * 2;
        return score;
    }
    public int tryPlaceWord(Word word) {
        ArrayList<Word> arrwords;
        int score = 0;
        if (numofwordsonboard == 0 && boardLegal(word)) {
            putonboard(board, word);
            return getScore(word);
        }
        if (boardLegal(word)) {
            arrwords = getWords(word);
            for (int i = 0; i < arrwords.size(); i++)
                if (!dictionaryLegal(arrwords.get(i)))
                    return 0;
            putonboard(board, word);
            for (int i = 0; i < arrwords.size(); i++)
                score += getScore(arrwords.get(i));
            return score;
        }
        return 0;
    }
    void putonboard(Tile[][] board, Word word) {
        if (!word.vertical) {
            int col = word.col;
            for (int i = 0; i < word.tiles.length; i++) {
                if (word.tiles[i] != null)
                    board[word.row][col] = word.tiles[i];
                col++;
            }
        } else {
            int row = word.row;
            for (int i = 0; i < word.tiles.length; i++) {
                if (word.tiles[i] != null)
                    board[row][word.col] = word.tiles[i];
                row++;
            }
        }
        numofwordsonboard++;
    }
}
