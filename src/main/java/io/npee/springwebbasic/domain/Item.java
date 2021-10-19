package io.npee.springwebbasic.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
// 글로벌 오류는 직접 작성하는게 낫다.
//@ScriptAssert(
//        lang = "javascript",
//        script="-this.price * _this.quantity >= 10000",
//        message = "총합이 10000원 넘게 입력해주세요")
public class Item {

    // 검증 마지막 - 등록/수정 검증 객체 분리
    // @NotNull(groups = UpdateCheck.class)
    private Long id;

    // @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    // @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    // @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    // @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    // @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    private Boolean open; //판매 여부
    private List<String> regions; //등록 지역
    private ItemType itemType; //상품 종류
    private String deliveryCode; //배송 방식

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
