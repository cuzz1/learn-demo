package com.cuzz.handler3;

/**
 * @Author: cuzz
 * @Date: 2019/1/22 16:00
 * @Description: 这是一个关于 Person 的协议
 */
public class PersonProtocol {

    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
