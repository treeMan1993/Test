package com.example.test.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * Created by SunJH on 2019/6/3
 **/
public class QRCodeUtil {
    /**
     * 创建二维码方法
     *
     * @param content content 字符串内容
     * @param width   位图宽度
     * @param height  位图高度
     * @return
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height) {
        return createQRCodeBitmap(content, width, height, "utf-8", "L", "2", Color.BLACK, Color.WHITE);
    }

    /**
     * @param content          二维码字符串内容
     * @param width            二维码宽度
     * @param height           二维码高度
     * @param character_set    字符集编码格式(支持格式:{@link com.google.zxing.common.CharacterSetECI})，传null时，默认使用 "ISO-8859-1"
     * @param error_correction 容错级别（支持级别:{@link com.google.zxing.qrcode.decoder.ErrorCorrectionLevel}）,传null时，源码默认使用"L"
     * @param margin           空白边距，(可以修改，要求：整形且>=0) 传null时，默认使用"4".
     * @param color_black      黑色块的自定义色值   默认使用系统的 FF000000
     * @param color_white      白色块的自定义色值   默认使用系统的 FFFFFFFF
     * @return                 正常情况下，会返回一个bitmap类型的二维码，如果参数不合理，会返回null
     */
    public static Bitmap createQRCodeBitmap(String content, int width, int height, @Nullable String character_set, @Nullable String error_correction, @Nullable String margin,
                                            @ColorInt int color_black, @ColorInt int color_white) {
        if (TextUtils.isEmpty(content)) return null;
        if (width <= 0 && height <= 0) return null;
        if (TextUtils.isEmpty(character_set)) return null;
        if (TextUtils.isEmpty(error_correction)) return null;
        try {
            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, character_set);
            hints.put(EncodeHintType.ERROR_CORRECTION, error_correction);
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            /**
             * 创建数组，并根据BitMatrix(位矩阵)对象为数组元素赋颜色值
             */
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0;x<width;x++)
                if(bitMatrix.get(x,y)){
                    pixels[y*width+x] = color_black;
                }else{
                    pixels[y*width+x] = color_white;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
