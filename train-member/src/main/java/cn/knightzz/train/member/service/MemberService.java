package cn.knightzz.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.knightzz.train.common.exception.BusinessException;
import cn.knightzz.train.common.exception.BusinessExceptionEnum;
import cn.knightzz.train.common.util.JwtUtil;
import cn.knightzz.train.common.util.SnowUtil;
import cn.knightzz.train.member.domain.Member;
import cn.knightzz.train.member.domain.MemberExample;
import cn.knightzz.train.member.mapper.MemberMapper;
import cn.knightzz.train.member.request.MemberLoginReq;
import cn.knightzz.train.member.response.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String mobile) {

        Member member = selectByMobile(mobile);
        if (ObjectUtil.isNotNull(member)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public String sendCode(String mobile) {

        // 1. 后端接口校验[注解]
        // 2. 判断手机号是否已经注册了

        Member member = selectByMobile(mobile);

        // 手机号尚未注册, 插入到数据库
        if (ObjectUtil.isNull(member)) {
            member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
            LOG.info("手机号 {} 不存在, 插入记录", mobile);
        } else {
            LOG.info("手机号 {} 已存在, 不插入记录", mobile);
        }

        // 3. 生成验证码

        // String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码 : {}", code);
        // TODO 保存短信记录表 : [手机号, 短信验证码,有效期, 是否已使用, 业务类型, 发送时间, 使用时间]
        // 如果没有这个表的话, 用户可以使用多次
        // 增加短信发送分析功能, 手动将大量发送的手机号拉黑, 防止黑客攻击
        LOG.info("保存短信记录表");
        // TODO 对接短信通道, 发送短信
        LOG.info("对接短信通道, 发送短信");

        return code;
    }

    public MemberLoginResp login(MemberLoginReq req) {

        String mobile = req.getMobile();
        String code = req.getCode();

        // 1. 查询当前用户是否已经注册
        Member member = selectByMobile(mobile);

        if(ObjectUtil.isNull(member)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        if(!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        // 拷贝属性
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(member, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    public Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> memberList = memberMapper.selectByExample(memberExample);

        if(CollUtil.isEmpty(memberList)) {
            return null;
        }else {
            return memberList.get(0);
        }
    }
}
