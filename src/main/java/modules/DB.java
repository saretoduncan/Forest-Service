package modules;
import org.sql2o.Sql2o;

public class DB{
    public  static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-35-169-188-58.compute-1.amazonaws.com:5432/dfmsooe8fm8ctg","jllzhgnprmdhfc","bd646d58692ba0b575ed0068f7fe354f5aecde2b5459386f6019cfae1e192501");
//    public  static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker","softwaredev","1234");
}


