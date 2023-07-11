package cn.knightzz.train.member.service;

import cn.knightzz.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 王天赐
 * @title: MemberService
 * @projectName train-project
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-07-11 10:36
 */
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return memberMapper.count();
    }
}
