package com.yongfeng.qianfeng.breadhunter.sub.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RuleMyList {

    /**
     * msg : 操作成功
     * data : {"label":[{"name":"韩娱250","id":166,"type":3,"pic":"http://images11.app.happyjuzi.com/content/201606/22/5769f13700045.png!ac1.nw.webp","urlroute":"juzi://article/list/category?id=166&name=%25E9%259F%25A9%25E5%25A8%25B1250","issub":true},{"name":"LGBT","id":50,"type":3,"pic":"http://images11.app.happyjuzi.com/content/201606/03/57514fc759462.png!ac1.nw.webp","urlroute":"juzi://article/list/category?id=50&name=LGBT","issub":true}],"star":[{"name":"TFBOYS","id":79,"type":6,"pic":"http://images11.app.happyjuzi.com/upload/star/9bfbab21-1626-4d63-afc6-ad62bc550b0a.jpeg!ac1.nw.webp","urlroute":"juzi://star/detail?id=79&name=TFBOYS","issub":true,"ename":"TFBOYS"}]}
     * code : 1
     */

    private String msg;
    private DataBean data;
    private int code;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * name : 韩娱250
         * id : 166
         * type : 3
         * pic : http://images11.app.happyjuzi.com/content/201606/22/5769f13700045.png!ac1.nw.webp
         * urlroute : juzi://article/list/category?id=166&name=%25E9%259F%25A9%25E5%25A8%25B1250
         * issub : true
         */

        private List<LabelBean> label;
        /**
         * name : TFBOYS
         * id : 79
         * type : 6
         * pic : http://images11.app.happyjuzi.com/upload/star/9bfbab21-1626-4d63-afc6-ad62bc550b0a.jpeg!ac1.nw.webp
         * urlroute : juzi://star/detail?id=79&name=TFBOYS
         * issub : true
         * ename : TFBOYS
         */

        private List<StarBean> star;

        public List<LabelBean> getLabel() {
            return label;
        }

        public void setLabel(List<LabelBean> label) {
            this.label = label;
        }

        public List<StarBean> getStar() {
            return star;
        }

        public void setStar(List<StarBean> star) {
            this.star = star;
        }

        public static class LabelBean {
            private String name;
            private String id;
            private int type;
            private String pic;
            private String urlroute;
            private boolean issub;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrlroute() {
                return urlroute;
            }

            public void setUrlroute(String urlroute) {
                this.urlroute = urlroute;
            }

            public boolean isIssub() {
                return issub;
            }

            public void setIssub(boolean issub) {
                this.issub = issub;
            }
        }

        public static class StarBean {
            private String name;
            private int id;
            private int type;
            private String pic;
            private String urlroute;
            private boolean issub;
            private String ename;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrlroute() {
                return urlroute;
            }

            public void setUrlroute(String urlroute) {
                this.urlroute = urlroute;
            }

            public boolean isIssub() {
                return issub;
            }

            public void setIssub(boolean issub) {
                this.issub = issub;
            }

            public String getEname() {
                return ename;
            }

            public void setEname(String ename) {
                this.ename = ename;
            }
        }
    }
}
