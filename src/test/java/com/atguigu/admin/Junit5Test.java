package com.atguigu.admin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/*@RunWith*/
@SpringBootTest//用spring的测试驱动来进行测试的
@DisplayName("junit5功能测试类")
public class Junit5Test {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 测试前置条件
     */
    @DisplayName("测试前置条件")
    @Test
    void testassumptions(){
        Assumptions.assumeTrue(false,"结果不是true啊");//和Disable差不多
        System.out.println("11111");
    }


    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions(){
        int cal = cal(3, 8);
        //
        assertEquals(11,cal,"算都能算错啊");//前面如果错了的话后面也就不会再执行了，断言
        //assertEquals(12,cal,"算都能算错啊");
        Object obj1 = new Object();
        Object obj2 = new Object();
        //assertSame(obj1,obj2,"两个对象不一样");

    }
    int cal(int i ,int j){
        return i+j;
    }

    @Test
    @DisplayName("array assertion")
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[] {1, 2},"这两个数组不相等啊");
        assertArrayEquals(new int[]{2, 2}, new int[] {1, 2},"这两个数组不相等啊");
    }

    //方法内所有成功即成功 有一个失败就失败
    @Test
    @DisplayName("assert all")
    public void all() {
        assertAll("test",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(1 > 0)
        );

        System.out.println("成功啦");
    }

    //异常断言
    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        //断定业务逻辑一定出现异常
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> {
                    int i= 10/0;
                },"这个业务居然能运行我擦，不该出异常么");
    }

    @Test
    @DisplayName("快读失败")
    void testFail(){
        //xxxxxxxx
        if(1==2)fail("测试失败");
    }


    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName(){
        System.out.println(1);
        System.out.println(jdbcTemplate);
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
    void test2(){
        System.out.println(2);
    }

    @RepeatedTest(value = 5)//重新连续运行5次
    @Test
    void test3(){
        System.out.println(2);
    }
    /**
     * 规定方法超时间。超出时间测试出异常
     * @throws InterruptedException
     */
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);
    }

    @BeforeEach//每一个单元测试之前都要运行一遍这个东西
    void testBeforeEach(){
        System.out.println("测试就要开始了：");
    }

    @AfterEach//每一个单元测试之后都要运行一遍这个东西
    void testAfterEach(){
        System.out.println("测试就要结束了：");
    }

    @BeforeAll//每一个单元测试之前都要运行一遍这个东西
    static void testBeforeAll(){
        System.out.println("所有的测试就要开始了：");
    }

    @AfterAll//每一个单元测试之后都要运行一遍这个东西
    static void testAfterAll(){
        System.out.println("所有的测试就要结束了：");
    }



}
