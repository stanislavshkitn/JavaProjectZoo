package mapper;

import models.Aviary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AviaryMapper implements RowMapper<Aviary> {
    public Aviary mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id =rs.getInt("ID");
        String location_name = rs.getString("LOCATION_NAME");
        int number =rs.getInt("NUMBER");
        String array_Animals = rs.getString("ARRAY_ANIMALS");
        String[] arrAnimals = array_Animals.split(" ");
        Aviary aviary = new Aviary(location_name, id, number, arrAnimals);
        return aviary;
    }
}
