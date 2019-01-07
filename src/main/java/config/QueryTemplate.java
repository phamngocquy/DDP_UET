package config;

public class QueryTemplate {
    public static String setObjQuery = "insert into ddpuet(graph,name) values(?,?)";
    public static String createQuery = "create table if not exists ddpuet(graph other,name text)";
    public static String selByNameQuery = "select * from ddpuet where name = ? ";
    public static String dropQuery = "DELETE FROM ddpuet";
}
