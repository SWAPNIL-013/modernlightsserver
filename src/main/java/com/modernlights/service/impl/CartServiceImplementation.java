package com.modernlights.service.impl;

import com.modernlights.exception.ProductException;

import com.modernlights.model.Cart;
import com.modernlights.model.CartItem;
import com.modernlights.model.Product;
import com.modernlights.model.User;
import com.modernlights.repository.CartItemRepository;
import com.modernlights.repository.CartRepository;
import com.modernlights.service.CartService;
import com.modernlights.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductService productService;


//	public Cart findUserCart(User user) {
//		Cart cart =	cartRepository.findByUserId(user.getId());
//
//		int totalPrice=0;
//		int totalDiscountedPrice=0;
//		int totalItem=0;
//		for(CartItem cartsItem : cart.getCartItems()) {
//			totalPrice+=cartsItem.getMrpPrice();
//			totalDiscountedPrice+=cartsItem.getSellingPrice();
//			totalItem+=cartsItem.getQuantity();
//		}
//
//		cart.setTotalMrpPrice(totalPrice);
//		cart.setTotalItem(cart.getCartItems().size());
//		cart.setTotalSellingPrice(totalDiscountedPrice-cart.getCouponPrice());
//		cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
//		cart.setTotalItem(totalItem);
//
//		return cartRepository.save(cart);
//
//	}
public Cart findUserCart(User user) {
	Cart cart = cartRepository.findByUserId(user.getId());

	// If no cart is found, create a new cart for the user
	if (cart == null) {
		cart = new Cart();
		cart.setUser(user);  // Associate the user with the new cart
		cart = cartRepository.save(cart); // Save the new cart to the database
	}

	int totalPrice = 0;
	int totalDiscountedPrice = 0;
	int totalItem = 0;

	for (CartItem cartItem : cart.getCartItems()) {
		totalPrice += cartItem.getMrpPrice();
		totalDiscountedPrice += cartItem.getSellingPrice();
		totalItem += cartItem.getQuantity();
	}

	cart.setTotalMrpPrice(totalPrice);
	cart.setTotalItem(cart.getCartItems().size());
	cart.setTotalSellingPrice(totalDiscountedPrice - cart.getCouponPrice());
	cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
	cart.setTotalItem(totalItem);

	return cartRepository.save(cart);
}


	public static int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}

//	@Override
//	public CartItem addCartItem(User user,
//								Product product,
//								String size,
//								int quantity
//								) throws ProductException {
//		Cart cart=findUserCart(user);
//
//		CartItem isPresent=cartItemRepository.findByCartAndProductAndSize(
//				cart, product, size);
//
//		if(isPresent == null) {
//			CartItem cartItem = new CartItem();
//			cartItem.setProduct(product);
//
//			cartItem.setQuantity(quantity);
//			cartItem.setUserId(user.getId());
//
//			int totalPrice=quantity*product.getSellingPrice();
//			cartItem.setSellingPrice(totalPrice);
//			cartItem.setMrpPrice(quantity*product.getMrpPrice());
//			cartItem.setSize(size);
//
//			cart.getCartItems().add(cartItem);
//			cartItem.setCart(cart);
//
//            return cartItemRepository.save(cartItem);
//		}
//
//		return isPresent;
//	}
@Override
public CartItem addCartItem(User user, Product product, String size, int quantity) throws ProductException {
	Cart cart = findUserCart(user);

	// Handle the case where no cart is found for the user
	if (cart == null) {
		cart = new Cart();
		cart.setUser(user);  // Associate the cart with the user
		cart = cartRepository.save(cart);  // Save the new cart to the database
	}

	CartItem existingItem = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

	if (existingItem == null) {
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		cartItem.setUserId(user.getId());

		int totalPrice = quantity * product.getSellingPrice();
		cartItem.setSellingPrice(totalPrice);
		cartItem.setMrpPrice(quantity * product.getMrpPrice());
		cartItem.setSize(size);

		cart.getCartItems().add(cartItem);
		cartItem.setCart(cart);

		return cartItemRepository.save(cartItem);
	}

	return existingItem;
}


}
