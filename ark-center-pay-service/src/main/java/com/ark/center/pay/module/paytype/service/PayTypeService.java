package com.ark.center.pay.module.paytype.service;

import com.ark.center.pay.dao.entity.PayTypeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ark.center.pay.dao.mapper.PayTypeMapper;
import com.ark.center.pay.module.paytype.dto.request.PayTypeUpdateReqDTO;
import com.ark.center.pay.module.paytype.dto.request.PayTypePageQueryReqDTO;
import com.ark.center.pay.module.paytype.dto.response.PayTypeRespDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ark.component.dto.PageResponse;
import org.springframework.stereotype.Service;
import com.ark.component.web.util.bean.BeanConvertor;
import com.ark.component.orm.mybatis.base.BaseEntity;

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
                .orderByDesc(BaseEntity::getCreateTime)
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
