package com.ctx;

import com.ctx.crawling.CretecDoc;
import com.ctx.domain.Category;
import com.ctx.repository.CategoryRty;
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
	public static void main(String[] args) {
		SpringApplication.run(CrawlingApplication.class, args);
	}
	private static Map loginInfo() {
		Map mLoginInfo = new HashMap<String, Object>();
		mLoginInfo.put("idName", "id");
		mLoginInfo.put("pwName", "password");
		mLoginInfo.put("id", "admin");
		mLoginInfo.put("pw", "1111");
		return mLoginInfo;
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Map mLoginInfo = loginInfo();
		CretecDoc cretecDoc = new CretecDoc("con");
		cretecDoc.Login(mLoginInfo);
		BufferedReader bufferedReader = cretecDoc.getCsvCateData();
		String line    = "";
		Category  cate = new Category();
		List<Category> saveCate = new ArrayList<>();
		while ( (line = bufferedReader.readLine()) != null ) {
			String[] aCate = line.split(",");
			if ( aCate != null && aCate.length == 5 ) {
				if ( categoryRty.countByCategoryLAndCategoryMAndCategorySAndCategorySS(aCate[0], aCate[1], aCate[2], aCate[3]) == 0 ) {
					cate.convert(aCate);
					categoryRty.save(cate);
				}
			}
		}
	}
}
