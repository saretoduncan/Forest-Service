package modules;

import interfaces.DatabaseManagement;

import java.util.Objects;

public class Animals implements DatabaseManagement {
   private final String NAME;
   private int id;
   Animals (String name){
       this.NAME=name;

   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animals = (Animals) o;
        return id == animals.id && Objects.equals(NAME, animals.NAME);
    }
    public int getId(){
       return this.id;
    }
    public String getNAME(){
       return NAME;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME);
    }
    @Override
    public void save(){

    }
    @Override
    public void delete(){

    }
}
