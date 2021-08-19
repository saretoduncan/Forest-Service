package modules;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.security.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighten {
    private int id;
    public String ranger;

    private  Timestamp lastSeen;
    private final int ANIMALID;
    public final String LOCATION;
    public Sighten(String ranger, String location, int animalId){
        this.ANIMALID=animalId;
        this.ranger= ranger;
        this.LOCATION=location;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighten sighten = (Sighten) o;
        return id == sighten.id && ANIMALID == sighten.ANIMALID && Objects.equals(ranger, sighten.ranger) && Objects.equals(lastSeen, sighten.lastSeen) && Objects.equals(LOCATION, sighten.LOCATION);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ranger, lastSeen, ANIMALID, LOCATION);
    }

    public String getRanger() {
        return ranger;
    }

    public int getId() {
        return id;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public int getANIMALID() {
        return ANIMALID;
    }
    public  void save(){
        try(Connection connection= DB.sql2o.open()){
            String sql = "INSERT INTO sighting (ranger, LOCATION, lastSeen, ANIMALID) VALUES (:ranger, :location, now(), :animalid);";
            this.id=(int) connection.createQuery(sql,true)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.LOCATION)
                    .addParameter("animalid", this.ANIMALID)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException err){
            System.out.println("error::: "+ err);
        }
    }
    public static void delete(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql = "DELETE FROM sighting  WHERE id = :id;";
            connection.createQuery(sql).addParameter("id",id).executeUpdate();
        } catch(Sql2oException err){
            System.out.println("error::: "+ err);
        }
    }
    public static Sighten findById(int id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM sighting WHERE id = :id";
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighten.class);
        }

    }
    public static List<Sighten> getAll(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM Sighting;";
           return connection.createQuery(sql).executeAndFetch(Sighten.class);
        }
    }
    public static void clearAll(){
        try(Connection connection= DB.sql2o.open()){
            String sql ="DELETE FROM sighting *";
            connection.createQuery(sql).executeUpdate();
        }
    }




}
