package com.springboot.mybatis.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.mybatis.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountMapperTest.class);

    @Autowired
    private AccountMapper mapper;

    @Test
    public void PageHelperTest(){

        final PageInfo<Account> pageInfo = PageHelper.startPage(1,2).setOrderBy("id asc").doSelectPageInfo(()->mapper.findAccountList());
        logger.info(pageInfo.toString());
        logger.info("============== 分割线 ====================");
        PageHelper.startPage(2, 1).setOrderBy("id asc");
        logger.info(mapper.findAccountList().toString());
    }
}
