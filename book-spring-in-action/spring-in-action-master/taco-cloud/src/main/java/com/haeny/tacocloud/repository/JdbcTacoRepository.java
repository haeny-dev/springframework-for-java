package com.haeny.tacocloud.repository;

import com.haeny.tacocloud.domain.Ingredient;
import com.haeny.tacocloud.domain.Taco;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcTacoRepository implements TacoRepository{

    private final JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());

        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
            "insert into Taco (name, createdAt) values (?, ?)",
            Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
            List.of(taco.getName(), taco.getCreatedAt())
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
            "insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
            tacoId, ingredient.getId()
        );
    }
}
