package com.dbsd6th.dao;

import com.dbsd6th.entity.TicketCount;
import com.dbsd6th.entity.TrainInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author hjs
 * @date 2018/11/29 14:04
 */
public class TicketCountMapperTest {

    private ApplicationContext applicationContext;
    @Autowired
    private TicketCountMapper ticketCountMapper;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        //加载spring配置文件

        ticketCountMapper = applicationContext.getBean(TicketCountMapper.class);
        //在这里导入要测试的

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        try{
            if(ticketCountMapper == null)
                System.out.println("ticketCountMapper is null!");
            TicketCount tc = this.ticketCountMapper.selectByPrimaryKey(1);
            System.out.println(tc);
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    @Test
    public void searchTicket() throws Exception {
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        Date sTime = adf.parse("2019-01-01");
        Date eTime = adf.parse("2019-01-02");

        HashMap<String, Object> input = new HashMap<String, Object>();
        input.put("sTime", sTime);
        input.put("eTime", eTime);
        input.put("tid", new Integer(1));

        TicketCount ticketCount = ticketCountMapper.searchTicket(input);
        System.out.println(ticketCount);
    }

    @Test
    public void minusBySeatLevel() throws Exception{
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        Date sTime = adf.parse("2019-01-01");
        /*Date eTime = adf.parse("2019-01-02");*/

        HashMap<String, Object> input = new HashMap<String, Object>();
        input.put("sTime",sTime);
        /*input.put("eTime",eTime);*/

        input.put("tid", new Integer(1));
        /*TicketCount ticketCount = this.ticketCountMapper.searchTicket(input);*/
        /*int nowNum = ticketCount.getStandRemain();*/
        /* input.put("nowNum",new Integer(nowNum));*/
        input.put("level","second_remain");//选择二等座
        int result = this.ticketCountMapper.minusBySeatLevel(input);
        if(result==1){
            System.out.println("购票成功！");
        }else{
            System.out.println("购票失败！");
        }

        /*时间转换测试*/
        /*Date time = ticketCount.getOrderTime();
        System.out.println(time);
        String s = adf.format(time);
        Date time2 = adf.parse(s);
        System.out.println(time2);
        System.out.println(sTime);*/

    }



    /*应该把ticket_Count表的所有剩余座位类型改为varchar？*/

}