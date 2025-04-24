package com.modernlights.service;


import com.modernlights.exception.WishlistNotFoundException;
import com.modernlights.model.Product;
import com.modernlights.model.User;
import com.modernlights.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);

    Wishlist getWishlistByUserId(User user);

    Wishlist addProductToWishlist(User user, Product product) throws WishlistNotFoundException;

}

