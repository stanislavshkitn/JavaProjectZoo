package dao;
import java.util.List;
import javax.sql.DataSource;
import models.Animal;

public interface AnimalsDao {
    public void setDataSource(DataSource ds);
    public void save(Animal object);
    public List<Animal> getAll();
    public Animal getObj(String pk);
    //public void update(String pk, Animal object);
    public void delete(String pk);
}
