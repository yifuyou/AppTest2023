package com.yifuyou.web.load;

import java.util.Observable;

public class LoadFileRecord extends Observable{
    private String name;
    private String path;
    private String url;
    private long sTime;
    private long lTime;
    private long fileLength;
    private long loadLength;
    private String state;

    public static LoadFileRecord Builder(String fileName, long fileLength, String filePath) {
        LoadFileRecord loadFileInfo = new LoadFileRecord();
        loadFileInfo.name=fileName;
        loadFileInfo.fileLength=fileLength;
        loadFileInfo.path=filePath;
        return loadFileInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getsTime() {
        return sTime;
    }

    public void setsTime(long sTime) {
        this.sTime = sTime;
    }

    public long getlTime() {
        return lTime;
    }

    public void setlTime(long lTime) {
        this.lTime = lTime;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public long getLoadLength() {
        return loadLength;
    }

    public void setLoadLength(long loadLength) {
        this.loadLength = loadLength;
        setChanged();
        notifyObservers(loadLength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadFileRecord that = (LoadFileRecord) o;
        return sTime == that.sTime && name.equals(that.name) && path.equals(that.path);
    }

    @Override
    public String toString() {
        return "LoadFileRecord{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", sTime=" + sTime +
                ", lTime=" + lTime +
                ", fileLength=" + fileLength +
                ", loadLength=" + loadLength +
                ", state='" + state + '\'' +
                '}';
    }
}
