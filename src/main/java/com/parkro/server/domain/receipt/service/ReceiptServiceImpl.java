package com.parkro.server.domain.receipt.service;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.mapper.ReceiptMapper;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;
import com.parkro.server.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.parkro.server.exception.ErrorCode.RECEIPT_NOT_FOUND;
import static com.parkro.server.exception.ErrorCode.USED_RECEIPT;

/**
 * 영수증 관련 로직
 *
 * @author 김지수
 * @since 2024.07.25
 *
 * <pre>
 * 수정일자       수정자        수정내용
 * ------------ --------    ---------------------------
 * 2024.07.25   김지수      최초 생성
 * 2024.07.25   김지수      영수증 등록 API
 * 2024.07.25   김지수      영수증 조회 API
 * 2024.08.03   김지수      영수증 상태 변경 API
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ReceiptServiceImpl implements ReceiptService {

  private final ReceiptMapper receiptMapper;

  /**
   * 영수증 등록
   * @param req
   * @return 업데이트된 row 수
   */
  @Override
  public Integer addReceipt(PostReceiptReq req) {
    receiptMapper.insertReceipt(req);
    log.info("[receipt service] addReceipt: " + req.getReceiptId());
    return req.getReceiptId();
  }

  /**
   * 영수증 조회
   * @param receiptId
   * @return receipt_id로 조회한 금액 정보, 상태 등을 포함한 영수증 정보{@link GetReceiptRes}
   */
  @Override
  public GetReceiptRes findReceipt(Integer receiptId) {
    GetReceiptRes result = receiptMapper.selectReceipt(receiptId);
    if (result == null) throw new CustomException(RECEIPT_NOT_FOUND);
    if (result.getStatus().equals("USED")) throw new CustomException(USED_RECEIPT);
    return result;
  }

  /**
   * 영수증 상태 변경 (ACTIVE -> USED)
   * @param receiptId
   * @return 업데이트된 row 수
   */
  @Override
  @Transactional
  public Integer modifyReceiptStatus(Integer receiptId) {
    return receiptMapper.updateReceiptStatus(receiptId);
  }
}
