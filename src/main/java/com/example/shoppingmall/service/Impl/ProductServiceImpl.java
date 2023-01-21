package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.request.RequestProduct;
import com.example.shoppingmall.data.dto.request.RequestProductModify;
import com.example.shoppingmall.data.dto.response.ResponseBanner;
import com.example.shoppingmall.data.dto.response.ResponseProduct;
import com.example.shoppingmall.data.dto.response.ResponseProductSummary;
import com.example.shoppingmall.data.entity.Banner;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.banner.BannerRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Product> productList = productRepository.findTop2ByOrderByIdDesc();
        List<Banner> bannerList = bannerRepository.findAll();

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        List<ResponseBanner> responseBannerList = new ArrayList<>();
        List<List<?>> returnList = new ArrayList<>();

        if (!productList.isEmpty() && !bannerList.isEmpty()) {
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
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
    public List<ResponseProductSummary> findByProductName(String keyword) {
        // Dto -> Entity
        List<Product> productList = productRepository.findByNameContaining(keyword);

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()) {
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public List<ResponseProductSummary> findAllProduct() {
        // Dto -> Entity
        List<Product> productList = productRepository.findAllFetchJoin();

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public List<ResponseProductSummary> findByCategory(String category) {
        // Dto -> Entity
        List<Product> productList = productRepository.findByCategory(category);

        // Entity -> Dto
        List<ResponseProductSummary> responseProductList = new ArrayList<>();
        if (!productList.isEmpty()){
            for(Product product : productList){
                ResponseProductSummary newDto = new ResponseProductSummary();
                newDto.setId(product.getId());
                newDto.setName(product.getName());
                newDto.setPrice(product.getPrice());
                newDto.setImgKey(product.getImgKey());

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

                responseProductList.add(newDto);
            }
        }

        return responseProductList;
    }

    @Override
    public boolean CreateProduct(RequestProduct requestProduct, User user){
        String[] parsedSize = requestProduct.getSize().split(",");

        // Dto -> Entity
        List<Product> productList = new ArrayList<>();
        for(String size : parsedSize){
            Product product = new Product();
            product.setName(requestProduct.getName());
            product.setPrice(requestProduct.getPrice());
            product.setCategory(requestProduct.getCategory());
            product.setDescription(requestProduct.getDescription());
            product.setImgKey(requestProduct.getImgKey());
            product.setUser(user);
            product.setSize(size);

            productList.add(product);
        }

        List<Product> createdProductList = productRepository.saveAll(productList);

        return createdProductList.size() == parsedSize.length;
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
            product.setName(requestProductModify.getName());
            product.setPrice(requestProductModify.getPrice());
            product.setCategory(requestProductModify.getCategory());
            product.setDescription(requestProductModify.getDescription());
            product.setSize(requestProductModify.getSize());
            product.setImgKey(requestProductModify.getImgKey());
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

}
