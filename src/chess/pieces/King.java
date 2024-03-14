package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position aPosition = new Position(0, 0);

        // above
        aPosition.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // below
        aPosition.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // left
        aPosition.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // right
        aPosition.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // nw
        aPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // ne
        aPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // sw
        aPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // se
        aPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(aPosition) && canMove(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        return mat;
    }

}
