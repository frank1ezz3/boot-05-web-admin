package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.exception.UserTooManyException;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
public class TableController {

    @Autowired
    UserService userService;

    /**
     *
     * @param a 不带请求参数或者请求参数不对，400：bad request 一般都是浏览器的参数没有传递正确
     * @return
     */
    @GetMapping (value = "/basic_table")
    public String basic_table(@RequestParam("a") int a){
        int i=10/0;
        return "table/basic_table";
    }

    @GetMapping("/user/insert/{id}")
    public String insertUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){
        User user1 = new User();
        user1.setId(1L);
        user1.setName("caoyu");
        user1.setAge(18);
        user1.setEmail("laoshi@qq.com");

        User user2 = new User();
        user1.setId(8L);
        user1.setName("caoyu");
        user1.setAge(18);
        user1.setEmail("laoshi@qq.com");

        List users = Arrays.asList(user1,user2);
        userService.saveOrUpdateBatch(users);

        ra.addAttribute("pn",pn);

        return "redirect:/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){

        userService.removeById(id);

        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }



    @GetMapping (value = "/dynamic_table")
    public String dynamic_table(@RequestParam(value="pn",defaultValue = "1") Integer pn, Model model){
        //表格内容的遍历
//        response.sendError
//     List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("haha", "aaaaa"),
//                new User("hehe ", "aaddd"));
//        model.addAttribute("users",users);
//
//        if(users.size()>3){
//            throw new UserTooManyException();
//        }
//
//        if(users.size()>3){
//            throw new UserTooManyException();
//        }
        //从数据库中查出user表中的用户进行展示



        //构造分页参数
        Page<User> page = new Page<>(pn, 2);
        //调用page进行分页
        Page<User> userPage = userService.page(page, null);


//        userPage.getRecords()
//        userPage.getCurrent()
//        userPage.getPages()


        model.addAttribute("users",userPage);

        return "table/dynamic_table";
    }






    @GetMapping (value = "/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }
    @GetMapping (value = "/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }
    @GetMapping (value = "/pricing_table")
    public String pricing_table(){
        return "table/pricing_table";
    }

}