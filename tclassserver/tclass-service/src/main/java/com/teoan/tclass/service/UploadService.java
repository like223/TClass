package com.teoan.tclass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * (Upload)表服务接口
 *
 * @author Teoan
 * @since 2020-07-25 10:14:50
 */
public interface UploadService extends IService<Upload> {

    /**
     * 上传文件
     *
     * @param wId 作业id
     * @param sId 学号
     * @param file 上传的文件
     */
    void uploadFile(Integer wId, Integer sId, MultipartFile file);

    /**
     * 检查用户是否已上传过文件
     * @param wId 作业id
     * @param sId 学号
     * @return 若存在返回文件信息 否则返回null
     */
    Upload isUploadedWorkFile(Integer wId,Integer sId);


    /**
     * 删除上传的文件
     * @param wId 作业Id
     * @param sId 学号
     * @param fileName 文件名
     */
    boolean deleteUploadFile(Integer wId,Integer sId,String fileName);


    /**
     * 根据作业id获取上传文件的信息
     * @param current 分页对象
     * @param size 查询数据量
     * @param wId 作业id
     * @return upload数据
     */
    IPage<Upload> getUploadPageByWId(Long current, Long size,Integer wId);

    /**
     * 根据作业id删除上传信息
     * @param wId 作业id
     * @return 是否成功
     */
    boolean deleteUploadByWId(Integer wId);
}