package jdbc;

import mapper.LocationMapper;
import models.Location;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class LocationJDBC {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void save(Location obj){
        String sql = "insert into TBL_LOCATION (NAME) values (?)";
        jdbcTemplateObject.update( sql, obj.getName());
    }

    public List<Location> getAll(){
        String sql = "select * from TBL_LOCATION";
        List <Location> locations = jdbcTemplateObject.query(sql, new LocationMapper());
        return locations;
    }

    public Object getObj(String pk){
        String sql = "select * from TBL_LOCATION where NAME = ?";
        try {
            Location location = jdbcTemplateObject.queryForObject(sql, new Object[]{pk}, new LocationMapper());
            return location;
        }catch (Exception e){
            return  null;
        }
    }

    public void delete(String pk){
        String sql = "delete from TBL_LOCATION where NAME = ?";
        jdbcTemplateObject.update(sql, pk);
    }
}
