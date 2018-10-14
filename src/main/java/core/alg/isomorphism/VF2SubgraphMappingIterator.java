package core.alg.isomorphism;


import org.jgrapht.Graph;

import java.util.*;

/**
 * This class is used to iterate over all existing (subgraph isomorphic) mappings between two
 * graphs. It is used by the {@link VF2SubgraphIsomorphismInspector}.
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
class VF2SubgraphMappingIterator<V, E> extends VF2MappingIterator<V, E> {
    public VF2SubgraphMappingIterator(
            GraphOrdering<V, E> ordering1, GraphOrdering<V, E> ordering2,
            Comparator<V> vertexComparator, Comparator<E> edgeComparator) {
        super(ordering1, ordering2, vertexComparator, edgeComparator);
    }

    /*
        ToDo
     */
    @Override
    protected IsomorphicGraphMapping<V, E> match() {
        VF2State<V, E> s;
        System.out.println("isomorphic Match");
        if (stateStack.isEmpty()) {
            Graph<V, E> g1 = ordering1.getGraph(), g2 = ordering2.getGraph();

            System.out.println("g1: " + g1.vertexSet());
            System.out.println("g2: " + g2.vertexSet());

            if ((g1.vertexSet().size() < g2.vertexSet().size())
                    || (g1.edgeSet().size() < g2.edgeSet().size())) {
                return null;
            }
            s = new VF2SubgraphIsomorphismState<>(
                    ordering1, ordering2, vertexComparator, edgeComparator);
            if (g2.vertexSet().isEmpty()) {
                return (hadOneMapping != null) ? null : s.getCurrentMapping();
            }
        } else {
            stateStack.pop().backtrack();
            s = stateStack.pop();
        }

        while (true) {
            while (s.nextPair()) {
                if (s.isFeasiblePair()) {
                    stateStack.push(s);
                    s = new VF2SubgraphIsomorphismState<>(s);
                    s.addPair();

                    if (s.isGoal()) {
                        stateStack.push(s);
                        return s.getCurrentMapping();
                    }

                    s.resetAddVertexes();
                }
            }

            if (stateStack.isEmpty()) {
                return null;
            }

            s.backtrack();
            s = stateStack.pop();
        }
    }
}
// End VF2SubgraphMappingIterator.java
