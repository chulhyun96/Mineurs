package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private String receiverName;
    private String phoneNumber;
    private String request;
    private String siDo;
    private String siGunGu;
    private String gu;
    private String roadAddress;
    private String detailAddress;
    private String frontGatePassword;
    private boolean isDefault; // 기본배송지인지 아닌지
    private Long memberId;
    private Long deliveryRequestTemplateId;
    private String name;
}
