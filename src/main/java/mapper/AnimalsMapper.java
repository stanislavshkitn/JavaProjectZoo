package mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Animal;
import org.springframework.jdbc.core.RowMapper;

public class AnimalsMapper implements RowMapper<Animal> {
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animal = new Animal("",false);
        animal.setName(rs.getString("NAME"));
        animal.setIs_predator(rs.getBoolean("IS_PREDATOR"));
        return animal;
    }
}
