package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
    
    // number representing position of tile (0-63 for all 64 tiles on a chessboard)
    // tilecoordinate can only be accessed by Tile subclasses and can only be set once (at construction time)
    protected final int tilecoordinate;

    // created all possible empty tiles on a board "up front" - no need to create new EmptyTiles now
    // can simply reference this map based on the position of the tile
    // EMPTY_TILES.get(0) would reference top left tile on chessboard
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {

            // create a HashMap represeting a board with 64 EmptyTiles
            // each with a unique tilecoordinate "i" from 0-63
            emptyTileMap.put(i, new EmptyTile(i));

        }

        // use Guava to create unmodifiable map
        return ImmutableMap.copyOf(emptyTileMap);
    }

    private Tile(final int tilecoordinate) {

        //Assign coordinate to tile when created
         this.tilecoordinate = tilecoordinate;

    }

    public abstract boolean isTileOccupied();

    // to get the piece from a given Tile object
    public abstract Piece getPiece();

    // factory method to create tile
    public static Tile createTile(final int tilecoordinate, final Piece piece) {

        // if there is no piece on tile (piece == null) get the empty tile already created in EMPTY_TILES based on its position on the board
        // if there is a piece on the tile create a new OccupiedTile at that posiiton with the specified piece on it
        return piece != null ? new OccupiedTile(tilecoordinate, piece) : EMPTY_TILES_CACHE.get(tilecoordinate);
        
    }


    public static final class EmptyTile extends Tile {

        @Override
        public String toString() {
            return "-";
        }

        private EmptyTile(int coordinate) {

            // call superclass constructor
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            // Empty tiles are not occupied
            return false;
        }

        @Override
        public Piece getPiece() {

            // no piece to retrive from an empty tile
            return null;
        }
    }


    public static final class OccupiedTile extends Tile {

        // occupied tile actually houses a piece
        // can only be acessed from within OccupiedTile class through getPiece()  
        private final Piece pieceOnTile;

        int tilecoordinate;

        @Override
        public String toString() {

            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }

        private OccupiedTile(int tilecoordinate, Piece pieceOnTile) {

            // call superclass constructor for tilecoordinate
            super(tilecoordinate);

            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }

    }


}
