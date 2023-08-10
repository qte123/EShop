package cn.itcast.eshop.common.util;

import java.io.*;
import java.net.URLDecoder;

/**
 * 文件操作工具类
 *
 * @author itcast
 *
 */
public class FileUtil {

    /**
     * 字节缓冲流拷贝文件
     * @param file
     * @param destDir
     * @throws IOException
     */
    public static void copyFileByStream(File file, String destDir) throws IOException {
        if(file != null) {
            File dir = new File(destDir); // 目标目录
            if(!dir.exists()) {
                dir.mkdirs(); // 创建目录
            }
            if(file.isFile()) {
                String fileName = file.getName(); // 文件名称
                File dest = new File(dir, fileName); // 目标文件对象
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));

                int len = 0;
                byte[] bs = new byte[2048];
                while((len = bis.read(bs)) != -1) {
                    bos.write(bs, 0, len);
                    bos.flush();
                }
                bos.close();
                bis.close();
            } else if(file.isDirectory()) { // 在目标位置创建目录
                File dest = new File(dir, file.getName());
                dest.mkdir();
            }
        }
    }

    /**
     * 使用字符缓冲流对象读取文件内容并转成字符串返回
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readByBuffered(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        // 使用ClassLoader加载资源，路径从src下开始：db/db_user.txt
       filePath = FileUtil.class.getClassLoader().getResource(filePath).getFile();
        String filePathUTF8= URLDecoder.decode(filePath,"UTF-8"); //将字符串中的utf8编码成汉字
        File file = new File(filePathUTF8);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine(); // 按行读取
            while (str != null) {
                sb.append(str);
                str = br.readLine();
            }
            br.close();
            return sb.toString();
        }
        return null;
    }

    /**
     * 将字符串数据写入指定文件。写入时会先清空原文件内容
     * @param data 要写入的数据
     * @param destFilePath 文件路径 + 文件名
     * @throws IOException
     */
    public static void writeByBuffered(String data, String destFilePath)
            throws IOException {
        File destFile = new File(destFilePath);
        if(!destFile.exists()) {
            File parent = destFile.getParentFile();
            if(!parent.exists())
                parent.mkdirs();
            destFile.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));
        bw.write(data);
        bw.close();
    }

}
