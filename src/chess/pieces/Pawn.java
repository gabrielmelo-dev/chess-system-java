package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int color = getColor() == Color.WHITE ? 1 : -1;
        int enPassantRow = getColor() == Color.WHITE ? 3 : 4;
        Position aPosition = new Position(0, 0);

        // forward 1
        aPosition.setValues(position.getRow() - 1 * color, position.getColumn());
        if (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // forward 2
        aPosition.setValues(position.getRow() - 2 * color, position.getColumn());
        Position aPosition2 = new Position(position.getRow() - 1 * color, position.getColumn());
        if (getBoard().positionExists(aPosition) && !getBoard().thereIsAPiece(aPosition)
                && getBoard().positionExists(aPosition2)
                && !getBoard().thereIsAPiece(aPosition2) && getMoveCount() == 0) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // NW
        aPosition.setValues(position.getRow() - 1 * color, position.getColumn() - 1 * color);
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // NE
        aPosition.setValues(position.getRow() - 1 * color, position.getColumn() + 1 * color);
        if (getBoard().positionExists(aPosition) && isThereOpponentPiece(aPosition)) {
            mat[aPosition.getRow()][aPosition.getColumn()] = true;
        }

        // #special move en passant
        if (position.getRow() == enPassantRow) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if (getBoard().positionExists(left) && isThereOpponentPiece(left)
                    && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                mat[left.getRow() - 1 * color][left.getColumn()] = true;
            }
            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if (getBoard().positionExists(right) && isThereOpponentPiece(right)
                    && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                mat[right.getRow() - 1 * color][right.getColumn()] = true;
            }
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}