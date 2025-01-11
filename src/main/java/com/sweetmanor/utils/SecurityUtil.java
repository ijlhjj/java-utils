package com.sweetmanor.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 安全工具类
 * 目前只支持AES和RSA两种加密方式
 *
 * @author ijlhjj
 * @version 1.0 2016-11-22
 */
public final class SecurityUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static final String AES = "AES";// 对称加密
    public static final String RSA = "RSA";// 非对称加密

    /**
     * 私有构造方法，防止类被实例化
     */
    private SecurityUtil() {
    }

    /**
     * 使用 MD5 算法计算字符串的哈希值
     *
     * @param data 要计算哈希值的字符串
     * @return 计算出的 MD5 哈希值，以十六进制字符串形式表示
     */
    public static String md5(String data) {
        // 使用 Apache Commons Codec 库的 DigestUtils 类计算字符串的 MD5 哈希值
        return DigestUtils.md5Hex(data);
    }

    /**
     * 计算文件的 MD5 哈希值
     *
     * @param file 要计算哈希值的文件
     * @return 计算出的 MD5 哈希值，以十六进制字符串形式表示
     */
    public static String md5(File file) {
        try {
            // 使用 Apache Commons Codec 库的 DigestUtils 类计算文件的 MD5 哈希值
            return DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("计算文件md5值失败：", e);
        }

        // 如果发生异常，返回 null
        return null;
    }

    /**
     * 使用 SHA-1 算法计算字符串的哈希值
     *
     * @param data 要计算哈希值的字符串
     * @return 计算出的 SHA-1 哈希值，以十六进制字符串形式表示
     */
    public static String sha(String data) {
        // 使用 Apache Commons Codec 库的 DigestUtils 类计算字符串的 SHA-1 哈希值
        return DigestUtils.sha1Hex(data);
    }

    /**
     * 计算文件的 SHA-1 哈希值
     *
     * @param file 要计算哈希值的文件
     * @return 计算出的 SHA-1 哈希值，以十六进制字符串形式表示
     */
    public static String sha(File file) {
        try {
            // 使用 Apache Commons Codec 库的 DigestUtils 类计算文件的 SHA-1 哈希值
            return DigestUtils.sha1Hex(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("计算文件sha值失败：", e);
        }

        // 如果发生异常，返回 null
        return null;
    }

    /**
     * 使用 AES 加密算法对字符串进行加密
     *
     * @param content 待加密的字符串
     * @param key     加密密钥
     * @return 加密后的字节数组
     */
    public static byte[] aesEncode(String content, SecretKey key) {
        // 调用 encode 方法，对字符串进行 AES 加密
        return encode(content.getBytes(), AES, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 AES 加密算法对字节数组进行解密
     *
     * @param content 待解密的字节数组
     * @param key     解密密钥
     * @return 解密后的字节数组
     */
    public static byte[] aesDecode(byte[] content, SecretKey key) {
        // 调用 encode 方法，对字节数组进行 AES 解密
        return encode(content, AES, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对字符串进行加密
     *
     * @param content   待加密的字符串
     * @param publicKey 加密公钥
     * @return 加密后的字节数组
     */
    public static byte[] rsaEncode(String content, RSAPublicKey publicKey) {
        // 调用 encode 方法，对字符串进行 RSA 加密
        return encode(content.getBytes(), RSA, publicKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对字节数组进行解密
     *
     * @param content   待解密的字节数组
     * @param publicKey 解密公钥
     * @return 解密后的字节数组
     */
    public static byte[] rsaDecode(byte[] content, RSAPublicKey publicKey) {
        // 调用 encode 方法，对字节数组进行 RSA 解密
        return encode(content, RSA, publicKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对字符串进行加密
     *
     * @param content    待加密的字符串
     * @param privateKey 加密私钥
     * @return 加密后的字节数组
     */
    public static byte[] rsaEncode(String content, RSAPrivateKey privateKey) {
        // 调用 encode 方法，对字符串进行 RSA 加密
        return encode(content.getBytes(), RSA, privateKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对字节数组进行解密
     *
     * @param content    待解密的字节数组
     * @param privateKey 解密私钥
     * @return 解密后的字节数组
     */
    public static byte[] rsaDecode(byte[] content, RSAPrivateKey privateKey) {
        // 调用 encode 方法，对字节数组进行 RSA 解密
        return encode(content, RSA, privateKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 对字节数组进行加密或解密操作
     *
     * @param content   待加密或解密的字节数组
     * @param algorithm 加密算法，支持 AES 和 RSA
     * @param key       加密密钥，支持 SecretKey、RSAPublicKey 和 RSAPrivateKey
     * @param isEncode  加密或解密模式，Cipher.ENCRYPT_MODE 表示加密，Cipher.DECRYPT_MODE 表示解密
     * @return 加密或解密后的字节数组，如发生异常则返回 null
     */
    private static byte[] encode(byte[] content, String algorithm, Key key, int isEncode) {
        // 参数检查
        if (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE)
            return null;

        if (algorithm == null || (!algorithm.equalsIgnoreCase(AES) && !algorithm.equalsIgnoreCase(RSA)))
            return null;

        if (!(key instanceof RSAPublicKey || key instanceof RSAPrivateKey || key instanceof SecretKey))
            return null;

        try {
            Cipher cipher = Cipher.getInstance(algorithm);// 创建加密、解密工具对象
            cipher.init(isEncode, key);
            return cipher.doFinal(content);
        } catch (Exception e) {
            logger.error("加密/解密失败：", e);
        }

        return null;
    }

    /**
     * 使用 AES 加密算法对文件进行加密
     *
     * @param srcFile    待加密文件的路径
     * @param targetFile 加密后文件的存储路径
     * @param key        用于加密的密钥
     */
    public static void aesEncode(String srcFile, String targetFile, SecretKey key) {
        // 调用 encode 方法，对文件进行 AES 加密
        encode(srcFile, targetFile, AES, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 AES 加密算法对文件进行解密
     *
     * @param srcFile    待解密文件的路径
     * @param targetFile 解密后文件的存储路径
     * @param key        用于解密的密钥
     */
    public static void aesDecode(String srcFile, String targetFile, SecretKey key) {
        // 调用 encode 方法，对文件进行 AES 解密
        encode(srcFile, targetFile, AES, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对文件进行加密
     *
     * @param srcFile    待加密文件的路径
     * @param targetFile 加密后文件的存储路径
     * @param publicKey  用于加密的公钥
     */
    public static void rsaEncode(String srcFile, String targetFile, RSAPublicKey publicKey) {
        // 调用 encode 方法，对文件进行 RSA 加密
        encode(srcFile, targetFile, RSA, publicKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对文件进行解密
     *
     * @param srcFile    待解密文件的路径
     * @param targetFile 解密后文件的存储路径
     * @param publicKey  用于解密的公钥
     */
    public static void rsaDecode(String srcFile, String targetFile, RSAPublicKey publicKey) {
        // 调用 encode 方法，对文件进行 RSA 解密
        encode(srcFile, targetFile, RSA, publicKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对文件进行加密
     *
     * @param srcFile    待加密文件的路径
     * @param targetFile 加密后文件的存储路径
     * @param privateKey 用于加密的私钥
     */
    public static void rsaEncode(String srcFile, String targetFile, RSAPrivateKey privateKey) {
        // 调用 encode 方法，对文件进行 RSA 加密
        encode(srcFile, targetFile, RSA, privateKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用 RSA 加密算法对文件进行解密
     *
     * @param srcFile    待解密文件的路径
     * @param targetFile 解密后文件的存储路径
     * @param privateKey 用于解密的私钥
     */
    public static void rsaDecode(String srcFile, String targetFile, RSAPrivateKey privateKey) {
        // 调用 encode 方法，对文件进行 RSA 解密
        encode(srcFile, targetFile, RSA, privateKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 对文件进行加密或解密操作
     *
     * @param srcFile    待加密或解密的源文件路径
     * @param targetFile 加密或解密后的目标文件路径
     * @param algorithm  加密算法，支持 AES 和 RSA
     * @param key        加密密钥，支持 SecretKey、RSAPublicKey 和 RSAPrivateKey
     * @param isEncode   加密或解密模式，Cipher.ENCRYPT_MODE 表示加密，Cipher.DECRYPT_MODE 表示解密
     */
    private static void encode(String srcFile, String targetFile, String algorithm, Key key, int isEncode) {
        // 参数检查
        if (isEncode != Cipher.ENCRYPT_MODE && isEncode != Cipher.DECRYPT_MODE)
            return;

        if (algorithm == null || (!algorithm.equalsIgnoreCase(AES) && !algorithm.equalsIgnoreCase(RSA)))
            return;

        if (!(key instanceof RSAPublicKey || key instanceof RSAPrivateKey || key instanceof SecretKey))
            return;

        try (FileChannel inChannel = new FileInputStream(srcFile).getChannel();
             FileChannel outChannel = new FileOutputStream(targetFile).getChannel()) {
            // 创建加密、解密工具对象
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(isEncode, key);

            ByteBuffer inBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的输入缓冲区
            ByteBuffer outBuffer = ByteBuffer.allocate(1024);// 创建一个同样大小的输出缓冲区
            // 循环读取文件内容
            while (inChannel.read(inBuffer) != -1) {
                inBuffer.flip();
                outBuffer.clear();
                cipher.doFinal(inBuffer, outBuffer);// 执行加密/解密操作
                outBuffer.flip();
                outChannel.write(outBuffer);
                inBuffer.clear();
            }
        } catch (Exception e) {
            logger.error("加密/解密文件失败：", e);
        }
    }

    /**
     * 生成对称密钥的方法
     *
     * @param algorithm 生成密钥的算法
     * @param filename  密钥保存的文件名
     * @return 成功返回true，失败返回false
     */
    public static boolean createSecretKey(String algorithm, String filename) {
        try {
            // 创建密钥生成器
            KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
            // 生成密钥
            SecretKey key = keygen.generateKey();
            // 将密钥写入文件
            ObjectAccessUtil.writeToFile(key, filename);

            return true;
        } catch (Exception e) {
            logger.error("生成密钥失败：", e);
        }

        return false;
    }

    /**
     * 生成非对称密钥对的方法
     *
     * @param algorithm      生成密钥对的算法
     * @param privateKeyFile 私钥保存的文件名
     * @param publicKeyFile  公钥保存的文件名
     * @return 成功返回true，失败返回false
     */
    public static boolean createKeyPair(String algorithm, String privateKeyFile, String publicKeyFile) {
        try {
            // 创建非对称密钥生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
            // 初始化密钥生成器，密钥大小为1024位
            keyPairGen.initialize(1024);

            // 生成非对称密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 获取私钥
            PrivateKey privateKey = keyPair.getPrivate();
            // 获取公钥
            PublicKey publicKey = keyPair.getPublic();

            // 将私钥写入文件
            ObjectAccessUtil.writeToFile(privateKey, privateKeyFile);
            // 将公钥写入文件
            ObjectAccessUtil.writeToFile(publicKey, publicKeyFile);

            return true;
        } catch (Exception e) {
            logger.error("生成非对称密钥失败：", e);
        }

        return false;
    }

}
