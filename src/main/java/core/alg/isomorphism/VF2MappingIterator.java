package core.alg.isomorphism;


import com.github.javaparser.utils.Log;
import core.dom.JavaClassNode;
import org.jgrapht.GraphMapping;

import java.util.*;

abstract class VF2MappingIterator<V, E>
        implements
        Iterator<GraphMapping<V, E>> {
    protected Comparator<V> vertexComparator;
    protected Comparator<E> edgeComparator;

    protected IsomorphicGraphMapping<V, E> nextMapping;
    protected Boolean hadOneMapping;

    protected GraphOrdering<V, E> ordering1, ordering2;

    protected ArrayDeque<VF2State<V, E>> stateStack;

    public VF2MappingIterator(
            GraphOrdering<V, E> ordering1, GraphOrdering<V, E> ordering2,
            Comparator<V> vertexComparator, Comparator<E> edgeComparator) {
        this.ordering1 = ordering1;
        this.ordering2 = ordering2;
        this.vertexComparator = vertexComparator;
        this.edgeComparator = edgeComparator;
        this.stateStack = new ArrayDeque<>();
    }

    /**
     * This function moves over all mappings between graph1 and graph2. It changes the state of the
     * whole iterator.
     *
     * @return null or one matching between graph1 and graph2
     */
    protected abstract IsomorphicGraphMapping<V, E> match();

    /*
        ToDo
     */
    protected IsomorphicGraphMapping<V, E> matchAndCheck() {
        IsomorphicGraphMapping<V, E> rel = match();
        /*
            get domain isomorphic in parent graph
         */
        Set<JavaClassNode> nodes = (Set<JavaClassNode>) rel.getMappingDomain();
        for (JavaClassNode node : nodes) {
            System.out.print(node.getName() + " ");
        }
        if (rel != null) {
            hadOneMapping = true;
        }
        return rel;
    }

    @Override
    public boolean hasNext() {
        return nextMapping != null || (nextMapping = matchAndCheck()) != null;

    }

    @Override
    public IsomorphicGraphMapping<V, E> next() {
        if (nextMapping != null) {
            IsomorphicGraphMapping<V, E> tmp = nextMapping;
            nextMapping = null;
            return tmp;
        }

        IsomorphicGraphMapping<V, E> rel = matchAndCheck();
        if (rel == null) {
            throw new NoSuchElementException();
        }
        return rel;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

// End VF2MappingIterator.java
