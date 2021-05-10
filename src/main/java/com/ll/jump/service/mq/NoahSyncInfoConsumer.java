package com.ll.jump.service.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhipin.noah.model.DeptInfoApiVO;
import com.zhipin.noah.model.UserInfoApiVO;
import com.zhipin.noah.mq.NoahEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "boss_serv5_test_noah_outbound_c0", topic = "boss_serv15_noah_outbound")
public class NoahSyncInfoConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        try {
            log.info("开始消费rocketMq消息:{}", msg);
            JSONObject jsonObject = JSONObject.parseObject(msg);
            NoahEventMessage noahEventMessage = JSON.toJavaObject(jsonObject, NoahEventMessage.class);
            switch (noahEventMessage.getSource()) {
                //员工信息变更消息
                case "user_info":
                    switch (noahEventMessage.getEventType()) {
                        case INSERT:
                            UserInfoApiVO userInfoApiVO = JSON.parseObject(noahEventMessage.getValue().toString(), UserInfoApiVO.class);
                            //TODO; 员工新增
                            break;
                        case DELETE:
                            UserInfoApiVO delUser = JSON.parseObject(noahEventMessage.getValue().toString(), UserInfoApiVO.class);
                            //TODO; 员工删除
                            break;
                        case LEAVE:
                            UserInfoApiVO leaveUser = JSON.parseObject(noahEventMessage.getValue().toString(), UserInfoApiVO.class);
                            //TODO; 员工离职
                            break;
                        case BATCH_UPDATE:
                            List<UserInfoApiVO> noahUserList = JSONArray.parseArray(noahEventMessage.getValue().toString(), UserInfoApiVO.class);
                            //TODO; 员工批量更新
                            break;
                        case SYNC_FULL:
                            //TODO; 员工发生大量复杂变动，希望全量同步
                            //获取全量员工信息dubbo接口 NoahUserApi.getUserInfo(UserInfoRequest request)
                            break;
                        default:
                            break;
                    }
                    break;
                //部门信息变更消息
                case "department":
                    switch (noahEventMessage.getEventType()) {
                        case INSERT:
                            DeptInfoApiVO deptInfo = JSON.parseObject(noahEventMessage.getValue().toString(), DeptInfoApiVO.class);
                            //TODO; 部门新增
                            break;
                        case DELETE:
                            DeptInfoApiVO deptInfoApiVO = JSON.parseObject(noahEventMessage.getValue().toString(), DeptInfoApiVO.class);
                            //TODO; 部门删除
                            break;
                        case BATCH_UPDATE:
                            List<DeptInfoApiVO> noahDeptList = JSONArray.parseArray(noahEventMessage.getValue().toString(), DeptInfoApiVO.class);
                            //TODO; 部门批量更新，此时T为List类型
                            break;
                        case SYNC_FULL:
                            //TODO; 部门发生大量复杂变动，希望全量同步部门及员工信息
                            //获取全量部门信息详见接口 NoahDepartmentApi
                            //获取全量员工信息dubbo接口 NoahUserApi.getUserInfo(UserInfoRequest request)
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("NoahSyncInfoConsumer error.", e);
        }
    }
}