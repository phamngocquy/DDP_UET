package core.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        projectNode.setType(FileType.DIRECTORY);
    }

    private void buildPrimitiveTree() {
        buildSubTree(projectNode);
//        System.out.println(projectNode.getAllChild().size());
//        System.out.println(JsonHelper.getInstance().getJson(projectNode));

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
                    node.setType(FileType.DIRECTORY);
                    buildSubTree(node);
                } else if (file.exists()) {
                    FileType fileType = FileHelper.getFileType(absolutePath);
                    switch (fileType) {
                        case JAVA_FILE:
                            node = new JavaFileNode();
                            node.setType(FileType.JAVA_FILE);
                            break;
                        default:
                            node = new UnknownFileNode();
                            node.setType(FileType.UNKNOWN);
                            break;
                    }
                }
                if (node != null) {
                    node.setName(file.getName());
                    node.setAbsolutePath(absolutePath);
                    parentNode.addChild(node);
                    node.setParent(parentNode);

                    //parser java file

                }
            }
        }
    }
}
