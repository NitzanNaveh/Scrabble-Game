package test;

import java.util.Objects;

public class Tile {
    final char letter ;
    final int score;
    
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for same object reference
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or different class
        Tile other = (Tile) obj; // Cast to Tile object
        return letter == other.letter && score == other.score; // Compare letter and value
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score); // Use Objects.hash for consistent hashing
    }

    public char getLetter(){
        return this.letter;
    }

    public int getScore(){
        return this.score;
    }
    

    public static class Bag{

        private static Bag bag;
        public int bag_size; //size of bag-C'TOR
        private int[] tile_amount; // Number of unique values currently in the bag-C'tor->clone
        private final int[] max_tile_amount= {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1}; //max num possible from each letter
        public final Tile[] tiles= {
            new Tile ('A', 1),
            new Tile('B',3),
            new Tile('C', 3),
            new Tile('D', 2),
            new Tile('E', 1),
            new Tile('F', 4),
            new Tile('G', 2),
            new Tile('H', 4),
            new Tile('I', 1),
            new Tile('J', 8),
            new Tile('K', 5),
            new Tile('L', 1),
            new Tile('M', 3),
            new Tile('N', 1),
            new Tile('O', 1),
            new Tile('P', 3),
            new Tile('Q', 10),
            new Tile('R', 1),
            new Tile('S', 1),
            new Tile('T', 1),
            new Tile('U', 1),
            new Tile('V', 4),
            new Tile('W', 4),
            new Tile('X', 8),
            new Tile('Y', 4),
            new Tile('Z', 10)
    };

    public Tile getRand(){
        if (bag_size == 0){
            return null;
        }
        for (int i = 0; i < this.tiles.length; i++) {
            double randomDouble = Math.random();
            int randomValue = (int) (randomDouble * 26);
            if (this.tile_amount[randomValue] > 0) {
            this.tile_amount[randomValue] -= 1;
            this.bag_size -= 1;
            return tiles[randomValue];
            }
        }
        return null;
    }
    
    public Tile getTile(char c){
        for (int i=0; i<this.tiles.length; i++){
            if (c == this.tiles[i].letter){
                this.tile_amount[i] -= 1;
                this.bag_size -= 1;
                return this.tiles[i];
            }
        }
        return null;
    }

    public void put(Tile t){
        if ('A'<= t.letter && t.letter <= 'Z'){
            int i = t.letter - 'A';
            if (this.tile_amount[i]<this.max_tile_amount[i]){
                    this.tile_amount[i]+=1;
                    this.bag_size+=1; 
                }
            }
        }

    public int size(){
        return this.bag_size;
    } 

    public int[] getQuantities(){
        int[] copy = this.tile_amount.clone();
        return copy;
    }

    public static Bag getBag(){
        if (bag == null){
        bag= new Bag();
        }
        return bag;
    }

    private Bag(){
        this.tile_amount = this.max_tile_amount.clone();
        this.bag_size = 98;
    }
}
}
