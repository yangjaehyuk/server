package com.parkro.server.domain.receipt.service;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;

public interface ReceiptService {

  // 영수증 등록
  Integer addReceipt(PostReceiptReq req);

  // 영수증 정보 조회
  GetReceiptRes findReceipt(Integer receiptId);

  // 영수증 상태 수정
  Integer modifyReceiptStatus(Integer receiptId);
}