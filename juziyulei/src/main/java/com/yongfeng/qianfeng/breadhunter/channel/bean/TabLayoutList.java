package com.yongfeng.qianfeng.breadhunter.channel.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class TabLayoutList {

    /**
     * msg : 操作成功
     * data : [{"id":0,"name":"推荐","flag":6,"pic":"http://images11.app.happyjuzi.com/content/201604/29/57231670d9eab.png"},{"id":27,"name":"八卦","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cbf1be89f.png"},{"id":26,"name":"时尚","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc02e83b6.png"},{"id":61,"name":"生活","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc116e5b8.png"},{"id":32,"name":"影视","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc23e054a.png"},{"id":102,"name":"美妆","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc3892550.png"},{"id":95,"name":"互动","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc4bcc795.png"},{"id":62,"name":"视频","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc5b118d0.png","flag":2},{"id":91,"name":"Gif","pic":"http://images11.app.happyjuzi.com/content/201605/23/5742cc6bab582.png","flag":12}]
     * code : 1
     */

    private String msg;
    private int code;
    /**
     * id : 0
     * name : 推荐
     * flag : 6
     * pic : http://images11.app.happyjuzi.com/content/201604/29/57231670d9eab.png
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
        private String id;
        private String name;
        private int flag;
        private String pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
