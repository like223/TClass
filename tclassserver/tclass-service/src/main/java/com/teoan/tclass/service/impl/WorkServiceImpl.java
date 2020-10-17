package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.mapper.WorkMapper;
import com.teoan.tclass.service.WorkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * (Work)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:11
 */
@Service("workService")
@CacheConfig(cacheNames = "work_cache")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {

    @Cacheable
    @Override
    public IPage getWorksByPage(Long current, Long size, Work work) {
        Page<Work> page = new Page<>(current,size);
        QueryWrapper<Work> wrapper = new QueryWrapper<>(work);
        wrapper.orderByDesc("create_time");
        //实现标题模糊查询
        if(work.getName() != null){
            wrapper.like("name",work.getName());
            //避免精准查询
            work.setName(null);
        }
        if(work.getCreateTime()!=null){
            wrapper.like("create_time",new SimpleDateFormat("yyyy-MM-dd").format(work.getCreateTime()));
            work.setCreateTime(null);
        }
        IPage<Work> workIPage = getBaseMapper().selectPage(page, wrapper);
        return workIPage;
    }
    @CacheEvict(allEntries = true)
    @Override
    public boolean save(Work entity) {
        return super.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(Work entity) {
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}