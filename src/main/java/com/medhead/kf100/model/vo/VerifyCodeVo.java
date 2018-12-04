package com.medhead.kf100.model.vo;

public class VerifyCodeVo {

    private String vid;

    private String base64Image;

    public VerifyCodeVo(String vid, String base64Image) {
        this.vid = vid;
        this.base64Image = base64Image;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
