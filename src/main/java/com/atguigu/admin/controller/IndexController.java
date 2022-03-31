package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.service.AccountService;
import com.atguigu.admin.service.CityService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CityService cityService;
//
//    //    @Autowired
//    StringRedisTemplate redisTemplate;
//
    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){

        cityService.saveCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
//        return null;
        return cityService.getById(id);
    }

    @ResponseBody
    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Long id){

        return accountService.getAcctByid(id);
    }


    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_tbl", Long.class);
        return aLong.toString();
    }

    /*
        登录页
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        if (!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
            //把登录成功的用户保存起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到main页面，不然会表单重复提交，恶熏熏
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","账号密码都能输错的啊，是不是想白嫖");
            System.out.println(model);
            //滚回去
            return "login";
        }

    }

    /**
     * 去main页面
     *
     * @return
     */
    @GetMapping(value = "/main.html")
    public String mainPage(HttpSession session,Model model) {
        log.info("当前方法是：{}","mainPage");
//        //是否登录 。 拦截器，过滤器
//        Object loginUser = session.getAttribute("loginUser");
//        if(loginUser!=null){
//            return "main";
//        }else{
//            model.addAttribute("msg","你能不能先给我登录一下啊，能不能啊！！！");
//            System.out.println(model);
//            //滚回去
//            return "login";
//        }

        return "main";


    }
}
