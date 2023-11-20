package com.yifuyou.web.load;

public class LoadFileInfo {
    String fileName;

    long fileLength;

    String filePath;

    // 文件下载地址
    String from;

    private LoadFileInfo(){
    }

    public static LoadFileInfo Builder(String fileName, long fileLength, String filePath) {
        LoadFileInfo loadFileInfo = new LoadFileInfo();
        loadFileInfo.fileName=fileName;
        loadFileInfo.fileLength=fileLength;
        loadFileInfo.filePath=filePath;
        return loadFileInfo;
    }
}
