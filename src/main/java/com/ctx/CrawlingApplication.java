package com.ctx;

import com.ctx.crawling.CretecDoc;
import com.ctx.domain.Category;
import com.ctx.domain.Item;
import com.ctx.domain.MetaInfo;
import com.ctx.domain.MetaInfoDetail;
import com.ctx.repository.CategoryRty;
import com.ctx.repository.ItemRty;
import com.ctx.repository.MetaInfoDetailRty;
import com.ctx.repository.MetaInfoRty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args);
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
        cateInfo();
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
                if ( categoryRty.countById(aCate[0] + aCate[1] + aCate[2] + aCate[3]) == 0 ) {
                    Category cate = new Category();
                    cate.setId(aCate[0] + aCate[1] + aCate[2] + aCate[3]);
                    cate.setCategoryNm(aCate[4]);
                    if ( Integer.parseInt(aCate[1]) == 0 ) { // 대분류
                    } else if ( Integer.parseInt(aCate[2]) == 0 ) { // 중분류
                        cate.setParent(categoryRty.findOne(aCate[0] + "000"));
                    } else if ( Integer.parseInt(aCate[3]) == 0 ) { // 세분류
                        cate.setParent(categoryRty.findOne(aCate[0] + aCate[1] + "00"));
                    } else { // 미세분류
                        cate.setParent(categoryRty.findOne(aCate[0] + aCate[1] + aCate[2] + "0"));
                    }
                    categoryRty.save(cate);
                }
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

        for ( String data : list ) {
            String[] aItem = data.split(",");
            if ( itemRty.countById(Long.parseLong(aItem[0])) == 0 ) {
                Item item = new Item();

                item.setId(aItem[0]);
                item.setProdCd(aItem[1]);
                item.setUseYn(aItem[2]);
                item.setSoldoutYn(aItem[3]);
                item.setMaker(aItem[5]);
                item.setItemName(aItem[6]);
                item.setModelNumber(aItem[7]);
                item.setTopImageUrl(aItem[8]);
                item.setOutUnit(Integer.parseInt(aItem[18]));
                item.setUnitNm(aItem[19]);
                item.setStandard(aItem[20]);
                item.setInBox(Integer.parseInt(aItem[21]));
                item.setOutBox(Integer.parseInt(aItem[22]));
                if ( aItem[23] != null && aItem[23].equals("Y") ) {

                }
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
                itemRty.save(item);
                if ( aItem[26] != null && !aItem[26].equals("") ) {
                    String[] Metas = aItem[26].split("|");
                    for ( String meta : Metas ) {
                        MetaInfo metaInfo = new MetaInfo();
                        String[] datas = data.split("_", 2);
                        metaInfo = metaInfoRty.findByName(datas[0]);
                        if ( metaInfo == null ) {  // INSERT
                            metaInfo.setItem(item);
                            metaInfo.setName(datas[0]);
                            metaInfoRty.save(metaInfo);
                        }
                        MetaInfoDetail metaInfoDetail = new MetaInfoDetail();
                        metaInfoDetail.setName(datas[1]);
                        metaInfoDetail.setMetaInfo(metaInfo);
                        metaInfoDetailRty.save(metaInfoDetail);

                    }
                }
                if ( aItem[27] != null ) { //제품이미지 add

                }

            }
        }
    }
}
