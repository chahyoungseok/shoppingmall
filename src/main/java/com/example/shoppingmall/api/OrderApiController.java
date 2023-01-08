package com.example.shoppingmall.api;

import com.example.shoppingmall.data.dto.request.RequestOrder;
import com.example.shoppingmall.data.dto.response.ResponseOrder;
import com.example.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderApiController {
    @Autowired
    private OrderService orderService;

    /** 주문목록 조회 */
    @GetMapping("/user/orderlist")
    public ResponseEntity<List<ResponseOrder>> read_order(HttpServletRequest request){
        List<ResponseOrder> responseOrderList = orderService.read_order(request.getAttribute("username").toString());

        return (responseOrderList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseOrderList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /** 주문목록 추가 */
    @PostMapping("/user/create_order")
    public ResponseEntity<List<ResponseOrder>> read_order(HttpServletRequest request, @RequestBody RequestOrder requestOrder){
        requestOrder.setUsername(request.getAttribute("username").toString());
        List<ResponseOrder> responseOrderList = orderService.create_order(requestOrder);

        return (responseOrderList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(responseOrderList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
