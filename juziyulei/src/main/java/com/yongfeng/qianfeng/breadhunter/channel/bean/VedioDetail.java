package com.yongfeng.qianfeng.breadhunter.channel.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class VedioDetail {

    /**
     * msg : 操作成功
     * data : {"article":{"id":78411,"replynum":26,"iscollected":false},"tags":[{"id":64,"issub":false}],"vote":[],"votegroup":[],"comment":{"type":1,"count":26,"list":[{"id":4911932,"user":{"id":3996916677714225,"name":"·嘿嘿","avatar":"http://images11.app.happyjuzi.com/usericonurl/900b0e92-06e3-4012-b37b-a0e3cf51ad5c!up1.webp","title":"橘子游客"},"content":"哈哈","isdigg":false,"publish_time":"3天前","timestamp":1468823962,"digg":0},{"id":4907788,"user":{"id":3947509152456517,"name":"ひぁかあ","avatar":"http://images11.app.happyjuzi.com/usericonurl/efa97b09-2d95-4e31-8c32-128885d0a1e6!up1.webp","title":"小小橘子"},"content":"我能说我被笑声感染了吗","isdigg":false,"publish_time":"4天前","timestamp":1468806490,"digg":2},{"id":4905967,"user":{"id":758835,"name":"阿恒恒恒恒呀","avatar":"http://images11.app.happyjuzi.com/usericonurl/de5df76c-b242-4c47-9bc1-623758492508.jpg!up1.webp","title":"橘子达人"},"content":"这个比较厉害😂😂😂😂😂","isdigg":false,"publish_time":"4天前","timestamp":1468773182,"digg":0}]},"attitude":{"count":189,"list":[{"id":20,"number":188},{"id":19,"number":1},{"id":17,"number":0},{"id":16,"number":0},{"id":15,"number":0},{"id":13,"number":0},{"id":14,"number":0},{"id":18,"number":0}]},"video":[{"url":"http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4","img":"http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4?vframe/jpg/offset/1/w/600/h/338"}],"isH5":false}
     * code : 1
     */

    private String msg;
    /**
     * article : {"id":78411,"replynum":26,"iscollected":false}
     * tags : [{"id":64,"issub":false}]
     * vote : []
     * votegroup : []
     * comment : {"type":1,"count":26,"list":[{"id":4911932,"user":{"id":3996916677714225,"name":"·嘿嘿","avatar":"http://images11.app.happyjuzi.com/usericonurl/900b0e92-06e3-4012-b37b-a0e3cf51ad5c!up1.webp","title":"橘子游客"},"content":"哈哈","isdigg":false,"publish_time":"3天前","timestamp":1468823962,"digg":0},{"id":4907788,"user":{"id":3947509152456517,"name":"ひぁかあ","avatar":"http://images11.app.happyjuzi.com/usericonurl/efa97b09-2d95-4e31-8c32-128885d0a1e6!up1.webp","title":"小小橘子"},"content":"我能说我被笑声感染了吗","isdigg":false,"publish_time":"4天前","timestamp":1468806490,"digg":2},{"id":4905967,"user":{"id":758835,"name":"阿恒恒恒恒呀","avatar":"http://images11.app.happyjuzi.com/usericonurl/de5df76c-b242-4c47-9bc1-623758492508.jpg!up1.webp","title":"橘子达人"},"content":"这个比较厉害😂😂😂😂😂","isdigg":false,"publish_time":"4天前","timestamp":1468773182,"digg":0}]}
     * attitude : {"count":189,"list":[{"id":20,"number":188},{"id":19,"number":1},{"id":17,"number":0},{"id":16,"number":0},{"id":15,"number":0},{"id":13,"number":0},{"id":14,"number":0},{"id":18,"number":0}]}
     * video : [{"url":"http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4","img":"http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4?vframe/jpg/offset/1/w/600/h/338"}]
     * isH5 : false
     */

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
         * id : 78411
         * replynum : 26
         * iscollected : false
         */

        private ArticleBean article;
        /**
         * type : 1
         * count : 26
         * list : [{"id":4911932,"user":{"id":3996916677714225,"name":"·嘿嘿","avatar":"http://images11.app.happyjuzi.com/usericonurl/900b0e92-06e3-4012-b37b-a0e3cf51ad5c!up1.webp","title":"橘子游客"},"content":"哈哈","isdigg":false,"publish_time":"3天前","timestamp":1468823962,"digg":0},{"id":4907788,"user":{"id":3947509152456517,"name":"ひぁかあ","avatar":"http://images11.app.happyjuzi.com/usericonurl/efa97b09-2d95-4e31-8c32-128885d0a1e6!up1.webp","title":"小小橘子"},"content":"我能说我被笑声感染了吗","isdigg":false,"publish_time":"4天前","timestamp":1468806490,"digg":2},{"id":4905967,"user":{"id":758835,"name":"阿恒恒恒恒呀","avatar":"http://images11.app.happyjuzi.com/usericonurl/de5df76c-b242-4c47-9bc1-623758492508.jpg!up1.webp","title":"橘子达人"},"content":"这个比较厉害😂😂😂😂😂","isdigg":false,"publish_time":"4天前","timestamp":1468773182,"digg":0}]
         */

        private CommentBean comment;
        /**
         * count : 189
         * list : [{"id":20,"number":188},{"id":19,"number":1},{"id":17,"number":0},{"id":16,"number":0},{"id":15,"number":0},{"id":13,"number":0},{"id":14,"number":0},{"id":18,"number":0}]
         */

        private AttitudeBean attitude;
        private boolean isH5;
        /**
         * id : 64
         * issub : false
         */

        private List<TagsBean> tags;
        private List<?> vote;
        private List<?> votegroup;
        /**
         * url : http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4
         * img : http://video01.app.happyjuzi.com/o_1ans7219e1au71ab51n8lcge14mm9.mp4?vframe/jpg/offset/1/w/600/h/338
         */

        private List<VideoBean> video;

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public AttitudeBean getAttitude() {
            return attitude;
        }

        public void setAttitude(AttitudeBean attitude) {
            this.attitude = attitude;
        }

        public boolean isIsH5() {
            return isH5;
        }

        public void setIsH5(boolean isH5) {
            this.isH5 = isH5;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<?> getVote() {
            return vote;
        }

        public void setVote(List<?> vote) {
            this.vote = vote;
        }

        public List<?> getVotegroup() {
            return votegroup;
        }

        public void setVotegroup(List<?> votegroup) {
            this.votegroup = votegroup;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public static class ArticleBean {
            private String id;
            private String replynum;
            private boolean iscollected;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getReplynum() {
                return replynum;
            }

            public void setReplynum(String replynum) {
                this.replynum = replynum;
            }

            public boolean isIscollected() {
                return iscollected;
            }

            public void setIscollected(boolean iscollected) {
                this.iscollected = iscollected;
            }
        }

        public static class CommentBean {
            private int type;
            private int count;
            /**
             * id : 4911932
             * user : {"id":3996916677714225,"name":"·嘿嘿","avatar":"http://images11.app.happyjuzi.com/usericonurl/900b0e92-06e3-4012-b37b-a0e3cf51ad5c!up1.webp","title":"橘子游客"}
             * content : 哈哈
             * isdigg : false
             * publish_time : 3天前
             * timestamp : 1468823962
             * digg : 0
             */

            private List<ListBean> list;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private int id;
                /**
                 * id : 3996916677714225
                 * name : ·嘿嘿
                 * avatar : http://images11.app.happyjuzi.com/usericonurl/900b0e92-06e3-4012-b37b-a0e3cf51ad5c!up1.webp
                 * title : 橘子游客
                 */

                private UserBean user;
                private String content;
                private boolean isdigg;
                private String publish_time;
                private int timestamp;
                private int digg;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public boolean isIsdigg() {
                    return isdigg;
                }

                public void setIsdigg(boolean isdigg) {
                    this.isdigg = isdigg;
                }

                public String getPublish_time() {
                    return publish_time;
                }

                public void setPublish_time(String publish_time) {
                    this.publish_time = publish_time;
                }

                public int getTimestamp() {
                    return timestamp;
                }

                public void setTimestamp(int timestamp) {
                    this.timestamp = timestamp;
                }

                public int getDigg() {
                    return digg;
                }

                public void setDigg(int digg) {
                    this.digg = digg;
                }

                public static class UserBean {
                    private long id;
                    private String name;
                    private String avatar;
                    private String title;

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }
                }
            }
        }

        public static class AttitudeBean {
            private int count;
            /**
             * id : 20
             * number : 188
             */

            private List<ListBean> list;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private int id;
                private int number;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }
            }
        }

        public static class TagsBean {
            private int id;
            private boolean issub;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIssub() {
                return issub;
            }

            public void setIssub(boolean issub) {
                this.issub = issub;
            }
        }

        public static class VideoBean {
            private String url;
            private String img;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}

