package com.chess.engine.board;


public abstract class Tile {
    
    // number representing position of tile (0-63 for all 64 tiles on a chessboard)
    int tilecoordinate;

    Tile(int tilecoordinate) {

        //Assign coordinate to tile when created
         this.tilecoordinate = tilecoordinate;

    }

    public abstract boolean isTileOccupied();

    // to get the piece from a given Tile object
    public abstract Piece getPiece();


    public static final class EmptyTile extends Tile {

        EmptyTile(int coordinate) {
`
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

            // no piece to get on an empty tile
            return null;
        }
    }


    public static final class OccupiedTile extends Tile {

        // occupied tile actually houses a piece
        Piece pieceOnTile;

        int tilecoordinate;

        OccupiedTile(int tilecoordinate, Piece pieceOnTile) {

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
