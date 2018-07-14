package core.loader;

import core.constant.FileType;
import core.dom.Node;
import core.exception.D2pNotFoundException;
import core.util.FileHelper;

import java.io.File;
import java.util.ArrayList;

public class LoaderImpl implements ILoader {
    private Node projectNode;

    public void load(String projectPath) throws D2pNotFoundException {
        String absolutePath = FileHelper.getAbsolutePath(projectPath);
        /*create root node*/
        initProjectNode(absolutePath);

        /*build tree from folder*/
        buildPrimitiveTree();
    }

    private void initProjectNode(String projectPath) throws D2pNotFoundException {
        File file = new File(projectPath);
        if (!file.exists()) {
            throw new D2pNotFoundException("File not exists");
        }
        if (!file.isDirectory()) {
            throw new D2pNotFoundException("File must be directory");
        }
        projectNode = new Node();
        projectNode.setAbsolutePath(projectPath);
        projectNode.setName(file.getName());
    }

    private void buildPrimitiveTree() {
        buildSubTree(projectNode);
    }

    private void buildSubTree(Node parentNode) {
        ArrayList<String> absolutePathChilds = FileHelper.getAbsolutePathChilds(parentNode.getAbsolutePath());
        for (String absolutePath : absolutePathChilds) {

        }

    }
}
