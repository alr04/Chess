package com.chess.engine.player;

import com.chess.engine.board.*;

public class MoveTransition {

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition (final Board transitonBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitonBoard;
        this.move = move;
        this.moveStatus = moveStatus; 
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }


}
