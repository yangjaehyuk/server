package com.parkro.server.domain.receipt.service;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;

public interface ReceiptService {

  Integer addReceipt(PostReceiptReq req);
  GetReceiptRes findReceipt(Integer receiptId);
  Integer modifyReceiptStatus(Integer receiptId);
}