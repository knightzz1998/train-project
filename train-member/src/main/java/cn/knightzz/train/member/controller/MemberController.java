package cn.knightzz.train.member.controller;

import cn.knightzz.train.common.response.CommonResp;
import cn.knightzz.train.member.request.MemberLoginReq;
import cn.knightzz.train.member.request.MemberRegisterReq;
import cn.knightzz.train.member.request.MemberSendCodeReq;
import cn.knightzz.train.member.response.MemberLoginResp;
import cn.knightzz.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Integer> count() {
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(memberService.count());
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq registerReq) {
        long id = memberService.register(registerReq.getMobile());
        CommonResp<Long> commonResp = new CommonResp<>();
        commonResp.setContent(id);
        return commonResp;
    }

    @PostMapping("/send-code")
    public CommonResp<String> sendCode(@Valid @RequestBody MemberSendCodeReq sendCodeReq) {
        String code = memberService.sendCode(sendCodeReq.getMobile());
        CommonResp<String> commonResp = new CommonResp<>();
        commonResp.setContent(code);
        return commonResp;
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq loginReq) {
        MemberLoginResp loginResp = memberService.login(loginReq);
        return new CommonResp<>(loginResp);
    }
}
