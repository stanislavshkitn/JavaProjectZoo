package mapper;

import models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location(rs.getString("NAME"));
        return location;
    }
}
