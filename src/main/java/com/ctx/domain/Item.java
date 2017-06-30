package com.ctx.domain;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chaester on 2017-05-10.
 */
@Entity
@Table(name="item")
public class Item implements Serializable {
    @Id
    private Long  id;
    @ManyToOne
    private Item    master;                     // 대표상품
    @OneToMany(mappedBy = "master")
    private List<Item> children;                // 자식 상품
    private String  prodCd;                     // 상품코드
    private String  itemName;                   // 실제 제품명
    private String  modelNumber;                // 모델명
    private String  maker;                      // 제조사
    private String  orgin;                      // 원산지
    private String  standard;                   // 규격
    private String  detailInfo;                 // 상세정보
    private Double  outUnit;                    // 출고단위
    private String  unitNm;                     // 단위명
    private Double  inBox;                      // 인박스
    private Double  outBox;                     // 아웃박스
    private String  barcode;                    // 바코드
    private String  topImageUrl;                // 대표 이미지
    @ElementCollection
    @CollectionTable(
            name = "T_IMAGE_URL",
            joinColumns = @JoinColumn(name="id", referencedColumnName = "id")
    )
    private List<String> imageUrl = new ArrayList<>(); // 나머지 이미지
    @ManyToOne
    private Category category;                  // 카테고리
    @ManyToOne
    private Brand   brand;                      // 브랜드
    private Integer hits;                       // 조회 수
    private String  useYn;                      // 노출여부
    private String  soldoutYn;                  // 품절여부
    private String  delYn;                      // 삭제여부
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date    regDate;                    // 수정일
    @OneToOne
    private ItemDelivery itemDelivery;          // 배송


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getMaster() {
        return master;
    }

    public void setMaster(Item master) {
        this.master = master;
    }

    public String getProdCd() {
        return prodCd;
    }

    public void setProdCd(String prodCd) {
        this.prodCd = prodCd;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getOrgin() {
        return orgin;
    }

    public void setOrgin(String orgin) {
        this.orgin = orgin;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public Double getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(Double outUnit) {
        this.outUnit = outUnit;
    }

    public Double getInBox() {
        return inBox;
    }

    public void setInBox(Double inBox) {
        this.inBox = inBox;
    }

    public Double getOutBox() {
        return outBox;
    }

    public void setOutBox(Double outBox) {
        this.outBox = outBox;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public void setUnitNm(String unitNm) {
        this.unitNm = unitNm;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTopImageUrl() {
        return topImageUrl;
    }

    public void setTopImageUrl(String topImageUrl) {
        this.topImageUrl = topImageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getSoldoutYn() {
        return soldoutYn;
    }

    public void setSoldoutYn(String soldoutYn) {
        this.soldoutYn = soldoutYn;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public ItemDelivery getItemDelivery() {
        return itemDelivery;
    }

    public void setItemDelivery(ItemDelivery itemDelivery) {
        this.itemDelivery = itemDelivery;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }
}
