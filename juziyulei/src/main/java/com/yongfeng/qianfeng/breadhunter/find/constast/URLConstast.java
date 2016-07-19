package com.yongfeng.qianfeng.breadhunter.find.constast;

/**
 * Created by 70700 on 2016/7/14.
 */
public class URLConstast {
    //发现部分注释
    //搜索拼接
    public static final String SEARCH1 ="http://api.app.happyjuzi.com/search/list/article?res=1080x1920&accesstoken=2a98cad272f183550f0acab7c98f8b66&pf=android&uid=3996811068993404&page=1&ts=1468567800&type=0&channel=qihoo360&net=wifi&mac=08-00-27-b0-38-47&keyword=";
    public static final String SEARCH2="&ver=3.0.0";
   // 搜索初始key
    public static final String HOTSEARCHKEY ="http://api.app.happyjuzi.com/search/index?res=1080x1920&accesstoken=2a98cad272f183550f0acab7c98f8b66&pf=android&uid=3996811068993404&channel=qihoo360&net=wifi&mac=08-00-27-b0-38-47&ver=3.0.";
    //明星排行榜
    public static final String STARRANK ="http://m.happyjuzi.com/event/more/top.html?res=1080x1920&ver=3.0.0&ov=5.1&channel=qihoo360&mid=00000000000000&accesstoken=b61f3d841cd78a6e39eacce9ead6c510&uuid=e1de2015cd19a8b&islogin=0&uid=3997824563913483&pf=android&ph=genymotion5.1_151117_200001&net=wifi&cid=62fc7ef13786fdb3cbbccea44e310ebf";
    //更多热文
    public static final String MOREHOT = "http://api.app.happyjuzi.com/findmore/hotnotice?uid=3997824563913483&res=720x1280&ver=3.0.0&pf=android&channel=qihoo360&accesstoken=b61f3d841cd78a6e39eacce9ead6c510&page=1&net=wifi&ts=0";
    //点击查看更多明星集合
    public static final String STARS = "http://api.app.happyjuzi.com/star/starlist?res=1080x1920&accesstoken=2a98cad272f183550f0acab7c98f8b66&pf=android&uid=3996811068993404&page=1&ts=0&channel=qihoo360&net=wifi&size=30&mac=08-00-27-b0-38-47&ver=3.0.0";
    //轮播广告前半段需加转码后半段实现广告点击跳转
    public static final String BANNERSPHLE = "http://m.happyjuzi.com/event/more/special.html";
    //大家都在看点击详情
    public static final String WATCHID = "http://api.app.happyjuzi.com/wapsite/article/content?view=1&id=";
    //点击明星进入详情
    public static final String STARGRID = "http://m.happyjuzi.com/event/more/start.html?id=";
    //大家都在看内容刷新拼接
    public static final String CHANGEWEWATCH1 = "http://api.app.happyjuzi.com/findmore/wewatch?res=1080x1920&accesstoken=2a98cad272f183550f0acab7c98f8b66&pf=android&uid=3996811068993404&page=";
    public static final String CHANGEWEWATCH2 = "&channel=qihoo360&net=wifi&mac=08-00-27-b0-38-47&ver=3.0.0";
    //发现总数据集合
    public static final String BANNER = "http://api.app.happyjuzi.com/findmore/home?res=1080x1920&accesstoken=2a98cad272f183550f0acab7c98f8b66&pf=android&uid=3996811068993404&channel=qihoo360&net=wifi&mac=08-00-27-b0-38-47&ver=3.0.0";
}
