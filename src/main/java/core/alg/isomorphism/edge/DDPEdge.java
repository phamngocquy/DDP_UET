package core.alg.isomorphism.edge;

import core.constant.JavaTypeDependencies;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DDPEdge extends DefaultEdge {


    private Set<JavaTypeDependencies> dependencies;

    public DDPEdge(Set<JavaTypeDependencies> dependenciesSet) {
        super();
        dependencies =  dependenciesSet;
    }

    public Set<JavaTypeDependencies> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<JavaTypeDependencies> dependencies) {
        this.dependencies = dependencies;
    }

    public void addDependencies(JavaTypeDependencies javaTypeDependencies) {
        dependencies.add(javaTypeDependencies);
    }

    @Override
    protected Object getSource() {
        return super.getSource();
    }

    @Override
    protected Object getTarget() {
        return super.getTarget();
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public void getDepedencyStr()
    {
        for (JavaTypeDependencies javaTypeDependencies : dependencies)
            System.out.println(javaTypeDependencies);
    }
}
