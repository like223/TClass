package com.teoan.tclass.user.service;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Teoan
 * @description Fdfs服务类
 * @date 2021/6/17 15:44
 */
@Service
public class FdfsService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传
     * @param bytes 文件字节
     * @param fileSize 文件大小
     * @param extension 文件拓展名
     * @return 文件组和文件名
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        // 元数据
        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        metaDataSet.add(new MetaData("dateTime", LocalDateTime.now().toString()));
        StorePath storePath = fastFileStorageClient.uploadFile(bais, fileSize, extension, metaDataSet);
        return storePath.getFullPath();
    }

    /**
     * 上传用户头像文件
     * @param file 头像文件
     * @param width 缩略图宽
     * @param height 缩略图长
     * @param extName 文件扩展名
     * @return 文件全路径
     */
    public String uploadUserAvatarImageFile(MultipartFile file,int width, int height,String extName) throws IOException {
        ThumbImage thumbImage = new ThumbImage(width,height);
        // 元数据
        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        metaDataSet.add(new MetaData("dateTime", LocalDateTime.now().toString()));
        FastImageFile imageFile = new FastImageFile(file.getInputStream(),file.getSize(),extName,metaDataSet,thumbImage);
        StorePath storePath = fastFileStorageClient.uploadImage(imageFile);
        return storePath.getFullPath();
    }



    /**
     * 下载文件
     * @param filePath 文件路径（组名+文件名）
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String filePath) throws IOException {
        byte[] bytes = null;
        if (StringUtils.isNotBlank(filePath)) {
            String group = filePath.substring(0, filePath.indexOf("/"));
            String path = filePath.substring(filePath.indexOf("/") + 1);
            DownloadByteArray byteArray = new DownloadByteArray();
            bytes = fastFileStorageClient.downloadFile(group, path, byteArray);
        }
        return bytes;
    }


    /**
     * 删除文件
     * @param filePath 文件路径（组名+文件名）
     */
    public void deleteFile(String filePath) {
        if (StringUtils.isNotBlank(filePath)) {
            fastFileStorageClient.deleteFile(filePath);
        }
    }

}
