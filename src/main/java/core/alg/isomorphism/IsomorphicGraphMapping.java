package core.alg.isomorphism;

import org.jgrapht.Graph;
import org.jgrapht.GraphMapping;

import java.util.*;

/**
 * This class represents a GraphMapping between two (subgraph)isomorphic graphs. In the subgraph
 * isomorphic case, the second one is assumed to be a subgraph of the first one.
 *
 * @author Fabian Späh
 * @author Alexandru Valeanu
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
public class IsomorphicGraphMapping<V, E> implements GraphMapping<V, E> {

    public static final int NULL_NODE = -1;

    private final Map<V, V> forwardMapping;
    private final Map<V, V> backwardMapping;

    private final Graph<V, E> graph1;
    private final Graph<V, E> graph2;

    /**
     * Construct a new isomorphic graph mapping
     * 
     * @param g1 the first graph
     * @param g2 the second graph which is a possible subgraph of g1
     * @param core1 the mapping as array (forwards)
     * @param core2 the mapping as array (backwards)
     */
    public IsomorphicGraphMapping(GraphOrdering<V, E> g1, GraphOrdering<V, E> g2, int[] core1, int[] core2) {
        this.graph1 = g1.getGraph();
        this.graph2 = g2.getGraph();

        this.forwardMapping = new HashMap<>(this.graph1.vertexSet().size());
        this.backwardMapping = new HashMap<>(this.graph1.vertexSet().size());

        for (V v: graph1.vertexSet()){
            int vNumber = g1.getVertexNumber(v);
            int uNumber = core1[vNumber];

            if (uNumber != NULL_NODE) {
                forwardMapping.put(v, g2.getVertex(uNumber));
            }
        }

        for (V v: graph2.vertexSet()){
            int vNumber = g2.getVertexNumber(v);
            int uNumber = core2[vNumber];

            if (uNumber != NULL_NODE) {
                backwardMapping.put(v, g1.getVertex(uNumber));
            }
        }
    }

    /**
     * Construct a new isomorphic graph mapping.
     *
     * @param forwardMapping the mapping from graph1 to graph2
     * @param backwardMapping the mapping from graph2 to graph1 (inverse of forwardMapping)
     * @param graph1 the first graph
     * @param graph2 the second graph

     */
    public IsomorphicGraphMapping(Map<V, V> forwardMapping, Map<V, V> backwardMapping, Graph<V, E> graph1, Graph<V, E> graph2) {
        this.forwardMapping = Objects.requireNonNull(forwardMapping);
        this.backwardMapping = Objects.requireNonNull(backwardMapping);

        this.graph1 = Objects.requireNonNull(graph1);
        this.graph2 = Objects.requireNonNull(graph2);
    }

    @Override
    public V getVertexCorrespondence(V v, boolean forward) {
        if (forward)
            return forwardMapping.get(v);
        else
            return backwardMapping.get(v);
    }

    @Override
    public E getEdgeCorrespondence(E e, boolean forward) {
        Graph<V, E> fromGraph;
        Graph<V, E> toGraph;

        if (forward) {
            fromGraph = graph1;
            toGraph = graph2;

        } else {
            fromGraph = graph2;
            toGraph = graph1;
        }

        V u = fromGraph.getEdgeSource(e);
        V v = fromGraph.getEdgeTarget(e);

        V uu = getVertexCorrespondence(u, forward);
        if (uu == null){
            return null;
        }

        V vv = getVertexCorrespondence(v, forward);
        if (vv == null){
            return null;
        }

        return toGraph.getEdge(uu, vv);
    }

    /**
     * Get an unmodifiable version of the forward mapping function.
     *
     * @return the unmodifiable forward mapping function
     */
    public Map<V, V> getForwardMapping(){
        return Collections.unmodifiableMap(forwardMapping);
    }

    /**
     * Get an unmodifiable version of the backward mapping function.
     *
     * @return the unmodifiable backward mapping function
     */
    public Map<V, V> getBackwardMapping(){
        return Collections.unmodifiableMap(backwardMapping);
    }

    /**
     * Get the active domain of the isomorphism.
     *
     * @return the set of vertices $v$ for which the mapping is defined
     */
    public Set<V> getMappingDomain(){
        return Collections.unmodifiableSet(forwardMapping.keySet());
    }

    /**
     * Get the range of the isomorphism.
     *
     * @return the set of vertices $v$ for which a preimage exists
     */
    public Set<V> getMappingRange(){
        return Collections.unmodifiableSet(backwardMapping.keySet());
    }

    /**
     * Checks if a vertex $v$ from the first graph has a corresponding vertex in the second graph
     *
     * @param v the vertex
     * @return is there a corresponding vertex to $v$ in the subgraph
     */
    public boolean hasVertexCorrespondence(V v)
    {
        return getVertexCorrespondence(v, true) != null;
    }

    /**
     * Checks if a edge e from the first graph has a corresponding edge in the second graph
     *
     * @param e the edge
     * @return is there a corresponding edge to $e$ in the subgraph
     */
    public boolean hasEdgeCorrespondence(E e)
    {
        return getEdgeCorrespondence(e, true) != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsomorphicGraphMapping<?, ?> that = (IsomorphicGraphMapping<?, ?>) o;
        return Objects.equals(forwardMapping, that.forwardMapping) &&
                Objects.equals(backwardMapping, that.backwardMapping) &&
                graph1 == that.graph1 &&
                graph2 == that.graph2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(forwardMapping, backwardMapping,
                System.identityHashCode(graph1), System.identityHashCode(graph2));
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("[");
        Set<V> vertexSet = graph1.vertexSet();
        Map<String, V> vertexMap = new TreeMap<>();

        for (V v : vertexSet) {
            vertexMap.put(v.toString(), v);
        }

        int i = 0;
        for (Map.Entry<String, V> entry : vertexMap.entrySet()) {
            V u = getVertexCorrespondence(entry.getValue(), true);
            str.append((i++ == 0) ? "" : " ").append(entry.getKey()).append("=").append(
                (u == null) ? "~~" : u);
        }

        return str + "]";
    }

    /**
     * Checks for equality. Assuming both are mappings on the same graphs.
     *
     * @param rel the corresponding mapping
     *
     * @return do both relations map to the same vertices
     */
    public boolean isEqualMapping(GraphMapping<V, E> rel)
    {
        for (V v : graph2.vertexSet()) {
            if (!getVertexCorrespondence(v, false).equals(rel.getVertexCorrespondence(v, false))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Computes the composition of two isomorphisms.
     * Let $f : V_{G_1} \rightarrow V_{G_2}$ be an isomorphism from $V_{G_1}$ to $V_{G_2}$ and
     * $g : V_{G_2} \rightarrow V_{G_3}$ one from $V_{G_2}$ to $V_{G_3}$.
     *
     * This method computes an isomorphism $h : V_{G_1} \rightarrow V_{G_3}$ from $V_{G_1}$ to $V_{G_3}$.
     *
     * Note: The composition $g ∘ f$ can be built only if $f$'s codomain equals $g$'s domain;
     * this implementation only requires that the former is a subset of the latter.
     *
     * @param otherMapping the other isomorphism (i.e. function $g$)
     * @return the composition of the two isomorphism
     */
    public IsomorphicGraphMapping<V, E> compose(IsomorphicGraphMapping<V, E> otherMapping){
        Map<V, V> fMap = new HashMap<>(forwardMapping.size());
        Map<V, V> bMap = new HashMap<>(forwardMapping.size());

        for (V v: graph1.vertexSet()){
            V u = otherMapping.getVertexCorrespondence(forwardMapping.get(v), true);
            fMap.put(v, u);
            bMap.put(u, v);
        }

        return new IsomorphicGraphMapping<>(fMap, bMap, graph1, otherMapping.graph2);
    }

    /**
     * Computes an identity automorphism (i.e. a self-mapping of a graph in which each vertex also maps to itself).
     *
     * @param graph the input graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return a mapping from graph to graph
     */
    public static <V, E> IsomorphicGraphMapping<V, E> identity(Graph<V, E> graph){
        Map<V, V> fMap = new HashMap<>(graph.vertexSet().size());
        Map<V, V> bMap = new HashMap<>(graph.vertexSet().size());

        for (V v: graph.vertexSet()){
            fMap.put(v, v);
            bMap.put(v, v);
        }

        return new IsomorphicGraphMapping<>(fMap, bMap, graph, graph);
    }
}

// End IsomorphicGraphMapping.java
