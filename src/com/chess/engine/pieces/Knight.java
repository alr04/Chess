package com.chess.engine.board;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.*;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int candidateDestinationCoordinate;

        List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {

            candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if (isValidTileCoordinate(candidateDestinationCoordinate)) {

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

       return ImmutableList.copyOf(legalMoves);

    }

    public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
        return false;
    }


    
    
}
