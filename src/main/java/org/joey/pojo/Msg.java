package org.joey.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JoeyShelby
 * @date: 2022-05-15 09:26
 * 通用的处理返回数据
 */
public class Msg {
    private int code;
    private String msg;
    // 要响应的数据
    private Map<String,Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(403);
        result.setMsg("fail");
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
