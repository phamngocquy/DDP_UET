package core.alg.isomorphism.edge;

import core.constant.JavaTypeDependencies;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class DDPEdge extends DefaultEdge {


    private List<JavaTypeDependencies> dependencies;

    public DDPEdge() {
        super();
        dependencies = new ArrayList<>();
    }

    public List<JavaTypeDependencies> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<JavaTypeDependencies> dependencies) {
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
}
