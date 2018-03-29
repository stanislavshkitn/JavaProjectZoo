package jdbc;
import java.util.List;
import javax.sql.DataSource;

import dao.AnimalsDao;
import mapper.AnimalsMapper;
import models.Animal;
import org.springframework.jdbc.core.JdbcTemplate;

//@Repository
public class AnimalsJDBC implements AnimalsDao {
    //@Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    //@PostConstruct
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Animal object){
        String sql = "insert into TBL_ANIMALS (NAME, IS_PREDATOR) values (?, ?)";
        int is_predator = 0;
        if (object.getIs_predator()){
            is_predator = 1;
        }
        jdbcTemplateObject.update( sql, object.getName(), is_predator);
    }

    @Override
    public List<Animal> getAll(){
        String sql = "select * from TBL_ANIMALS";
        List <Animal> animals = jdbcTemplateObject.query(sql, new AnimalsMapper());
        return animals;
    }

    @Override
    public Animal getObj(String pk){
        String sql = "select * from TBL_ANIMALS where NAME = ?";
        Animal animal = jdbcTemplateObject.queryForObject(sql, new Object []{pk}, new AnimalsMapper());
        return animal;
    }

/*    @Override
    public void update(String pk, Animal object){

    }*/

    @Override
    public void delete(String pk){
        String sql = "delete from TBL_ANIMALS where NAME = ?";
        jdbcTemplateObject.update(sql, pk);
    }
}
