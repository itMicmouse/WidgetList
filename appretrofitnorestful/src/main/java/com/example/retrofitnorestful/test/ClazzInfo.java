package com.example.retrofitnorestful.test;

/**
 * Created by yakun on 2016/5/14.
 */
public class ClazzInfo {

    /**
     * ret : 0
     * msg : 成功
     * data : {"uid":1,"name":"kkmike999"}
     */

    private int ret;
    private String msg;
    /**
     * uid : 1
     * name : kkmike999
     */

    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int uid;
        private String name;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
