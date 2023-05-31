package com.chess.engine.pieces;

public class Piece {
    
    protected final int piecePosition; // tilecoordinate of the tile that piece rests on 
    protected final Alliance pieceAlliance; // white or black
    
    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
    }
}
