package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position aPosition = new Position(0, 0);

        // above
        aPosition.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
            aPosition.setRow(aPosition.getRow() - 1);
        }
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // below
        aPosition.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
            aPosition.setRow(aPosition.getRow() + 1);
        }
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // left
        aPosition.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
            aPosition.setColumn(aPosition.getColumn() - 1);
        }
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // right
        aPosition.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
            aPosition.setColumn(aPosition.getColumn() + 1);
        }
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        return mat;
    }

}
