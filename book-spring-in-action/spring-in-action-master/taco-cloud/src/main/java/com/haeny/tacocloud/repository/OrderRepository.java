package com.haeny.tacocloud.repository;

import com.haeny.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
