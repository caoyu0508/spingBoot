package com.safc.springcloud.springCoudClientTest.modules.common.vo;

public class Result <T>{
    private  int status;
    private String message;
    private T object;

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    /**
     * @Description ResultStatus
     * @Author HymanHu 枚举，把固定的常量值变为有属性的常量值
     * @Date 2020/8/12 10:02
     */
    public enum ResultStatus{
        SUCCESS(200),fAILD(500);
        public int status;
        ResultStatus(int status) {
            this.status = status;
        }
    }


    /*举例说明：为什么用带属性的常量值，用网页的大小图片说明，如果用固定的常量值，那么要规定的很多*/
    private final static int BIG_IMAGE_WIDTH = 1000;
    private final static int BIG_IMAGE_HEIGHT = 1000;
    private final static int MIDDLE_IMAGE_WIDTH = 500;
    private final static int MIDDLE_IMAGE_HEIGHT = 500;
    private final static int SMALL_IMAGE_WIDTH = 100;
    private final static int SMALL_IMAGE_HEIGHT = 100;

    public enum IMAGE{
        //大图片尺寸
        BIG_IMAGE(1000, 1000, 100),
        //中图片尺寸
        MIDDEL_IMAGE(500, 500, 50),
        //小图片尺寸
        SMALL_IMAGE(100, 100, 20);
        public int width;
        public int height;
        public int size;
        IMAGE(int width, int height, int size) {
            this.width = width;
            this.height = height;
            this.size = size;
        }
    }
}
