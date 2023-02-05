package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.service.OrderService;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class OrderApiController {

    private final OrderService orderService;

    private final ProductService productService;

    @Autowired
    public OrderApiController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    /** 주문목록 조회 */
    @GetMapping("/user/order")
    public ResponseEntity<List<ResponseOrder>> read_order(HttpServletRequest request){
        List<ResponseOrder> responseOrderList = orderService.read_order((User) request.getAttribute("user"));

        return (responseOrderList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseOrderList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 주문목록 추가 */
    @Transactional
    @PostMapping("/user/order")
    public ResponseEntity<List<ResponseOrder>> create_order(HttpServletRequest request, @RequestBody RequestOrder requestOrder){
        List<ResponseOrder> responseOrderList = orderService.create_order((User) request.getAttribute("user"), requestOrder);
        if (responseOrderList == null) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }

        Boolean state = productService.purchaseProduct(requestOrder);
        return (state) ?
                ResponseEntity.status(HttpStatus.OK).body(responseOrderList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
