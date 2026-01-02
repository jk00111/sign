package com.example.sign.config;

import com.example.sign.vo.ApprovalUser;
import com.example.mybatis.common.entity.User;

public interface ApprovalUserResolver {

    ApprovalUser resolve(User entity);

}
