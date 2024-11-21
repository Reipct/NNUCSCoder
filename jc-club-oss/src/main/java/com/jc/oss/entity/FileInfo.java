package com.jc.oss.entity;

/**
 * 文件类
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/19
 */
public class FileInfo {

    private String filename;

    private Boolean directoryFlag;

    private String etag;

    public String getFilename() {
        return filename;
    }

    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
