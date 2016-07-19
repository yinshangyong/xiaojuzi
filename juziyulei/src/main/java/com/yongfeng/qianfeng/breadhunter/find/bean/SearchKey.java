package com.yongfeng.qianfeng.breadhunter.find.bean;

import java.util.List;

/**
 * Created by 70700 on 2016/7/19.
 */
public class SearchKey {
    /**
     * msg : 操作成功
     * data : {"list":[{"keyword":"短发"},{"keyword":"扎马尾"},{"keyword":"赵丽颖"},{"keyword":"鹿晗"},{"keyword":"薛之谦"},{"keyword":"张艺兴"}]}
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
         * keyword : 短发
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String keyword;

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }
        }
    }
}
