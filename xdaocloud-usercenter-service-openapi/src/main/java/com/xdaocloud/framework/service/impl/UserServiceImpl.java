package com.xdaocloud.framework.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xdaocloud.framework.dto.request.user.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdaocloud.base.config.QiniuConfig;
import com.xdaocloud.base.exception.XdaoException;
import com.xdaocloud.base.info.ResultInfo;
import com.xdaocloud.base.page.PageResponse;
import com.xdaocloud.base.utils.ListUtils;
import com.xdaocloud.base.utils.RedisUtils;
import com.xdaocloud.framework.Constant;
import com.xdaocloud.framework.dto.response.user.FindAllUserResponse;
import com.xdaocloud.framework.dto.response.user.FindUserPermissionResponse;
import com.xdaocloud.framework.dto.response.user.LoginResponse;
import com.xdaocloud.framework.dto.response.user.UserDetailResponse;
import com.xdaocloud.framework.mapper.UserMapper;
import com.xdaocloud.framework.model.User;
import com.xdaocloud.framework.service.UserService;
import com.xdaocloud.framework.tree.TreeDataBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private QiniuConfig qiniuConfig;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 注册
     */
    @Override
    @Transactional
    public ResultInfo<?> register(RegisterRequest request)
            throws Exception {
        User user = new User();
        user.setUsername(request.getUsername());
        User userResul = userMapper.selectOne(user);
        if (null != userResul) {
            return new ResultInfo<>(ResultInfo.FAILURE, "用户名已存在", userResul);
        }
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        boolean bool = userMapper.insertSelective(user) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        User userResp = userMapper.selectOne(user);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, userResp);
    }

    /**
     * 登录
     */
    @Override
    public ResultInfo<?> login(LoginRequest request)
            throws Exception {
        User user = userMapper.login(request.getUsername());
        if (user == null) {
            return new ResultInfo<>(ResultInfo.FAILURE, "用户名或密码错误");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResultInfo<>(ResultInfo.FAILURE, "用户名或密码错误");
        }

        if (user.getStatus() != 1) {
            return new ResultInfo<>(ResultInfo.FAILURE, "账户已被禁用，请联系管理员");
        }

        // 更新最后登录时间
        User record = new User();
        record.setId(user.getId());
        record.setLastLoginTime(new Date());
        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByPrimaryKeySelective(record) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        // 生成Token
        String userToken = Jwts.builder().setSubject(String.valueOf(user.getId())).setExpiration(new Date(System.currentTimeMillis() + Constant.TOKEN_EXP_SECENDS * 1000)).signWith(SignatureAlgorithm.HS512, Constant.JWT_SECRET).compact();
        redisUtils.set(Constant.USER_TOKEN + user.getId(), userToken, Constant.TOKEN_EXP_SECENDS);

        LoginResponse response = new LoginResponse(user.getId(), userToken, user.getNickname(), user.getAvatar(), user.getPhone(), user.getName(), user.getIdcard());

        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    @Override
    public ResultInfo<?> logout(Long id) {
        redisUtils.remove(Constant.USER_TOKEN + id);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 详情
     */
    @Override
    public ResultInfo<UserDetailResponse> detail(Long id)
            throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        UserDetailResponse response = new UserDetailResponse(user.getId(), user.getName(), user.getUsername(), user.getAvatar(), user.getPhone(), user.getEmail(), user.getNickname());
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    /**
     * 修改资料
     */
    @Override
    @Transactional
    public ResultInfo<?> updateProfile(UpdateUserRequest request)
            throws Exception {
        User record = new User();
        record.setId(request.getId());

        if (StringUtils.isNoneBlank(request.getName())) {
            record.setName(request.getName());
        }
        if (StringUtils.isNoneBlank(request.getIdcard())) {
            record.setIdcard(request.getIdcard());
        }
        if (StringUtils.isNoneBlank(request.getPhone())) {
            record.setPhone(request.getPhone());
        }
        if (StringUtils.isNoneBlank(request.getEmail())) {
            record.setEmail(request.getEmail());
        }
        if (StringUtils.isNoneBlank(request.getNickname())) {
            record.setNickname(request.getNickname());
        }
        if (StringUtils.isNoneBlank(request.getAddress())) {
            record.setAddress(request.getAddress());
        }

        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByPrimaryKeySelective(record) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional
    public ResultInfo<?> updatePwd(UpdatePwdRequest request)
            throws Exception {
        User user = userMapper.selectByPrimaryKey(request.getId());
        if (user == null) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return new ResultInfo<>(ResultInfo.FAILURE, "原密码错误");
        }

        User record = new User();
        record.setId(request.getId());
        record.setPassword(passwordEncoder.encode(request.getPassword()));
        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByPrimaryKeySelective(record) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }

        // 修改密码后移除userToken，用户需要重新登录
        redisUtils.remove(Constant.USER_TOKEN + request.getId());
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 用户列表
     */
    @Override
    public ResultInfo<PageResponse> findAllUser(FindAllUserRequest request) {
        PageHelper.startPage(request.getPage(), request.getSize());
        List<FindAllUserResponse> list = userMapper.findAllUser(request);
        if (com.xdaocloud.base.utils.ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        for (FindAllUserResponse response : list) {
            String avatar = response.getAvatar();
            if (StringUtils.isNotBlank(avatar) && !avatar.startsWith("/")) {
                response.setAvatar(qiniuConfig.getDomain() + avatar);
            }
        }

        PageInfo<FindAllUserResponse> pageInfo = new PageInfo<FindAllUserResponse>(list);
        PageResponse response = new PageResponse(pageInfo);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, response);
    }

    /**
     * 启用/禁用
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> updateStatus(UpdateUserStatusRequest request)
            throws Exception {
        for (Long id : request.getIds()) {
            User user = new User();
            user.setId(id);
            user.setStatus(request.getStatus());
            user.setUpdateTime(new Date());
            boolean bool = userMapper.updateByPrimaryKeySelective(user) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
            // 状态变化后移除userToken
            redisUtils.remove(Constant.USER_TOKEN + id);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 用户权限列表
     */
    @Override
    public ResultInfo<List<FindUserPermissionResponse>> findPermissionByUserId(FindUserPermissionRequest request)
            throws Exception {

        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", request.getUserId());
        if (StringUtils.isNotBlank(request.getAppId())) {
            condition.put("appId", request.getAppId());
        }

        List<FindUserPermissionResponse> list = userMapper.findPermissionByUserId(condition);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, list);
    }

    /**
     * 用户权限树
     */
    @Override
    public ResultInfo<List<FindUserPermissionResponse>> findPermissionAsTreeByUserId(FindUserPermissionRequest request)
            throws Exception {

        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", request.getUserId());
        if (StringUtils.isNotBlank(request.getAppId())) {
            condition.put("appId", request.getAppId());
        }

        List<FindUserPermissionResponse> list = userMapper.findPermissionByUserId(condition);
        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }

        // 转成树形结构
        TreeDataBuilder<FindUserPermissionResponse> treeDataBuilder = new TreeDataBuilder<>();
        List<FindUserPermissionResponse> trees = new ArrayList<>();
        for (FindUserPermissionResponse data : list) {
            trees.add(new FindUserPermissionResponse(data.getId(), data.getAppId(), data.getParentId(), data.getName(), data.getPermissionKey(), data.getType(), data.getUrl(), data.getMethod()));
        }
        List<FindUserPermissionResponse> build = treeDataBuilder.build(trees);
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, build);
    }

    /**
     * 逻辑删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> deleteLogic(List<Long> ids)
            throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        for (Long id : ids) {
            User user = new User();
            user.setId(id);
            user.setDeleted(true);
            user.setUpdateTime(new Date());
            boolean bool = userMapper.updateByPrimaryKeySelective(user) > 0;
            if (!bool) {
                throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
            }
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 物理删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo<?> delete(List<Long> ids)
            throws Exception {
        if (ListUtils.isEmpty(ids)) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        Example example = new Example(User.class);
        example.createCriteria().andIn("id", ids);
        boolean bool = userMapper.deleteByExample(example) > 0;
        if (!bool) {
            throw new XdaoException(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 重置密码
     */
    @Override
    public ResultInfo<?> resetPwd(ResetUserPwdRequest request) {
        Long[] ids = request.getIds();
        List<Long> asList = Arrays.asList(ids);
        Example example = new Example(User.class);
        example.createCriteria().andIn("id", asList);

        User record = new User();
        record.setPassword(passwordEncoder.encode(request.getPassword()));
        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByExampleSelective(record, example) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }

        // 修改密码后移除userToken，用户需要重新登录
        for (Long id : ids) {
            redisUtils.remove(Constant.USER_TOKEN + id);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 保存身份证信息
     *
     * @param idCardRequest
     * @return
     * @throws Exception
     * @date 2018年3月6日
     * @author LuoFuMin
     */
    @Override
    @Transactional
    public ResultInfo<?> saveIdCard(IdCardRequest idCardRequest) throws Exception {
        User record = new User();
        record.setId(Long.valueOf(idCardRequest.getUserid()));
        record.setIdcard(idCardRequest.getIdcard());
        record.setName(idCardRequest.getName());
        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByPrimaryKeySelective(record) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }

    /**
     * 保存图片地址
     *
     * @param avatar 图片地址
     * @param id     用户id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ResultInfo<?> avatarUpload(String avatar, Long id) throws Exception {
        User record = new User();
        record.setId(id);
        record.setAvatar(avatar);
        record.setUpdateTime(new Date());
        boolean bool = userMapper.updateByPrimaryKeySelective(record) > 0;
        if (!bool) {
            return new ResultInfo<>(ResultInfo.FAILURE, ResultInfo.MSG_FAILURE);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS);
    }


    /**
     * 查询用户角色
     *
     * @param id 用户id
     * @return
     */
    @Override
    public ResultInfo<?> getUserRoles(Long id) {
        List<String> list = userMapper.getUserRoles(id);

        if (ListUtils.isEmpty(list)) {
            return new ResultInfo<>(ResultInfo.DATA_NOT_FOUND, ResultInfo.MSG_DATA_NOT_FOUND);
        }
        return new ResultInfo<>(ResultInfo.SUCCESS, ResultInfo.MSG_SUCCESS, list);
    }
}
