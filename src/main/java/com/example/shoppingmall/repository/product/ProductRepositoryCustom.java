package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.example.shoppingmall.data.dto.queryselect.SelectProductStockQuery;
import com.example.shoppingmall.data.dto.response.ResponseProductPurchase;
import com.example.shoppingmall.data.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.HashMap;
import java.util.List;

public interface ProductRepositoryCustom {
    BooleanExpression eqUsername(String username);

    List<SelectIDQuery> selectIDFromUsername(String username);

    BooleanExpression eqProductIDList(List<Long> IDList);

    BooleanExpression eqProductID(Long id);

    void deleteProductIDList(List<Long> IDList);

    void deleteProductID(Long id);

    List<Product> findByIdList(List<Long> IDList);

    List<ResponseProductPurchase> findAllProductPurchase();

    List<ResponseProductPurchase> findSearchProductPurchase(String keyword);

    List<ResponseProductPurchase> findCategoryProductPurchase(String category);

    BooleanExpression containKeyword(String keyword);

    BooleanExpression eqCategory(String category);

    Integer updateProductListStock(HashMap<Long, Integer> productMap);

    List<ChangeStockQuery> findRemoveByProductIDList(List<Long> IDList);

    SelectProductStockQuery findAddStockByProductID(Long product_id);

    Integer updateProductStock(Long product_id, int after_stock);
}
