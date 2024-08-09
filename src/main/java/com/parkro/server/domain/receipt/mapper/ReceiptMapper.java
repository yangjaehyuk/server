package com.parkro.server.domain.receipt.mapper;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiptMapper {

  // 영수증 데이터 삽입
  int insertReceipt(PostReceiptReq req);

  // 영수증 데이터 조회
  GetReceiptRes selectReceipt(Integer receiptId);

  // 영수증 상태 수정
  // e.g.) ACTIVE -> USED
  Integer updateReceiptStatus(Integer receiptId);
}
