package modules;

import interfaces.DatabaseManagement;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animals implements DatabaseManagement {
   private final String name;
   private int id;
   Animals (String name){
       this.name=name;

   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animals = (Animals) o;
        return id == animals.id && Objects.equals(name, animals.name);
    }
    public int getId(){
       return this.id;
    }
    public String getNAME(){
       return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @Override
    public void save(){
       try(Connection connection=DB.sql2o.open()){
           String sql = "INSERT INTO animals (name) VALUES (:name)";
           this.id=(int) connection.createQuery(sql,true)
                   .addParameter("name", this.name)
                   .executeUpdate()
                   .getKey();

                  }catch (Sql2oException err){
           System.out.println("error::: "+ err);
       }

    }

    @Override
    public  void delete(int id ){
       try(Connection connection= DB.sql2o.open()){
           String sql= "DELETE FROM animals where id= :id";
           connection.createQuery(sql).addParameter("id",id).executeUpdate();

           }

       }


    public static List<Animals> getAll(){
       try(Connection connection= DB.sql2o.open()) {
           String sql = "SELECT * FROM animals;";
           return connection.createQuery(sql).executeAndFetch(Animals.class);
       }

    }
}
