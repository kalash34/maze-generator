package com.zhukovsd.graphs;

import com.zhukovsd.graphs.embedded.EmbeddedVertex;

import java.awt.*;

/**
 * Representation of {@link Graph graph}, which can be painted on java.awt.Graphic object.
 * @param <T> type of represented {@link Graph graph}
 * @param <U> type of {@link Vertex vertex} of represented {@link Graph graph}
 */
public abstract class GraphView<T extends Graph<U>, U extends Vertex> {
    /**
     * {@link Graph Graph} object, represented by current view. Immutable.
     */
    private final T graph;

    /**
     * Color which will be used for painting vertexes and edges of graph.
     */
    public Color color;

    public GraphView(T graph) {
        this.graph = graph;
    }

    /**
     * Getter for typed graph, may be overrode if graph have to be specific of specific type, derived from T
     * @return stored graph to represent
     */
    protected T getGraph() {
        return graph;
    }

    /**
     * Abstract method for calculating size of image, representing current view. Must be overrode in derived class
     * @return image size as {@link Point} object
     */
    public abstract Point getSize();

    /**
     * Abstract method for calculating vertex position in current view representation. Must be overrode in derived class
     * @param vertex vertex to calculate position of
     * @return vertex position as {@link Point} object
     */
    protected abstract Point calculateVertexPosition(U vertex);

    /**
     * Paint given vertex on given {@link Graphics graphics} object
     * @param graphics {@link Graphics graphics} object to paint on
     * @param vertex {@link Vertex vertex} object to paint
     */
    protected void paintVertex(Graphics graphics, U vertex) {
        Point position = calculateVertexPosition(vertex);
        graphics.drawLine(position.x, position.y, position.x, position.y);
    }

    /**
     * Paint given link on given {@link Graphics graphics} object
     * @param graphics {@link Graphics graphics} object to paint on
     * @param edge {@link Edge edge} object to paint
     */
    protected void paintEdge(Graphics graphics, Edge edge) {
        Point sourcePosition = calculateVertexPosition((U) edge.destination);
        Point destinationPosition = calculateVertexPosition((U) edge.source);

        graphics.drawLine(sourcePosition.x, sourcePosition.y, destinationPosition.x, destinationPosition.y);
    }

    /**
     * Paint whole view on given {@link Graphics graphics} object
     * @param graphics {@link Graphics graphics} object to paint on
     */
    public void paint(Graphics graphics) {
        graphics.setColor(color);

        for (U vertex : graph.vertexList) {
            paintVertex(graphics, vertex);

            for (Edge edge : vertex.edgeList) {
                paintEdge(graphics, edge);
            }
        }
    }
}