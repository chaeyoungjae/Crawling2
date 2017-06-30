package com.ctx;

import com.ctx.crawling.CretecDoc;
import com.ctx.domain.*;
import com.ctx.enums.DeliveryType;
import com.ctx.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.*;


@SpringBootApplication
public class CrawlingApplication implements CommandLineRunner {
	@Autowired
	CategoryRty categoryRty;
	@Autowired
    ItemRty itemRty;
    @Autowired
	MetaInfoRty metaInfoRty;
    @Autowired
    MetaInfoDetailRty metaInfoDetailRty;
    @Autowired
    ItemDeliveryRty itemDeliveryRty;
	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//        cateInfo();
        ItemInfo();
	}
    private static Map loginInfo() {
        Map mLoginInfo = new HashMap<String, Object>();
        mLoginInfo.put("idName", "id");
        mLoginInfo.put("pwName", "password");
        mLoginInfo.put("id", "admin");
        mLoginInfo.put("pw", "1111");
        return mLoginInfo;
    }
    private void cateInfo() throws Exception {
        Map mLoginInfo = loginInfo();
        CretecDoc cretecDoc = new CretecDoc("con");
        cretecDoc.Login(mLoginInfo);
        List<String> lResult = cretecDoc.getCsvCateData();
        String line    = "";
        List<Category> saveCate = new ArrayList<>();
        for ( String data : lResult ) {
            String[] aCate = data.split(",");
            if ( aCate != null && aCate.length == 5 ) {
                Category cate = new Category();
                cate.setId(aCate[0] + aCate[1] + aCate[2] + aCate[3]);
                cate.setCategoryNm(aCate[4]);
                if ( Integer.parseInt(aCate[1]) == 0 ) { // 대분류
                } else if ( Integer.parseInt(aCate[2]) == 0 ) { // 중분류
                    if ( categoryRty.findOne(aCate[0] + "000") == null ) {
                        cate.setUseYn('N');
                    } else {
                    }
                    cate.setParent(categoryRty.findOne(aCate[0] + "000"));
                } else if ( Integer.parseInt(aCate[3]) == 0 ) { // 세분류
                    if ( categoryRty.findOne(aCate[0] + aCate[1] + "00") == null ) {
                        cate.setUseYn('N');
                    } else {
                    }
                    cate.setParent(categoryRty.findOne(aCate[0] + aCate[1] + "00"));
                } else { // 미세분류
                    if ( categoryRty.findOne(aCate[0] + aCate[1] + aCate[2] + "0") == null ) {
                        cate.setUseYn('N');
                    } else {
                    }
                    cate.setParent(categoryRty.findOne(aCate[0] + aCate[1] + aCate[2] + "0"));
                }
                categoryRty.save(cate);
            }
        }
    }
    private void ItemInfo() throws Exception {
        Map mLoginInfo = loginInfo();

        CretecDoc cretecDoc = new CretecDoc("con");
        cretecDoc.Login(mLoginInfo);
        List<String> list = cretecDoc.getCsvItemData();
        StringBuffer resultSb = new StringBuffer();
        String line = "";
        int i = 0;
        for ( String data : list ) {
            String[] aItem = data.split(",");
            if ( isNumeric(aItem[0].replaceAll("-", "")) && i != 0 ) {
                Item item = new Item();

                item.setId(Long.parseLong(aItem[0].replaceAll("-", "")));
                item.setProdCd(aItem[1]);
                item.setUseYn(aItem[2]);
                item.setSoldoutYn(aItem[3]);
                item.setMaker(aItem[5]);
                item.setItemName(aItem[6]);
                item.setModelNumber(aItem[7]);
                item.setTopImageUrl(aItem[8]);
                item.setOutUnit(Double.parseDouble(aItem[18]));
                item.setUnitNm(aItem[19]);
                item.setStandard(aItem[20]);
                item.setInBox(Double.parseDouble(aItem[21]));
                item.setOutBox(Double.parseDouble(aItem[22]));
                if ( aItem[29] != null && !aItem[29].equals("") ) {
                    String[] cates = aItem[29].split("_");
                    if ( Integer.parseInt(cates[1]) == 0 ) { // 대분류
                        item.setCategory(categoryRty.findOne(cates[0]+"000"));
                    } else if ( Integer.parseInt(cates[2]) == 0 ) { // 중분류
                        item.setCategory(categoryRty.findOne(cates[0]+cates[1]+"00"));
                    } else if ( Integer.parseInt(cates[3]) == 0 ) { // 세분류
                        item.setCategory(categoryRty.findOne(cates[0]+cates[1]+cates[2]+"0"));
                    } else { // 미세분류
                        item.setCategory(categoryRty.findOne(cates[0]+cates[1]+cates[2]+cates[3]));
                    }
                }
                item.setOrgin(aItem[30]);
                ItemDelivery itemDelivery = new ItemDelivery();
                if ( aItem[23] != null && aItem[23].equals("Y") ) {
                    itemDelivery.setDeliveryType(DeliveryType.FREIGHT);
                    itemDeliveryRty.save(itemDelivery);
                    item.setItemDelivery(itemDelivery);
                }
                if ( aItem[27] != null ) { //제품이미지 add
                    item.getImageUrl().add(aItem[27]);
                }
                List<Item> master  = itemRty.findByTopImageUrlAndMasterNull(item.getTopImageUrl());
                List<Item> master2 = itemRty.findByTopImageUrlAndMasterNotNull(item.getTopImageUrl());
                if ( master2 != null && master2.size() > 0 ) {
                    item.setMaster(master2.get(0));
                }
                itemRty.save(item);
                for ( Item m : master ) {
                    if ( !item.getId().equals(m.getId()) ) {
                        if ( master2 != null && master2.size() > 0 ) {
                            m.setMaster(master2.get(0));
                        } else {
                            m.setMaster(item);
                        }
                    }
                    itemRty.save(m);
                }
                if ( aItem[26] != null && !aItem[26].equals("") ) {
                    if ( !aItem[26].equals("정보없음") ) {
                        String[] Metas = aItem[26].split("\\|");
                        for (String meta : Metas) {
                            MetaInfo metaInfo = new MetaInfo();
                            String[] datas = meta.split("_", 2);
                            metaInfo = metaInfoRty.findByNameAndItem(datas[0], item);
                            if (metaInfo == null) {  // INSERT
                                metaInfo = new MetaInfo();
                                metaInfo.setItem(item);
                                metaInfo.setName(datas[0]);
                                metaInfoRty.save(metaInfo);
                            } else {
                                metaInfoRty.save(metaInfo);
                            }
                            MetaInfoDetail metaInfoDetail = metaInfoDetailRty.findByNameAndMetaInfo(datas[1], metaInfo);
                            if (metaInfoDetail == null) {
                                metaInfoDetail = new MetaInfoDetail();
                                metaInfoDetail.setName(datas[1]);
                                metaInfoDetail.setMetaInfo(metaInfo);
                                metaInfoDetailRty.save(metaInfoDetail);
                            } else {
                                metaInfoDetailRty.save(metaInfoDetail);
                            }
                        }
                        List<MetaInfo> tmpList =  metaInfoRty.findAllByItem(item);
                        for ( MetaInfo tmpInfo : tmpList ) {
                            if ( !Arrays.asList(Metas).contains(tmpInfo.getName()) ) {
                                List<MetaInfoDetail> metaInfoDetails = metaInfoDetailRty.findAllByMetaInfo(tmpInfo);
                                metaInfoDetailRty.delete(metaInfoDetails);
                                metaInfoRty.delete(tmpInfo);
                            }
                        }
                    }
                }
            } i++;
        }
    }
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
