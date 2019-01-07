//package database.h2db;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//@PropertySource("classpath:application.properties")
//@Repository
//public class H2JDBC {
//
//    @Autowired
//    private Environment env;
//    private static H2JDBC h2connection;
//
//    public static H2JDBC getCurrentConnection() {
//        if (h2connection == null) h2connection = new H2JDBC();
//        return h2connection;
//    }
//
//    public void get() {
//
//    }
//
//    public void insertObject(Object obj) {
//
//    }
//
//}
