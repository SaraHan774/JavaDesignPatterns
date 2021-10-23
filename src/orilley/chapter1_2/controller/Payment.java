package orilley.chapter1_2.controller;

interface Payment {
    public void pay(int amount);
}

// 여러가지 형태의 payment 를 생서할 수 있는 유연성이 생긴다.