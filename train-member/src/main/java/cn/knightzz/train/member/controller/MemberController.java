package cn.knightzz.train.member.controller;

import cn.knightzz.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王天赐
 * @title: MemberController
 * @projectName train-project
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 10:37
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    MemberService memberService;

    @GetMapping("/count")
    public String count() {
        return "count = " + memberService.count();
    }

    @PostMapping("/register")
    public String register(String mobile) {
        long id = memberService.register(mobile);
        return "id = " + id;
    }
}
