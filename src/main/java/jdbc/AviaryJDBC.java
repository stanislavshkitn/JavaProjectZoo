package jdbc;

import mapper.AviaryMapper;
import models.Aviary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class AviaryJDBC {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void save(Aviary obj){
        String sql = "insert into TBL_AVIARY (LOCATION_NAME, NUMBER, ARRAY_ANIMALS) values (?,?,?)";
        String arrAnimals = "";
        String[] arr = obj.getArray_Animals();
        for(int i=0;i<arr.length;i++){
            arrAnimals = arrAnimals + arr[i] +" ";
        }
        jdbcTemplateObject.update( sql, obj.getLocation_Name(), obj.getNumber(),
                arrAnimals);
    }

    public List<Aviary> getAllForLocation(String location_name){
        String sql = "select * from TBL_AVIARY WHERE LOCATION_NAME=\"" + location_name +"\"" + "ORDER BY NUMBER ";
        List <Aviary> list_aviary = jdbcTemplateObject.query(sql, new AviaryMapper());
        return list_aviary;
    }
    public List<Aviary> getNotNullForLocation(String location_name){
        String sql = "select * from TBL_AVIARY WHERE LOCATION_NAME=\"" + location_name +"\"" + "and ARRAY_ANIMALS!=\"\" ORDER BY NUMBER ";
        List <Aviary> list_aviary = jdbcTemplateObject.query(sql, new AviaryMapper());
        return list_aviary;
    }
    public Integer getCountAnimals(String key_animals){
        String sql = "select count(*) from TBL_AVIARY where ARRAY_ANIMALS=\"" +key_animals + "\"";
        Integer count = jdbcTemplateObject.queryForObject(sql,Integer.class);
        return count;
    }
}
