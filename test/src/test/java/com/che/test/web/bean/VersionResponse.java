package com.che.test.web.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VersionResponse extends BaseResponse {
    private int versionCode;//版本号
    private String url;//下载地址
    private String desc;//版本描述
    private boolean mustInstall;//是否强制更新
}