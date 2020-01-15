package com.mechague.tacocloud.api.repository.jdbc;

import com.mechague.tacocloud.api.domain.Order;

public interface JdbcOrderRepository {
    Order save(Order order);
}
