package com.medhead.kf100.web.common;

import com.medhead.kf100.common.dto.ResponseResult;
import com.medhead.kf100.common.util.contant.CommonConstants;
import com.medhead.kf100.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/upload")
@Api(tags = "上传")
public class UploadController {

    private static final String TYPE_JPG = "jpg";
    private static final String TYPE_GIF = "gif";
    private static final String TYPE_PNG = "png";
    private static final String TYPE_BMP = "bmp";
    private static final String TYPE_UNKNOWN = "unknown";

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if(src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if(hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件流判断图片类型
     *
     * @param fis 文件流
     * @return jpg/png/gif/bmp
     */
    private String getPicType(InputStream fis) throws IOException {
        if(fis == null) {
            return TYPE_UNKNOWN;
        }
        byte[] b = new byte[4];
        int read = fis.read(b, 0, b.length);
        String type = Objects.requireNonNull(bytesToHexString(b)).toUpperCase();
        fis.close();
        if(type.contains("FFD8FF")) {
            return TYPE_JPG;
        } else if(type.contains("89504E47")) {
            return TYPE_PNG;
        } else if(type.contains("47494638")) {
            return TYPE_GIF;
        } else if(type.contains("424D")) {
            return TYPE_BMP;
        } else {
            return TYPE_UNKNOWN;
        }

    }


    @PostMapping("/image")
    @ResponseBody
    @ApiOperation(value = "上传图片", notes = "上传成功返回图片名")
    @ApiImplicitParam(name = "file", value = "图片文件", dataType = "query")
    public ResponseResult uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String imagePath = "./upload/image";
            String parentPath = ResourceUtils.getURL(imagePath).getPath();
            String picType = getPicType(file.getInputStream());
            if(TYPE_UNKNOWN.equals(picType)) {
                throw new BusinessException(CommonConstants.RESPONSE_BAD_REQUEST_CODE, "未知图片类型");
            }
            //上传到服务器的路径
            File tempFile = new File(parentPath);
            if(!tempFile.exists()) {
                tempFile.mkdirs();
            }
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString().replaceAll("-", "") + "." + picType;
            File deskFile = new File(parentPath + File.separator + fileName);
            file.transferTo(deskFile);
            return ResponseResult.success(fileName);
        } else {
            return ResponseResult.badRequest("上传文件为空");
        }
    }

}
