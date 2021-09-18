package com.desy.demo.service;

import com.desy.demo.data.payloads.request.ItemRequest;
import com.desy.demo.data.payloads.response.MessageResponse;

public interface ItemService {

    MessageResponse createItem(ItemRequest itemRequest);
}
