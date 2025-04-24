package com.modernlights.service;

import com.modernlights.exception.ProductException;
import com.modernlights.model.Cart;
import com.modernlights.model.CartItem;
import com.modernlights.model.Product;
import com.modernlights.model.User;

public interface CartService {
	
	public CartItem addCartItem(User user,
								Product product,
								String size,
								int quantity) throws ProductException;
	
	public Cart findUserCart(User user);

}
