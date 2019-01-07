package database.h2db;

import config.QueryTemplate;
import core.dom.Node;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class H2DAO {

    private Connection connection;

    public H2DAO() {
        connection = H2JDBC.getH2JDBC().getConnection();
    }

    public void setObject(Node node) {
        try {
            connection.prepareStatement(QueryTemplate.createQuery).executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(QueryTemplate.setObjQuery);
            preparedStatement.setObject(1, node);
            preparedStatement.setObject(2, node.getName());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Node getObjectByName(String name) {
        Node node = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QueryTemplate.selByNameQuery);
            preparedStatement.setObject(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                node = (Node) rs.getObject("graph");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return node;
    }

    public void clearTable() {
        try {
            connection.prepareStatement(QueryTemplate.dropQuery).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
