package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.*;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece {

    private static int[] CANDIDATE_MOVE_COORDINATE = {8, 16};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(PieceType.PAWN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // #TODO more later
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&  // pawn jump
                      (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()) || 
                      (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite())) {

                final int behindCandidateDestinationCoordinate = (this.pieceAlliance.getDirection() * 8) + this.piecePosition;

                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && 
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                }

            } else if (currentCandidateOffset == 7 && 
            !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) || // move 7 (attackmove) dnw for white in eighth column
            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) ) {  // move 7 (attackmove) dnw for black in first column
                if (!board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece candidatePiece = board.getTile(candidateDestinationCoordinate).getPiece();

                    if (this.pieceAlliance != candidatePiece.getPieceAlliance()) {
                        // #TODO add more
                        legalMoves.add(new AttackMove(candidatePiece, board, this, candidateDestinationCoordinate));
                    }

                }
            }

            else if (currentCandidateOffset == 9 && 
            !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) || // move 9 (attackmove) dnw for black in eighth column
            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())) ) {  // move 9 (attackmove) dnw for white in first column
                if (!board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece candidatePiece = board.getTile(candidateDestinationCoordinate).getPiece();

                    if (this.pieceAlliance != candidatePiece.getPieceAlliance()) {
                        // #TODO add more
                        legalMoves.add(new AttackMove(candidatePiece, board, this, candidateDestinationCoordinate));
                    }

                }
            }
        
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }
    
}
