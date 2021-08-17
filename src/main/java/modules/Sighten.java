package modules;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.security.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighten {
    private int id;

    private  Timestamp lastSeen;
    private final int ANIMALID;
    private final String LOCATION;
    Sighten(String location, int animalId){
        this.ANIMALID=animalId;

        this.LOCATION=location;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighten sighten = (Sighten) o;
        return id == sighten.id && ANIMALID == sighten.ANIMALID && Objects.equals(lastSeen, sighten.lastSeen) && Objects.equals(LOCATION, sighten.LOCATION);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastSeen, ANIMALID, LOCATION);
    }

    public int getId() {
        return id;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public Timestamp getLastseen() {
        return lastSeen;
    }

    public int getANIMALID() {
        return ANIMALID;
    }
    public  void save(){
        try(Connection connection= DB.sql2o.open()){
            String sql = "INSERT INTO sighten (LOCATION, lastSeen, ANIMALID) VALUES (:location, now(), :animalid)";
            this.id=(int) connection.createQuery(sql,true)
                    .addParameter("location", this.LOCATION)
                    .addParameter("animalid", this.ANIMALID)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static void delete(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql = "DELETE FROM sighten  WHERE id = :id";
            connection.createQuery(sql).addParameter("id",id).executeUpdate();
        } catch(Sql2oException err){
            System.out.println("error::: "+ err);
        }
    }

    public static List<Sighten> getAll(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM Sighten";
           return connection.createQuery(sql).executeAndFetch(Sighten.class);
        }
    }
    public static void clearAll(){
        try(Connection connection= DB.sql2o.open()){
            String sql ="DELETE FROM sighten *";
            connection.createQuery(sql).executeUpdate();
        }
    }




}
