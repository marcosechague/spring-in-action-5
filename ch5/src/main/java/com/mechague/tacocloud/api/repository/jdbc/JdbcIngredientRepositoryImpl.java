package com.mechague.tacocloud.api.repository.jdbc;

import com.mechague.tacocloud.api.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepositoryImpl implements JdbcIngredientRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }

    //If no method reference is used, you must do the RowMapper like this:
    /*@Override
    public IngredientEntity findOne(String id) {
        return jdbc.queryForObject(
                "select id, name, type from IngredientEntity where id=?",
                new RowMapper<IngredientEntity>() {
                    public IngredientEntity mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        return new IngredientEntity(
                                rs.getString("id"),
                                rs.getString("name"),
                                IngredientEntity.Type.valueOf(rs.getString("type")));
                    };
                }, id);
    }*/

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
