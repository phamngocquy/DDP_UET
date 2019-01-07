package database.h2db;

import core.dom.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = H2JDBC.class)
@TestPropertySource(locations = "classpath:application.properties")
public class test {


    @Test
    public void testProperties() {
        H2DAO h2DAO = new H2DAO();
        Node node = new Node();
        node.setName("quypn2");
        Node node2 = new Node();
        node2.setName("quypn3_child");

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(node2);

        node.setChild(nodes);

        h2DAO.setObject(node);
        Node node1 = h2DAO.getObjectByName("quypn3");
        System.out.println(node.getName());
        System.out.println(node.getChild().get(0).getName());
    }
}
