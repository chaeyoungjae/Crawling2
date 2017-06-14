package com.ctx.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by chaester on 2017-05-10.
 */
@Entity
@Table(name="item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;
    @ManyToOne
    private Item    master;                     // 대표상품
    @OneToMany(mappedBy = "master")
    private List<Item> children;                // 자식 상품
    private String  prodCd;                     // 상품코드
    private String  itemName;                   // 실제 제품명
    @OneToMany(mappedBy = "item")
    private List<ItemPrice> itemPriceList;      // 가격 정보
    private String  modelNumber;                // 모델명
    private String  maker;                      // 제조사
    private String  orgin;                      // 원산지
    private String  detailInfo;                 // 상세정보
    private Integer outUnit;                    // 출고단위
    private Integer unitNm;                     // 단위명
    private Integer inBox;                      // 인박스
    private Integer outBox;                     // 아웃박스
    private String  barcode;                    // 바코드
    private String  topImageUrl;                // 대표 이미지
    @CollectionTable
    private Collection<String> imageUrl;        // 나머지 이미지
    @ManyToOne
    private Category category;                  // 카테고리
    @ManyToOne
    private Brand   brand;                      // 브랜드
    @OneToMany(mappedBy = "master")
    private List<MetaInfo> metaInfos;

    private Integer hits;                       // 조회 수
    private String  useYn;                      // 노출여부
    private String  soldoutYn;                  // 품절여부
    private String  delYn;                      // 삭제여부
    private Date    regDate;                    // 수정일

    public List<ItemPrice> getItemPriceList() {
        return itemPriceList;
    }

    public void setItemPriceList(List<ItemPrice> itemPriceList) {
        this.itemPriceList = itemPriceList;
    }
}
