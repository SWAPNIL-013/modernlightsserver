package com.modernlights.response;

import com.modernlights.dto.OrderHistory;
import com.modernlights.model.Cart;
import com.modernlights.model.Product;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
