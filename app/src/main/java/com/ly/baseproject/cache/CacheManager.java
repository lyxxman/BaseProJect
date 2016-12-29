package com.ly.baseproject.cache;

import android.content.Context;


import com.ly.baseproject.utils.TDevice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CacheManager {

    // wifi缓存时间为5分钟
    private static long wifi_cache_time = 5 * 60 * 1000;
    // 其他网络环境为1小时
    private static long other_cache_time = 60 * 60 * 1000;

    /**
     * 保存对象
     *
     * @param ser
     * @param file
     * @throws IOException
     */
    public static boolean saveObject(Context context, Serializable ser,
                                     String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public static void deleteObject(Context context, String fileName) {
        String filePath = context.getFilesDir().getPath() + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Serializable readObject(Context context, String file) {
        if (!isExistDataCache(context, file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public static boolean isExistDataCache(Context context, String cachefile) {
        if (context == null)
            return false;
        boolean exist = false;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 缓存是否还有效
     *
     * @param context   上下文
     * @param cachefile 缓存名称
     * @return 是否在有效期 true 有效 false 无效
     */
    public static boolean isCacheDataOnTime(Context context, String cachefile) {
        File data = context.getFileStreamPath(cachefile);
        if (!data.exists()) {
            return false; //无效
        }
        long existTime = System.currentTimeMillis() - data.lastModified();
        boolean failure = false; //无效
        if (TDevice.isWifiOpen()) { //wifi模式
            failure = existTime < wifi_cache_time;
        } else { //3g、4g模式
            failure = existTime < other_cache_time;
        }
        return failure;
    }
}
