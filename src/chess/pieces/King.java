package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece != null && piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
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

        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // #special move castling king side rook
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }            
            // #special move castling queen side rook
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null)  {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        } 

        return mat;
    }

}