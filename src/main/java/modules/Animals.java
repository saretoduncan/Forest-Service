package modules;

import interfaces.DatabaseManagement;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animals  {
   public String name;
   public int id;
  public Animals (String name){
       this.name=name;

   }


}
