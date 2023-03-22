package com.example.shoppingmall.repository.product;

import com.example.shoppingmall.data.dto.queryselect.SelectIDQuery;
import com.example.shoppingmall.data.dto.queryselect.SelectProductStockQuery;
import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.response.ResponseProductPurchase;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.repository.OrderByNull;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

import static com.example.shoppingmall.data.entity.QOrderProduct.orderProduct;
import static com.example.shoppingmall.data.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public BooleanExpression eqUsername(String username) {
        if (!StringUtils.hasText(username)){
            return null;
        }
        return product.user.username.eq(username);
    }

    @Override
    public List<SelectIDQuery> selectIDFromUsername(String username){
        BooleanExpression status = eqUsername(username);

        // BooleanExpression 이 모두 null 이라면 모든 쿼리를 불러오므로 조건처리
        if (status == null) {
            return null;
        }

        return queryFactory.select(Projections.fields(
                SelectIDQuery.class, product.id))
                .from(product)
                .where(status)
                .fetch();
    }

    @Override
    public BooleanExpression eqProductID(Long id){
        if (id == null) {
            return null;
        }
        return product.id.eq(id);
    }

    @Override
    public BooleanExpression eqProductIDList(List<Long> IDList){
        if (IDList == null) {
            return null;
        }
        return product.id.in(IDList);
    }

    @Override
    public void deleteProductIDList(List<Long> IDList){
        BooleanExpression status = eqProductIDList(IDList);

        if (status == null) {
            return;
        }

        queryFactory.update(product)
                .set(product.description, "deleted product")
                .set(product.stock, -100)
                .set(product.user.id, 1L)
                .where(status)
                .execute();
    }

    @Override
    public void deleteProductID(Long id){
        BooleanExpression status = eqProductID(id);

        if (status == null) {
            return;
        }

        queryFactory.update(product)
                .set(product.description, "deleted product")
                .set(product.stock, -100)
                .set(product.user.id, 1L)
                .where(status)
                .execute();
    }

    @Override
    public List<Product> findByIdList(List<Long> IDList) {
        BooleanExpression status = eqProductIDList(IDList);

        if (status == null) {
            return null;
        }

        return queryFactory.selectFrom(product)
                .where(status)
                .fetch();
    }

    @Override
    public BooleanExpression containKeyword(String keyword){
        if (keyword == null) {
            return null;
        }

        return product.name.contains(keyword);
    }

    @Override
    public BooleanExpression eqCategory(String category){
        if (category == null) {
            return null;
        }
        return product.category.eq(category);
    }



    @Override
    public Integer updateProductListStock(HashMap<Long, Integer> productMap) {
        // update 쿼리가 실패해서 0이 한번이라도 나왔을경우 sum은 0
        int sum = 1;

        for(Long product_id : productMap.keySet()) {
            sum *= queryFactory.update(product)
                    .set(product.stock, productMap.get(product_id))
                    .where(product.id.eq(product_id))
                    .execute();
        }

        return sum;
    }

    @Override
    public List<ChangeStockQuery> findRemoveByProductIDList(List<Long> IDList) {
        BooleanExpression status = eqProductIDList(IDList);

        if (status == null) {
            return null;
        }

        return  queryFactory.select(Projections.fields(ChangeStockQuery.class,
                        product.id.as("product_id"),
                        product.stock))
                .from(product)
                .where(status)
                .fetch();
    }

    @Override
    public SelectProductStockQuery findAddStockByProductID(Long product_id) {
        BooleanExpression status = eqProductID(product_id);

        if (status == null) {
            return null;
        }

        return  queryFactory.select(Projections.fields(SelectProductStockQuery.class,
                        Expressions.asNumber(product_id).as("product_id"),
                        product.stock.as("stock"),
                        product.user.id.as("user_id")))
                .from(product)
                .where(status)
                .fetchOne();
    }

    @Override
    public Integer updateProductStock(Long product_id, int after_stock) {

        return (int) queryFactory.update(product)
                .set(product.stock, after_stock)
                .where(product.id.eq(product_id))
                .execute();
    }

    @Override
    public Product findByFirstProduct() {
        return queryFactory.selectFrom(product).fetchFirst();
    }


    @Override
    public List<ResponseProductPurchase> findAllProductPurchase() {

        return queryFactory.select(Projections.fields(ResponseProductPurchase.class,
                        product.id,
                        product.name,
                        product.price,
                        product.favorite,
                        product.imgKey,
                        ExpressionUtils.as(
                                JPAExpressions.select(orderProduct.count.sum())
                                        .from(orderProduct)
                                        .groupBy(orderProduct.product.id)
                                        .orderBy(OrderByNull.DEFAULT)
                                        .where(orderProduct.product.eq(product)), "count"
                        )))
                .from(product)
                .where(product.stock.gt(0))
                .fetch();
    }

    @Override
    public List<ResponseProductPurchase> findSearchProductPurchase(String keyword) {
        BooleanExpression status = containKeyword(keyword);

        if (status == null) {
            return null;
        }

        return findProductPurchaseQuery(status);
    }

    @Override
    public List<ResponseProductPurchase> findCategoryProductPurchase(String category) {
        BooleanExpression status = eqCategory(category);

        if (status == null) {
            return null;
        }

        return findProductPurchaseQuery(status);
    }

    public List<ResponseProductPurchase> findProductPurchaseQuery(BooleanExpression status) {

        return queryFactory.select(Projections.fields(ResponseProductPurchase.class,
                        product.id,
                        product.name,
                        product.price,
                        product.favorite,
                        product.imgKey,
                        ExpressionUtils.as(
                                JPAExpressions.select(orderProduct.count.sum())
                                        .from(orderProduct)
                                        .groupBy(orderProduct.product.id)
                                        .orderBy(OrderByNull.DEFAULT)
                                        .where(orderProduct.product.eq(product)), "count"
                        )))
                .from(product)
                .where(status, product.stock.gt(0))
                .fetch();
    }
}
