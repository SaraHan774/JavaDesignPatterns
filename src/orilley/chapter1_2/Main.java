package orilley.chapter1_2;

import orilley.chapter1_2.controller.CreditCardAlgorithm;
import orilley.chapter1_2.controller.PayPalAlgorithm;
import orilley.chapter1_2.controller.ShoppingCart;
import orilley.chapter1_2.model.Product;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        Product pants = new Product("123", 3000);
        Product shirts = new Product("124", 2000);

        shoppingCart.addProduct(pants);
        shoppingCart.addProduct(shirts);

        // Cart 에다가 product 를 담고 나서 지불 방법을 선택한다.
        // payment decisions
        shoppingCart.pay(new PayPalAlgorithm("sarahan774@gmail.com", "password123"));
        shoppingCart.pay(new CreditCardAlgorithm("sara", "1233123312331233"));
    }
}
