package test;


public class Word {
    
    Tile[] tiles;
    Boolean vertical; // true if vertical, false if horizontal
    int row, col;
    
public Word(Tile[] tiles) {
    this.tiles = tiles;
}

public Word(Tile[] t, int row, int col, Boolean vert) {
    this.tiles = t;
    this.row = row;
    this.col = col;
    this.vertical = vert;
}

public Tile[] getTiles() {
    return this.tiles;
}

public Boolean getVertical() {
    return vertical;
}

public int getRow(){
    return this.row;
}

public int getCol(){
    return this.col;
}

public int getLength(){
    return this.tiles.length;
}

@Override
public String toString() {
    String s = "";
    for(Tile tile: tiles){
        s+=tile.getLetter();
    }
    return s;
}
/* for (int i = 0; i < this.tiles.length; i++) {
        s += this.tiles[i].getLetter();} */

    public boolean inBounds(Word word) {
    if (word.col < 0 || word.row < 0){
        return false;
    }
    int wordLength = word.tiles.length;
    if (word.getVertical()) {
        return word.row + wordLength <= this.row && word.col < this.col;
    } else {
        return word.row < this.row && word.col + wordLength <= this.col;
    }
}


 
@Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
