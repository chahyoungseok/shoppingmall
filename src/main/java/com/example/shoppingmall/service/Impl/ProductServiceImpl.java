package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.ChangeStockQuery;
import com.example.shoppingmall.data.dto.queryselect.QueryOrderProduct;
import com.example.shoppingmall.data.dto.queryselect.SelectProductStockQuery;
import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.*;
import com.example.shoppingmall.data.entity.Banner;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.banner.BannerRepository;
import com.example.shoppingmall.repository.favorite.FavoriteRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;
    private final BannerRepository bannerRepository;

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BannerRepository bannerRepository, FavoriteRepository favoriteRepository){
        this.productRepository = productRepository;
        this.bannerRepository = bannerRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<List<?>> mainPageProductList() {
        List<Product> productList = productRepository.findTop8ByStockGreaterThanOrderByFavoriteDesc(0);
        List<Banner> bannerList = bannerRepository.findAll();

        return entityToDtoMainPageList(productList, bannerList);
    }

    @Override
    public List<ResponseProductSummary> findByProductName(String keyword, String sort) {
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findByNameContainingAndStockGreaterThanOrderByHitsDesc(keyword, 0);
            case "date" -> productList = productRepository.findByNameContainingAndStockGreaterThanOrderByDateDesc(keyword, 0);
            case "favorite"-> productList = productRepository.findByNameContainingAndStockGreaterThanOrderByFavoriteDesc(keyword, 0);
            case "purchase" -> {
                return purchaseSort(productRepository.findSearchProductPurchase(keyword));
            }
        }

        return entityToDtoResponseProductSummary(productList);
    }

    @Override
    public List<ResponseProductSummary> findAllProduct(String sort) {
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findByStockGreaterThanOrderByHitsDesc(0);
            case "date" -> productList = productRepository.findByStockGreaterThanOrderByDateDesc(0);
            case "favorite"-> productList = productRepository.findByStockGreaterThanOrderByFavoriteDesc(0);
            case "purchase" -> {
                return purchaseSort(productRepository.findAllProductPurchase());
            }
        }

        return entityToDtoResponseProductSummary(productList);
    }

    @Override
    public List<ResponseProductSummary> findByCategory(String category, String sort) {
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findByCategoryAndStockGreaterThanOrderByHitsDesc(category, 0);
            case "date" -> productList = productRepository.findByCategoryAndStockGreaterThanOrderByDateDesc(category, 0);
            case "favorite"-> productList = productRepository.findByCategoryAndStockGreaterThanOrderByFavoriteDesc(category, 0);
            case "purchase" -> {
                return purchaseSort(productRepository.findCategoryProductPurchase(category));
            }
        }

        return entityToDtoResponseProductSummary(productList);
    }

    @Override
    public ResponseProductDetails findById(User user, Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            return null;
        }

        Boolean status = false;
        if(user != null) {
            status = favoriteRepository.existUserProductByFavorite(user.getId(), id);
        }

        return ResponseProductDetails.builder().product(product).status(status).build();
    }

    @Override
    public List<ResponseProductSummary> findProductByUsername(Long userId) {
        List<Product> productList = productRepository.findByUserId(userId);

        return entityToDtoResponseProductSummary(productList);
    }

    @Override
    public boolean CreateProduct(RequestProduct requestProduct, User user){
        Product product = requestProduct.toEntity(user);

        Product createdProduct = productRepository.save(product);

        return !createdProduct.getName().isEmpty();
    }

    @Override
    @Transactional
    public ResponseProduct editProduct(Long id, User user) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return null;
        }

        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if (product.getUser().getUsername().equals(user.getUsername())) {
            return ResponseProduct.builder().product(product).build();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseProduct updateProduct(RequestProductModify requestProductModify) {
        Product product = productRepository.findById(requestProductModify.getId()).orElse(null);
        if(product == null) {
            return null;
        }

        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if(product.getUser().getUsername().equals(requestProductModify.getUsername())){
            requestProductModify.toEntity(product);
            Product modified_Product = productRepository.save(product);

            return ResponseProduct.builder().product(modified_Product).build();
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteProduct(Long id, User user) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return false;
        }

        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if (product.getUser().getUsername().equals(user.getUsername())) {
            productRepository.deleteProductID(product.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void increaseHits(Long id) {
        productRepository.increaseHits(id);
    }

    @Override
    public void increaseFavorite(Long id) {
        productRepository.increaseFavorite(id);
    }

    @Override
    public void decreaseFavorite(Long id) {
        productRepository.decreaseFavorite(id);
    }

    @Override
    public Boolean purchaseProduct(RequestOrder requestOrder) {
        int value = 0;
        List<ChangeStockQuery> changeStockList =
                productRepository.findRemoveByProductIDList(requestOrder.getQueryOrderProductList().stream().map(QueryOrderProduct::getProduct_id).toList());
        if(changeStockList == null){
            return null;
        }

        HashMap<Long, Integer> productMap = new HashMap<>();
        for(ChangeStockQuery changeStockQuery : changeStockList){
            productMap.put(changeStockQuery.getProduct_id(), changeStockQuery.getStock());
        }

        for(QueryOrderProduct queryOrderProduct : requestOrder.getQueryOrderProductList()){
            value = productMap.get(queryOrderProduct.getProduct_id()) - queryOrderProduct.getCount();

            if(value < 0) {
                return false;
            }

            productMap.put(queryOrderProduct.getProduct_id(), value);
        }

        return productRepository.updateProductListStock(productMap) != 0;
    }

    @Override
    @Transactional
    public Boolean stockUpProduct(User user, ChangeStockQuery changeStockQuery) {
        Long register_id = user.getId();

        SelectProductStockQuery selectProductStockQuery =
                productRepository.findAddStockByProductID(changeStockQuery.getProduct_id());

        if(selectProductStockQuery == null || selectProductStockQuery.getUser_id() != register_id){
            return false;
        }

        int after_stock = selectProductStockQuery.getStock() + changeStockQuery.getStock();
        if(after_stock < 0){
            return false;
        }

        return productRepository.updateProductStock(changeStockQuery.getProduct_id(), after_stock) != 0;
    }

    public List<ResponseProductSummary> purchaseSort(List<ResponseProductPurchase> productPurchaseList) {
        Collections.sort(productPurchaseList);

        return productPurchaseList.stream().map(productPurchase -> ResponseProductSummary
                .dtoBuilder()
                .responseProductPurchase(productPurchase)
                .dtoBuild()).toList();
    }

    /** Entity to Dto */
    public List<ResponseProductSummary> entityToDtoResponseProductSummary(List<Product> productList) {
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                responseProductList.add(ResponseProductSummary.builder().product(product).build());
            }
        }

        return responseProductList;
    }

    /** Entity to Dto */
    public List<List<?>> entityToDtoMainPageList(List<Product> productList, List<Banner> bannerList) {
        List<ResponseProductMain> responseProductList = new ArrayList<>();
        List<ResponseBanner> responseBannerList = new ArrayList<>();
        List<List<?>> returnList = new ArrayList<>();

        if (!productList.isEmpty() && !bannerList.isEmpty()) {
            for(Product product : productList){
                responseProductList.add(ResponseProductMain.builder().product(product).build());
            }
            for (Banner banner : bannerList) {
                responseBannerList.add(ResponseBanner.builder().banner(banner).build());
            }
            returnList.add(responseProductList);
            returnList.add(responseBannerList);
        }

        return returnList;
    }
}
