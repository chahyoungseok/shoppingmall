package com.example.shoppingmall.repository.favorite;

import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

public interface FavoriteRepositoryCustom {

    List<ResponseProductSummary> findAllFavorite(String username);

    BooleanExpression eqUsername(String username);
}
