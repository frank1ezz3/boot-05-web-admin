package com.atguigu.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("嵌套测试")
public class TestingAStackDemo {
    Stack<Object> stack;//栈

    @ParameterizedTest
    @DisplayName("参数化测试")
    @ValueSource(ints = {1,2,3,4,5})//把所有想测的参数都打包起来一起测一遍
    void testParameterized(int i){
        System.out.println(i);
    }



    @ParameterizedTest
    @DisplayName("参数化测试")
    @MethodSource("stringProvider")//以流的形式，把所有想测的参数都打包起来一起测一遍
    void testParameterized2(String i){
        System.out.println(i);
    }
    static Stream<String> stringProvider(){
        return Stream.of("apple","banana","atguigu");
    }

    @Test
    @DisplayName("is instantiated with new Stack()我new了一个栈")
    void isInstantiatedWithNew() {
        new Stack<>();
        //在嵌套测试情况下外层的test不能驱动内存的Before（After）Each/All之类的方法提前或者之后运行
        assertNull(stack);
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();//虽然new了，但是里面没有元素，所以下面是正确的
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);//pop是在栈里弹出一个元素
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);//peek是在栈里找到第一个元素
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);//往栈里加元素了哦
            }

            /**
             * 内层的Test可以驱动外层的方法
             */
            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
