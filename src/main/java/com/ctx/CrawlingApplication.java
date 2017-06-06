package com.ctx;

import com.ctx.crawling.CretecDoc;
import com.ctx.domain.Category;
import com.ctx.domain.Item;
import com.ctx.domain.ItemMaster;
import com.ctx.repository.CategoryRty;
import com.ctx.repository.ItemMasterRty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class CrawlingApplication implements CommandLineRunner {
	@Autowired
	CategoryRty categoryRty;
	@Autowired
    ItemMasterRty itemMasterRty;
	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args);
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
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
        BufferedReader bufferedReader = cretecDoc.getCsvCateData();
        String line    = "";
        List<Category> saveCate = new ArrayList<>();
        while ( (line = bufferedReader.readLine()) != null ) {
            String[] aCate = line.split(",");
            if ( aCate != null && aCate.length == 5 ) {
                if ( categoryRty.countByCategoryLAndCategoryMAndCategorySAndCategorySS(aCate[0], aCate[1], aCate[2], aCate[3]) == 0 ) {
                    Category cate = new Category();
                    cate.convert(aCate);
                    categoryRty.save(cate);
                }
            }
        }
    }
    private void ItemInfo() throws Exception {
        Map mLoginInfo = loginInfo();

        CretecDoc cretecDoc = new CretecDoc("con");
        cretecDoc.Login(mLoginInfo);
        BufferedReader bufferedReader = cretecDoc.getCsvItemData();
        StringBuffer resultSb = new StringBuffer();
        String line = "";

        while ( (line = bufferedReader.readLine()) != null ) {
            String[] aItem = line.split(",");
            Item item = new Item();
            item.convert(aItem);
            if ( itemMasterRty.countByMaster_image_link(item.getImage_link()) > 0 ) { // SUB
                ItemMaster itemMaster = new ItemMaster();

            } else { // MAIN
                ItemMaster itemMaster = new ItemMaster();
            }
        }
    }
}
