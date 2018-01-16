package com.neo.mapper;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.entity.UserEntity;
import com.neo.enums.UserSexEnum;
import org.springframework.util.ResourceUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper UserMapper;

	@Test
	public void testInsert() throws Exception {
		UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
		UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
		UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));

		Assert.assertEquals(3, UserMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<UserEntity> users = UserMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.toString());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		UserEntity user = UserMapper.getOne(28l);
		System.out.println(user.toString());
		user.setNickName("neo");
		UserMapper.update(user);
		Assert.assertTrue(("neo".equals(UserMapper.getOne(28l).getNickName())));
	}

	//读取单个单元格
	@Test
	public void testRead() throws Exception {
		//Excel文件
//		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStreamputStream(ResourceUtils.getFile("classpath:demo.xls")));
        Workbook wb = WorkbookFactory.create(new FileInputStream(ResourceUtils.getFile("classpath:demo.xls")));
//		Workbook wb = null;
//		wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:demo.xlsx")));
//        ZipSecureFile.setMinInflateRatio(-1.0d);
//		if (isExcel2003)
//		{
//			wb = new HSSFWorkbook(inputStream);
//		}
//		else
//		{
//			wb = new XSSFWorkbook(inputStream);
//		}
		//Excel工作表
//		HSSFSheet sheet = wb.getSheetAt(0);

//		表头那一行
//		HSSFRow titleRow = sheet.getRow(0);

//		表头那个单元格
//		HSSFCell titleCell = titleRow.getCell(0);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell titleCell = row.getCell(0);
        String title = titleCell.getStringCellValue();

		System.out.println("标题是："+title);
	}


}