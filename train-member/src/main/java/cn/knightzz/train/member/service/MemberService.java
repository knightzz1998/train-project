package cn.knightzz.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.knightzz.train.member.domain.Member;
import cn.knightzz.train.member.domain.MemberExample;
import cn.knightzz.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile) {

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> memberList = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(memberList)) {
            // return memberList.get(0).getId();
            throw new RuntimeException("手机号已注册!");
        }


        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
