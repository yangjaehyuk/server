package com.parkro.server.domain.receipt.mapper;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiptMapper {

  int insertReceipt(PostReceiptReq req);
  GetReceiptRes selectReceipt(Integer receiptId);
  Integer updateReceiptStatus(Integer receiptId);
}
