package June.Spring.demo.clientrepository;


import June.Spring.demo.clientdomain.clientmemeber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplatememberrepository implements memberrepository{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplatememberrepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public clientmemeber save(clientmemeber member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getClientname());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setClientid(key.longValue());
        return member;

    }

    @Override
    public Optional<clientmemeber> findByID(Long id) {
        List<clientmemeber> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);

        return result.stream().findAny();
    }

    @Override
    public Optional<clientmemeber> findByName(String name) {
        List<clientmemeber> result = jdbcTemplate.query("select * from member where name = ?" ,
                memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<clientmemeber> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<clientmemeber> memberRowMapper() {
        return (rs, rowNum) -> {
            clientmemeber member = new clientmemeber();
            member.setClientid(rs.getLong("id"));
            member.setClientname(rs.getString("name"));
            return member;
        };
    }



}
