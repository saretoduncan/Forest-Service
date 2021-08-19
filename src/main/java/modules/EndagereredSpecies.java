package modules;

import interfaces.DatabaseManagement;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class EndagereredSpecies extends Animals implements DatabaseManagement {
    private String health;
    private String age;


    public EndagereredSpecies(String name , String health, String age){
        super(name);
        this.name=name;

      
        this.health=health;

        this.age = age;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndagereredSpecies that = (EndagereredSpecies) o;
        return Objects.equals(health, that.health) && Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, age);
    }




    public String getName(){
        return this.name;
}
    public String getHEALTH(){
        return health;
    }
    public String getAGE(){
        return age;
    }

    public int getId(){
        return this.id;
    }

@ Override
    public void save(){
        try(Connection connection= DB.sql2o.open()){
            String sql = "INSERT INTO animals(name, health, age) VALUES (:name, :health, :age);";
            this.id=(int) connection.createQuery(sql,true)
                    .addParameter("health",this.health)
                    .addParameter("age", this.age)
                    .addParameter("name",this.name)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException err){
            System.out.println("error::: "+ err);
        }
    }
    @ Override
    public  void delete(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql= "DELETE FROM animals where id= :id";
            connection.createQuery(sql).addParameter("id",id)
                    .executeUpdate();

        }

    }
    public static EndagereredSpecies findById(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql= "SELECT * FROM animals WHERE id= :id;";
            return connection.createQuery(sql).addParameter("id", id)
                    .executeAndFetchFirst(EndagereredSpecies.class);
        }
    }
    public static Sighten findSighing(int id){
        try (Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM sighting WHERE id = :animalid;";
            return connection.createQuery(sql)
                    .addParameter("animalid", id)
                    .executeAndFetchFirst(Sighten.class);
        }
    }

    public static List<EndagereredSpecies> getAll(){
        try(Connection connection= DB.sql2o.open()){
            String sql ="SELECT * FROM animals;";
            return connection.createQuery(sql).executeAndFetch(EndagereredSpecies.class);
        }

    }
    public static void clearAll(){
        try(Connection connection=DB.sql2o.open()){
            String sql = "DELETE FROM animals *;";
            connection.createQuery(sql).executeUpdate();
        }catch (Sql2oException error){
            System.out.println("error::: "+ error);
        }
    }
}
