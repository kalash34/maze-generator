package com.zhukovsd.graphs.rectangular;

import com.zhukovsd.graphs.embedded.EmbeddedVertex;
import java.awt.geom.Point2D;

/**
 * Vertex of graph in form of rectangular lattice, which position described by row and column indexes in this lattice.
 */
class RectangularVertex extends EmbeddedVertex {
    private int row, column;

    public RectangularVertex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    protected Point2D.Double getPosition() {
        return new Point2D.Double(column, row);
    }
}