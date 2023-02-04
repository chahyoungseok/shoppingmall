package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.*;
import com.example.shoppingmall.data.entity.Banner;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.banner.BannerRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BannerRepository bannerRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BannerRepository bannerRepository){
        this.productRepository = productRepository;
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<List<?>>  mainPageProductList() {
        // Dto -> Entity
        List<Product> productList = productRepository.findTop8ByOrderByIdDesc();
        List<Banner> bannerList = bannerRepository.findAll();

        // Entity -> Dto
        List<ResponseProductMain> responseProductList = new ArrayList<>();
        List<ResponseBanner> responseBannerList = new ArrayList<>();
        List<List<?>> returnList = new ArrayList<>();

        if (!productList.isEmpty() && !bannerList.isEmpty()) {
            for(Product product : productList){
                ResponseProductMain newDto = new ResponseProductMain();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());

                responseProductList.add(newDto);
            }
            for (Banner banner : bannerList) {
                ResponseBanner newDto = new ResponseBanner();
                newDto.setImgKey(banner.getImgKey());

                responseBannerList.add(newDto);
            }
            returnList.add(responseProductList);
            returnList.add(responseBannerList);
        }

        return returnList;
    }

    @Override
    public List<ResponseProductSummary> findByProductName(String keyword, String sort) {
        // Dto -> Entity
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findByNameContainingOrderByHitsDesc(keyword);
            case "date" -> productList = productRepository.findByNameContainingOrderByDateDesc(keyword);
            case "favorite"-> productList = productRepository.findByNameContainingOrderByFavoriteDesc(keyword);
            case "purchase"-> {
                List<ResponseProductPurchase> productPurchaseList = productRepository.findSearchProductPurchase(keyword);

                // 서브쿼리로 받아온 count 로 order by 문법이 적용안되므로 WAS 단에서 정렬수행
                Collections.sort(productPurchaseList);

                return productPurchaseList.stream().map(productPurchase -> new ResponseProductSummary(
                        productPurchase.getId(),
                        productPurchase.getName(),
                        productPurchase.getPrice(),
                        productPurchase.getFavorite(),
                        productPurchase.getImgKey()
                )).toList();
            }
        }

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()) {
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());
                newDto.setFavorite(product.getFavorite());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public List<ResponseProductSummary> findAllProduct(String sort) {
        // Dto -> Entity
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findAllByOrderByHitsDesc();
            case "date" -> productList = productRepository.findAllByOrderByDateDesc();
            case "favorite"-> productList = productRepository.findAllByOrderByFavoriteDesc();
            case "purchase"-> {
                List<ResponseProductPurchase> productPurchaseList = productRepository.findAllProductPurchase();

                // 서브쿼리로 받아온 count 로 order by 문법이 적용안되므로 WAS 단에서 정렬수행
                Collections.sort(productPurchaseList);

                return productPurchaseList.stream().map(productPurchase -> new ResponseProductSummary(
                        productPurchase.getId(),
                        productPurchase.getName(),
                        productPurchase.getPrice(),
                        productPurchase.getFavorite(),
                        productPurchase.getImgKey()
                )).toList();
            }
        }

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());
                newDto.setFavorite(product.getFavorite());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public List<ResponseProductSummary> findByCategory(String category, String sort) {
        // Dto -> Entity
        List<Product> productList = new ArrayList<>();
        switch (sort) {
            case "hits" -> productList = productRepository.findByCategoryOrderByHitsDesc(category);
            case "date" -> productList = productRepository.findByCategoryOrderByDateDesc(category);
            case "favorite"-> productList = productRepository.findByCategoryOrderByFavoriteDesc(category);
            case "purchase"-> {
                List<ResponseProductPurchase> productPurchaseList = productRepository.findCategoryProductPurchase(category);

                // 서브쿼리로 받아온 count 로 order by 문법이 적용안되므로 WAS 단에서 정렬수행
                Collections.sort(productPurchaseList);

                return productPurchaseList.stream().map(productPurchase -> new ResponseProductSummary(
                        productPurchase.getId(),
                        productPurchase.getName(),
                        productPurchase.getPrice(),
                        productPurchase.getFavorite(),
                        productPurchase.getImgKey()
                )).toList();
            }
        }

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());
                newDto.setFavorite(product.getFavorite());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public ResponseProduct findById(Long id) {
        // Dto -> Entity
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            return null;
        }

        // Entity -> Dto
        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.setId(product.getId());
        responseProduct.setName(product.getName());
        responseProduct.setPrice(product.getPrice());
        responseProduct.setCategory(product.getCategory());
        responseProduct.setDescription(product.getDescription());
        responseProduct.setSize(product.getSize());
        responseProduct.setFavorite(product.getFavorite());
        responseProduct.setImgKey(product.getImgKey());
        return responseProduct;
    }

    @Override
    public List<ResponseProductSummary> findByUsername(Long userId) {
        // Dto -> Entity
        List<Product> productList = productRepository.findByUserId(userId);

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());
                newDto.setFavorite(product.getFavorite());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public boolean CreateProduct(RequestProduct requestProduct, User user){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        Product product = Product.builder()
                .id(null)
                .name(requestProduct.getName())
                .price(requestProduct.getPrice())
                .category(requestProduct.getCategory())
                .description(requestProduct.getDescription())
                .size(requestProduct.getSize())
                .imgKey(requestProduct.getImgKey())
                .date(LocalDateTime.parse(requestProduct.getDate(), formatter))
                .hits(0)
                .favorite(0)
                .user(user)
                .build();

        Product createdProduct = productRepository.save(product);

        return !createdProduct.getName().isEmpty();
    }

    @Override
    public ResponseProduct editProduct(Long id, String username) {
        // Dto -> Entity
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return null;
        }

        // Entity -> Dto
        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if (product.getUser().getUsername().equals(username)) {
            ResponseProduct responseProduct = new ResponseProduct();
            responseProduct.setId(product.getId());
            responseProduct.setName(product.getName());
            responseProduct.setPrice(product.getPrice());
            responseProduct.setCategory(product.getCategory());
            responseProduct.setDescription(product.getDescription());
            responseProduct.setSize(product.getSize());
            responseProduct.setImgKey(product.getImgKey());
            return responseProduct;
        } else {
            return null;
        }
    }

    @Override
    public ResponseProduct updateProduct(RequestProductModify requestProductModify) {
        // Dto -> Entity
        Product product = productRepository.findById(requestProductModify.getId()).orElse(null);
        if(product == null) {
            return null;
        }

        ResponseProduct responseProduct = new ResponseProduct();

        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if(product.getUser().getUsername().equals(requestProductModify.getUsername())){
            product.updateProduct(
                    requestProductModify.getName(),
                    requestProductModify.getPrice(),
                    requestProductModify.getCategory(),
                    requestProductModify.getDescription(),
                    requestProductModify.getSize(),
                    requestProductModify.getImgKey()
            );
            Product modified_Product = productRepository.save(product);

            // Entity -> Dto
            responseProduct.setId(modified_Product.getId());
            responseProduct.setName(modified_Product.getName());
            responseProduct.setPrice(modified_Product.getPrice());
            responseProduct.setCategory(modified_Product.getCategory());
            responseProduct.setDescription(modified_Product.getDescription());
            responseProduct.setSize(modified_Product.getSize());
            responseProduct.setImgKey(modified_Product.getImgKey());
            return responseProduct;
        }else {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Long id, String username) {
        // Dto -> Entity
        Product product = productRepository.findById(id).orElse(null);

        if(product == null) {
            return false;
        }

        // Entity -> Dto
        // product 를 등록한 유저아이디와 받은 jwt 의 유저 아이디가 같은지 확인
        if (product.getUser().getUsername().equals(username)) {
            productRepository.delete(product);
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
}
