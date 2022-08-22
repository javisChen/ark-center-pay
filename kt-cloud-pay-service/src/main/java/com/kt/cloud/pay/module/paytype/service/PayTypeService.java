package com.kt.cloud.pay.module.paytype.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kt.cloud.pay.dao.entity.PayTypeDO;
import com.kt.cloud.pay.dao.mapper.PayTypeMapper;
import com.kt.cloud.pay.module.paytype.dto.request.PayTypeUpdateReqDTO;
import com.kt.cloud.pay.module.paytype.dto.request.PayTypePageQueryReqDTO;
import com.kt.cloud.pay.module.paytype.dto.response.PayTypeRespDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kt.component.dto.PageResponse;
import org.springframework.stereotype.Service;
import com.kt.component.web.util.bean.BeanConvertor;
import com.kt.component.orm.mybatis.base.BaseEntity;

/**
 * <p>
 * 支付类别表 服务实现类
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Service
public class PayTypeService extends ServiceImpl<PayTypeMapper, PayTypeDO> implements IService<PayTypeDO> {

    public Long createPayType(PayTypeUpdateReqDTO reqDTO) {
        PayTypeDO entity = BeanConvertor.copy(reqDTO, PayTypeDO.class);
        save(entity);
        return entity.getId();
    }

    public PageResponse<PayTypeRespDTO> getPageList(PayTypePageQueryReqDTO queryDTO) {
        IPage<PayTypeRespDTO> page = lambdaQuery()
                .orderByDesc(BaseEntity::getGmtCreate)
                .page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()))
                .convert(item -> BeanConvertor.copy(item, PayTypeRespDTO.class));
        return BeanConvertor.copyPage(page, PayTypeRespDTO.class);
    }

    public Long updatePayType(PayTypeUpdateReqDTO reqDTO) {
        PayTypeDO entity = BeanConvertor.copy(reqDTO, PayTypeDO.class);
        updateById(entity);
        return entity.getId();
    }

    public PayTypeRespDTO getPayTypeInfo(Long PayTypeId) {
        PayTypeDO entity = getById(PayTypeId);
        return BeanConvertor.copy(entity, PayTypeRespDTO.class);
    }

}
