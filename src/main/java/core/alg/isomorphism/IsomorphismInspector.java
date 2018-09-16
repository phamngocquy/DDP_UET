package core.alg.isomorphism;


import org.jgrapht.GraphMapping;

import java.util.*;

/**
 * General interface for graph and subgraph isomorphism.
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
public interface IsomorphismInspector<V, E> {
    /**
     * Get an iterator over all calculated (isomorphic) mappings between two graphs.
     *
     * @return an iterator over all calculated (isomorphic) mappings between two graphs
     */
    Iterator<GraphMapping<V, E>> getMappings();

    /**
     * Check if an isomorphism exists.
     *
     * @return true if there is an isomorphism, false if there is no isomorphism
     */
    boolean isomorphismExists();
}

// End IsomorphismInspector.java
