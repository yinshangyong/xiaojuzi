package com.yongfeng.qianfeng.breadhunter.find.bean;

import java.util.List;

/**
 * Created by 70700 on 2016/7/15.
 */
public class Change {

    /**
     * msg : 操作成功
     * data : [{"id":1776,"title":"捕获一只小小权志龙！","urlroute":"juzi://article/detail/native?id=77990"},{"id":1753,"title":"测你是爱豆的几级花痴","urlroute":"juzi://article/detail/native?id=77977"},{"id":1749,"title":"情侣摩托车上花式接吻","urlroute":"juzi://article/detail/native?id=77934"},{"id":1750,"title":"BIGBANG上榜福布斯","urlroute":"juzi://article/detail/native?id=77940"},{"id":1745,"title":"王源王俊凯演技惊喜","urlroute":"juzi://article/detail/native?id=77876"},{"id":1143,"title":"6月美妆新品惊艳来袭","urlroute":"juzi://article/detail/native?id=75249"}]
     * code : 1
     */

    private String msg;
    private int code;
    /**
     * id : 1776
     * title : 捕获一只小小权志龙！
     * urlroute : juzi://article/detail/native?id=77990
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String title;
        private String urlroute;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrlroute() {
            return urlroute;
        }

        public void setUrlroute(String urlroute) {
            this.urlroute = urlroute;
        }
    }
}
