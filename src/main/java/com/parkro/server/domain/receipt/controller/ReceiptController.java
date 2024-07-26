package com.parkro.server.domain.receipt.controller;

import com.parkro.server.domain.receipt.dto.PostReceiptReq;
import com.parkro.server.domain.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.parkro.server.domain.receipt.dto.GetReceiptRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 영수증
 *
 * @author 김지수
 * @since 2024.07.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  김지수      최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
public class ReceiptController {

  private final ReceiptService receiptService;

  @PostMapping
  public ResponseEntity<Integer> receiptSave(@RequestBody PostReceiptReq req) {
    return ResponseEntity.ok(receiptService.addReceipt(req));
  }

  @GetMapping("/{receiptId}")
  public ResponseEntity<GetReceiptRes> receiptDetails(@PathVariable("receiptId") Integer receiptId) {
    return ResponseEntity.ok(receiptService.findReceipt(receiptId));
  }
}
