package core.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Parser.JavaFileParser;
import core.constant.FileType;
import core.dom.JavaFileNode;
import core.dom.Node;
import core.dom.UnknownFileNode;
import core.exception.D2pNotFoundException;
import core.util.FileHelper;
import core.util.JsonHelper;

import java.io.File;
import java.util.ArrayList;

public class LoaderImpl implements ILoader {
    private Node projectNode;
    private JavaFileParser javaFileParser;


    public LoaderImpl() {
        this.javaFileParser = new JavaFileParser();
    }

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
//        projectNode.setType(FileType.DIRECTORY);
    }

    private void buildPrimitiveTree() {
        buildSubTree(projectNode);
    }

    private void buildSubTree(Node parentNode) {
        Node node = null;
        ArrayList<String> absolutePathChilds = FileHelper.getAbsolutePathChilds(parentNode.getAbsolutePath());
        for (String absolutePath : absolutePathChilds) {
            File file = new File(absolutePath);
            if (file.exists()) {
                if (file.isDirectory()) {
                    node = new Node();
                    node.setName(file.getName());
                    node.setAbsolutePath(absolutePath);
                    buildSubTree(node);
                } else if (file.exists()) {
                    FileType fileType = FileHelper.getFileType(absolutePath);
                    switch (fileType) {
                        case JAVAFILE:
                            node = new JavaFileNode();
                            break;
                        default:
                            node = new UnknownFileNode();
                            break;
                    }
                }
                if (node != null) {
                    node.setName(file.getName());
                    node.setAbsolutePath(absolutePath);
                    parentNode.addChild(node);
                    node.setParent(parentNode);

                    //parser java file
                    if (node instanceof JavaFileNode) {
                        javaFileParser.parse(node);
                    }
                }
            }
        }
    }

    public Node getProjectNode() {
        return projectNode;
    }
}
