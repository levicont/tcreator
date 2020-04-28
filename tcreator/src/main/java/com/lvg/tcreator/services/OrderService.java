package com.lvg.tcreator.services;

import com.lvg.tcreator.models.Order;

public interface OrderService {
    Order loadFromFile(byte[] file);
}
