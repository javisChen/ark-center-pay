package com.kt.cloud.pay.module.paynotifyrecord.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.pay.dao.entity.PayNotifyRecordDO;
import com.kt.cloud.pay.dao.mapper.PayNotifyRecordMapper;
import com.kt.cloud.pay.module.paynotifyrecord.dto.request.PayNotifyRecordUpdateReqDTO;
import com.kt.cloud.pay.module.paynotifyrecord.dto.request.PayNotifyRecordPageQueryReqDTO;
import com.kt.cloud.pay.module.paynotifyrecord.dto.response.PayNotifyRecordRespDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kt.component.dto.PageResponse;
import org.springframework.stereotype.Service;
import com.kt.component.web.util.bean.BeanConvertor;
import com.kt.component.orm.mybatis.base.BaseEntity;

/**
 * <p>
 * 支付结果通知记录 服务实现类
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Service
public class PayNotifyRecordService extends ServiceImpl<PayNotifyRecordMapper, PayNotifyRecordDO> implements IService<PayNotifyRecordDO> {

    public Long createPayNotifyRecord(PayNotifyRecordUpdateReqDTO reqDTO) {
        PayNotifyRecordDO entity = BeanConvertor.copy(reqDTO, PayNotifyRecordDO.class);
        save(entity);
        return entity.getId();
    }

    public PageResponse<PayNotifyRecordRespDTO> getPageList(PayNotifyRecordPageQueryReqDTO queryDTO) {
        IPage<PayNotifyRecordRespDTO> page = lambdaQuery()
                .orderByDesc(BaseEntity::getGmtCreate)
                .page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()))
                .convert(item -> BeanConvertor.copy(item, PayNotifyRecordRespDTO.class));
        return BeanConvertor.copyPage(page, PayNotifyRecordRespDTO.class);
    }

    public Long updatePayNotifyRecord(PayNotifyRecordUpdateReqDTO reqDTO) {
        PayNotifyRecordDO entity = BeanConvertor.copy(reqDTO, PayNotifyRecordDO.class);
        updateById(entity);
        return entity.getId();
    }

    public PayNotifyRecordRespDTO getPayNotifyRecordInfo(Long PayNotifyRecordId) {
        PayNotifyRecordDO entity = getById(PayNotifyRecordId);
        return BeanConvertor.copy(entity, PayNotifyRecordRespDTO.class);
    }

}
