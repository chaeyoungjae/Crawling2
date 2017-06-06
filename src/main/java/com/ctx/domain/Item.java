package com.ctx.domain;
import javax.persistence.*;

/**
 * Created by chaester on 2017-05-10.
 */
@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name="item")
public class Item {
    @Id
    @Column(name="item_id")
    private String  id;
    @ManyToOne
    @JoinColumn(name="item_master_id")
    private ItemMaster master;                  // 대표 상품군
    private String  prod_cd;                    // 상품코드
    private String  pname;                      // 실제 제품명
    private String  nickName;                   // 변경 제품명
    private Integer sle_pc;                     // 공급 가격
    private Integer price_pc;                   // 판매 가격
    private Integer price_mobile;               // 모바일 판매 가격
    private Integer normal_price;               // 할인전 가격
    private String  image_link;                 // 대표 이미지
    private String  add_image_link;             // 구분자 |
    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;                  // 카테고리
    private String  naver_category;             // 네이버카테고리
    private String  naver_product_id;           // 가격비고 페이지 ID
    private String  condition_h;                // 상품상태 (신상품/중고/리퍼/전시/반품/스크래치)
    private String  import_flag;                // 해외구매대행 여부
    private String  paralel_import;             // 병행수입 여부
    private String  order_made;                 // 주문제작 상품여부
    private String  product_flag;               // 판매방식 구분 (도매/렌탈/대여/할부/예약판매/구매대행)
    private String  adult;                      // 미성년자 구매불가 상품여부
    private String  goods_type;                 // 상품구분
    private String  barcode;                    // 바코드
    private String  manufacture_define_number;  // 제품코드
    private String  model_number;               // 모델명
    private String  brand;                      // 브랜드
    private String  maker;                      // 제조사
    private String  orgin;                      // 원산지
    private String  card_event;                 // 카드명/카드할인가격
    private String  event_words;                // 이벤트
    private String  coupon;                     // 일반/제휴쿠폰
    private String  partner_coupon_download;    // 쿠폰다운로드필요여부
    private String  interest_free_event;        // 카드 무이자 할부 정보
    private String  point;                      // 포인트
    private String  installation_costs;         // 별도 설치비 유무
    private String  search_tag;                 // 검색태그
    private String  group_id;                   // GroupID
    private String  vendor_id;                  // 제휴사 상품ID
    private String  coordi_id;                  // 코디상품 ID
    private Integer minimun_purchase_quanitity; // 최소구매수량
    private Integer review_count;               // 상품평 수
    private Integer shipping;                   // 배송료
    private String  delivery_grade;             // 차등배송비 여부
    private String  delivery_detail;            // 차등배송비 내용
    private String  attribute;                  // 상품속성
    private String  option_detail;              // 구매옵션
    private String  age_group;                  // 주이용고객층
    private String  gender;                     // 성별
    private String  use_yn;                     // 노출여부
    private String  soldout_yn;                 // 품절여부
/*
    private List<Item> relationItem;            // 관련상품
    private List<MetaInfo> metaInfos;           // 메타정보
*/

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (this.category != null) this.category.getItems().remove(this);
        this.category = category;
        category.getItems().add(this);
    }

    public void convert(String[] aItem) {
        if ( aItem.length > 0
                && Integer.parseInt(aItem[12]) > 10
                && Integer.parseInt(aItem[13]) > 10
                && Integer.parseInt(aItem[14]) > 10
        ) {
            this.id = aItem[0];
            this.prod_cd      = aItem[1].replace("-", "");
            this.use_yn       = aItem[2];
            this.soldout_yn   = aItem[3];
            this.brand        = aItem[5];
            this.maker        = aItem[5];
            this.pname        = aItem[6];
            this.model_number = aItem[7];
            this.image_link   = aItem[8];
            this.price_pc     = Integer.parseInt(aItem[12]);
            this.price_mobile = Integer.parseInt(aItem[12]);
            this.normal_price = Integer.parseInt(aItem[13]);
            this.sle_pc       = Integer.parseInt(aItem[14]);
            this.minimun_purchase_quanitity = Integer.parseInt(aItem[18]);
            this.manufacture_define_number  = aItem[1].replace("-", "");
            if ( aItem[23] != null && aItem[23].equals("Y") ) {
                this.delivery_grade  = "Y";
                this.delivery_detail = "화물착불";
            }
            if ( aItem[26] != null && !aItem[26].equals("") ) {
                String[] Meta = aItem[26].split("|");
                for ( String data : Meta ) {
                    MetaInfo metaInfo = new MetaInfo();
                    String[] datas = data.split("_");
//                    metaInfo.setId(datas[0]);
                    metaInfo.setName(datas[1]);
//                    this.metaInfos.add(metaInfo);
                }
            }
            add_image_link = aItem[27];
            if ( aItem[29] != null && !aItem[29].equals("") ) {
                String[] cates = aItem[29].split("_");
                this.category.setCategoryL(cates[0]);
                this.category.setCategoryM(cates[1]);
                this.category.setCategoryS(cates[2]);
                this.category.setCategorySS(cates[3]);
            }
            this.orgin = aItem[30];
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemMaster getMaster() {
        return master;
    }

    public void setMaster(ItemMaster master) {
        this.master = master;
    }

    public String getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(String prod_cd) {
        this.prod_cd = prod_cd;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSle_pc() {
        return sle_pc;
    }

    public void setSle_pc(Integer sle_pc) {
        this.sle_pc = sle_pc;
    }

    public Integer getPrice_pc() {
        return price_pc;
    }

    public void setPrice_pc(Integer price_pc) {
        this.price_pc = price_pc;
    }

    public Integer getPrice_mobile() {
        return price_mobile;
    }

    public void setPrice_mobile(Integer price_mobile) {
        this.price_mobile = price_mobile;
    }

    public Integer getNormal_price() {
        return normal_price;
    }

    public void setNormal_price(Integer normal_price) {
        this.normal_price = normal_price;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getAdd_image_link() {
        return add_image_link;
    }

    public void setAdd_image_link(String add_image_link) {
        this.add_image_link = add_image_link;
    }

    public String getNaver_category() {
        return naver_category;
    }

    public void setNaver_category(String naver_category) {
        this.naver_category = naver_category;
    }

    public String getNaver_product_id() {
        return naver_product_id;
    }

    public void setNaver_product_id(String naver_product_id) {
        this.naver_product_id = naver_product_id;
    }

    public String getCondition_h() {
        return condition_h;
    }

    public void setCondition_h(String condition_h) {
        this.condition_h = condition_h;
    }

    public String getImport_flag() {
        return import_flag;
    }

    public void setImport_flag(String import_flag) {
        this.import_flag = import_flag;
    }

    public String getParalel_import() {
        return paralel_import;
    }

    public void setParalel_import(String paralel_import) {
        this.paralel_import = paralel_import;
    }

    public String getOrder_made() {
        return order_made;
    }

    public void setOrder_made(String order_made) {
        this.order_made = order_made;
    }

    public String getProduct_flag() {
        return product_flag;
    }

    public void setProduct_flag(String product_flag) {
        this.product_flag = product_flag;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getManufacture_define_number() {
        return manufacture_define_number;
    }

    public void setManufacture_define_number(String manufacture_define_number) {
        this.manufacture_define_number = manufacture_define_number;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getCard_event() {
        return card_event;
    }

    public void setCard_event(String card_event) {
        this.card_event = card_event;
    }

    public String getEvent_words() {
        return event_words;
    }

    public void setEvent_words(String event_words) {
        this.event_words = event_words;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getPartner_coupon_download() {
        return partner_coupon_download;
    }

    public void setPartner_coupon_download(String partner_coupon_download) {
        this.partner_coupon_download = partner_coupon_download;
    }

    public String getInterest_free_event() {
        return interest_free_event;
    }

    public void setInterest_free_event(String interest_free_event) {
        this.interest_free_event = interest_free_event;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getInstallation_costs() {
        return installation_costs;
    }

    public void setInstallation_costs(String installation_costs) {
        this.installation_costs = installation_costs;
    }

    public String getSearch_tag() {
        return search_tag;
    }

    public void setSearch_tag(String search_tag) {
        this.search_tag = search_tag;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getCoordi_id() {
        return coordi_id;
    }

    public void setCoordi_id(String coordi_id) {
        this.coordi_id = coordi_id;
    }

    public Integer getMinimun_purchase_quanitity() {
        return minimun_purchase_quanitity;
    }

    public void setMinimun_purchase_quanitity(Integer minimun_purchase_quanitity) {
        this.minimun_purchase_quanitity = minimun_purchase_quanitity;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public String getDelivery_grade() {
        return delivery_grade;
    }

    public void setDelivery_grade(String delivery_grade) {
        this.delivery_grade = delivery_grade;
    }

    public String getDelivery_detail() {
        return delivery_detail;
    }

    public void setDelivery_detail(String delivery_detail) {
        this.delivery_detail = delivery_detail;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOption_detail() {
        return option_detail;
    }

    public void setOption_detail(String option_detail) {
        this.option_detail = option_detail;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public String getSoldout_yn() {
        return soldout_yn;
    }

    public void setSoldout_yn(String soldout_yn) {
        this.soldout_yn = soldout_yn;
    }
}
