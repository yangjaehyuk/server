package com.parkro.server.domain.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
/**
 * 쿠폰 등록 클래스
 *
 * @author 양재혁
 * @since 2024.08.03
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  양재혁       최초 생성
 * </pre>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMemberCouponReq {
    private Long memberCouponId;
    private Long couponId;
    private Integer memberId;
    private String status;
    private Date createdDate;
    private Date endDate;
}
