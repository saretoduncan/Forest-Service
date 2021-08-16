package modules;

import interfaces.DatabaseManagement;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class EndagereredSpecies  {
    private final String HEALTH;
    private final String AGE;
    private final int ANIMALID;
    private int id;

    EndagereredSpecies(String health, String age,int animalsId){
        this.HEALTH= health;
        this.AGE= age;
        this.ANIMALID= animalsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndagereredSpecies that = (EndagereredSpecies) o;
        return ANIMALID == that.ANIMALID && id == that.id && Objects.equals(HEALTH, that.HEALTH) && Objects.equals(AGE, that.AGE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(HEALTH, AGE, ANIMALID, id);
    }

    public String getHEALTH(){
        return HEALTH;
    }
    public String getAGE(){
        return AGE;
    }
    public int getANIMAL_ID(){
        return ANIMALID;
    }
    public int getId(){
        return  id;
    }


    public void save(){
        try(Connection connection=DB.sql2o.open()){
            String sql = "INSERT INTO endegered (HEALTH, AGE, ANIMALID) VALUES (:health, :age, :animalid)";
            this.id=(int) connection.createQuery(sql,true)
                    .addParameter("health",this.HEALTH)
                    .addParameter("age", this.AGE)
                    .addParameter("animalid",this.ANIMALID)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException err){
            System.out.println("error::: "+ err);
        }
    }

    public static void delete(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql= "DELETE FROM endegered where id= :id";
            connection.createQuery(sql).addParameter("id",id)
                    .executeUpdate();

        }

    }
    public static EndagereredSpecies findById(int id){
        try(Connection connection= DB.sql2o.open()){
            String sql= "SELECT * FROM endegered WHERE id= :id";
            return connection.createQuery(sql).addParameter("id", id)
                    .executeAndFetchFirst(EndagereredSpecies.class);
        }
    }

    public static List<EndagereredSpecies> getAll(){
        try(Connection connection= DB.sql2o.open()){
            String sql ="SELECT * FROM endegered";
            return connection.createQuery(sql).executeAndFetch(EndagereredSpecies.class);
        }

    }
    public static void clearAll(){
        try(Connection connection=DB.sql2o.open()){
            String sql = "DELETE FROM endegered *";
            connection.createQuery(sql).executeUpdate();
        }catch (Sql2oException error){
            System.out.println("error::: "+ error);
        }
    }
}
