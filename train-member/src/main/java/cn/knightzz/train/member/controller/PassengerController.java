package cn.knightzz.train.member.controller;

import cn.knightzz.train.common.context.LoginMemberContext;
import cn.knightzz.train.common.response.CommonResp;
import cn.knightzz.train.common.response.PageResp;
import cn.knightzz.train.member.request.PassengerQueryReq;
import cn.knightzz.train.member.request.PassengerSaveReq;
import cn.knightzz.train.member.response.PassengerQueryResp;
import cn.knightzz.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王天赐
 * @title: PassengerController
 * @projectName train-project
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-14 10:16
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {


    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        passengerService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-mine")
    public CommonResp<List<PassengerQueryResp>> queryMine() {
        List<PassengerQueryResp> list = passengerService.queryMine();
        return new CommonResp<>(list);
    }
}
