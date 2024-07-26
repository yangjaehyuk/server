package com.parkro.server.domain.receipt.service;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.mapper.ReceiptMapper;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.parkro.server.exception.ErrorCode.RECEIPT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReceiptServiceImpl implements ReceiptService {

  private final ReceiptMapper receiptMapper;

  @Override
  public Integer addReceipt(PostReceiptReq req) {
    receiptMapper.insertReceipt(req);
    log.info("[receipt service] addReceipt: " + req.getReceiptId());
    return req.getReceiptId();
  }

  @Override
  public GetReceiptRes findReceipt(Integer receiptId) {
    GetReceiptRes result = receiptMapper.selectReceipt(receiptId);
    if (result == null) throw new CustomException(RECEIPT_NOT_FOUND);
    return result;
  }
}
