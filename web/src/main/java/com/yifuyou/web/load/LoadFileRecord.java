package com.yifuyou.web.load;

import com.yifuyou.web.Constants;

import java.util.Observable;


public class LoadFileRecord extends Observable {
    private String name;
    private String path;
    private String url;
    private long sTime;
    private long lTime;
    private long fileLength;
    private long loadLength;
    private String state;


    /**
     * Builder load file record.
     *
     * @param fileName   the file name
     * @param fileLength the file length
     * @param filePath   the file path
     * @return the load file record
     */
    public static LoadFileRecord Builder(String fileName, long fileLength, String filePath) {
        LoadFileRecord loadFileInfo = new LoadFileRecord();
        loadFileInfo.name = fileName;
        loadFileInfo.fileLength = fileLength;
        loadFileInfo.path = filePath;
        return loadFileInfo;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    public boolean isFinished() {
        return Constants.STATE_STRING_LOAD_SUCCESS.equals(state);
    }

    public boolean isLoading() {
        return Constants.STATE_STRING_LOADING.equals(state);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public long getsTime() {
        return sTime;
    }

    /**
     * Sets time.
     *
     * @param sTime the s time
     */
    public void setsTime(long sTime) {
        this.sTime = sTime;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public long getlTime() {
        return lTime;
    }

    /**
     * Sets time.
     *
     * @param lTime the l time
     */
    public void setlTime(long lTime) {
        this.lTime = lTime;
    }

    /**
     * Gets file length.
     *
     * @return the file length
     */
    public long getFileLength() {
        return fileLength;
    }

    /**
     * Sets file length.
     *
     * @param fileLength the file length
     */
    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    /**
     * Gets load length.
     *
     * @return the load length
     */
    public long getLoadLength() {
        return loadLength;
    }

    /**
     * Sets load length.
     *
     * @param loadLength the load length
     */
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
