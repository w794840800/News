package comn.example.administrator.news.jean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class zhihu {


    /**
     * date : 20170406
     * stories : [{"images":["https://pic3.zhimg.com/v2-88f95a5df3b15df1890c5cac4807a3e6.jpg"],"type":0,"id":9339661,"ga_prefix":"040617","title":"一边集体当网红一边拿冠军，国乒队靠的是什么？"},{"title":"533 万年前，一场大洪水拯救了曾经干涸的地中海","ga_prefix":"040616","images":["https://pic2.zhimg.com/v2-b4ef0f1b91a82b84dd4f121415bb00f5.jpg"],"multipic":true,"type":0,"id":9339626},{"title":"看过很多菜谱依然做不好一盘鱼香肉丝的你，需要这篇教程","ga_prefix":"040615","images":["https://pic3.zhimg.com/v2-b4ea15b47ccf8b4401994aeb4a9d6256.jpg"],"multipic":true,"type":0,"id":9338751},{"images":["https://pic4.zhimg.com/v2-78230126b6bafc969d2744f351c1b08b.jpg"],"type":0,"id":9340102,"ga_prefix":"040614","title":"人工智能要和人类打德州扑克，这场比赛注定很精彩"},{"images":["https://pic4.zhimg.com/v2-2e585eb3c61ccd0d8a543067667bdd67.jpg"],"type":0,"id":9340074,"ga_prefix":"040613","title":"职人介绍所：时颖、邓柯，他们说的比很多人唱的还好听"},{"images":["https://pic1.zhimg.com/v2-11e8b495046d3237d5ac7b6095ef9efc.jpg"],"type":0,"id":9336323,"ga_prefix":"040613","title":"如何分析中国手游市场上网易和腾讯两强争霸的局面？"},{"images":["https://pic4.zhimg.com/v2-23b71d164e2a221db8304ffa6759dff3.jpg"],"type":0,"id":9338503,"ga_prefix":"040612","title":"大误 · 床戏有多难你们知道么！"},{"images":["https://pic1.zhimg.com/v2-dcd3cd89939cd306c50eecc84dda287c.jpg"],"type":0,"id":9336298,"ga_prefix":"040611","title":"我的心中藏着一个完美的自己"},{"images":["https://pic1.zhimg.com/v2-94fc66ae23ae8d536e0786339f05f554.jpg"],"type":0,"id":9329517,"ga_prefix":"040610","title":"怎么到现在还有人相信「全国每年 210 万儿童死于装修」？"},{"images":["https://pic1.zhimg.com/v2-a019695d89d8d30b65b9dbbc2bd78c18.jpg"],"type":0,"id":9338925,"ga_prefix":"040609","title":"猫主子根本不会「妒忌」，你想多了哼哼"},{"title":"全球只有 7 人完成过，这是比攀登珠峰更令人惊叹的探险","ga_prefix":"040608","images":["https://pic2.zhimg.com/v2-a28fc4ec2c6e2f9480cededf698ad5cd.jpg"],"multipic":true,"type":0,"id":9338552},{"images":["https://pic3.zhimg.com/v2-ceeb67df90f5f2d19ca4c7a38a574022.jpg"],"type":0,"id":9338554,"ga_prefix":"040607","title":"你看，那个人在对着一只牛研究大象和老鼠"},{"images":["https://pic2.zhimg.com/v2-8fe914d7c0392ec7d3bbd4e05253afd5.jpg"],"type":0,"id":9338357,"ga_prefix":"040607","title":"马云常说的「新零售」会带来哪些新的机会？"},{"images":["https://pic1.zhimg.com/v2-843ddbf533365695fe11322ff98eb700.jpg"],"type":0,"id":9338342,"ga_prefix":"040607","title":"人工智能背后，有人在默默做着机械而繁重的工作"},{"images":["https://pic2.zhimg.com/v2-590b9487c043505e9e60d6af74e2039d.jpg"],"type":0,"id":9336381,"ga_prefix":"040606","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-ce45d7f0bf5139e33a1c2198146d2984.jpg","type":0,"id":9340102,"ga_prefix":"040614","title":"人工智能要和人类打德州扑克，这场比赛注定很精彩"},{"image":"https://pic3.zhimg.com/v2-87c968324465e4bfab1495162bed1af2.jpg","type":0,"id":9338552,"ga_prefix":"040608","title":"全球只有 7 人完成过，这是比攀登珠峰更令人惊叹的探险"},{"image":"https://pic3.zhimg.com/v2-b5f946e6b4ef368aa965d9391802c2b2.jpg","type":0,"id":9338357,"ga_prefix":"040607","title":"马云常说的「新零售」会带来哪些新的机会？"},{"image":"https://pic4.zhimg.com/v2-894de8db5d54b8c4646cbb003dc8c71b.jpg","type":0,"id":9338342,"ga_prefix":"040607","title":"人工智能背后，有人在默默做着机械而繁重的工作"},{"image":"https://pic2.zhimg.com/v2-3a1501f45065638d15d2755ef3dc8b6d.jpg","type":0,"id":9337665,"ga_prefix":"040516","title":"这场 Dota 决赛还没正式开打，IG 就「赢了一半」"}]
     */
    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic3.zhimg.com/v2-88f95a5df3b15df1890c5cac4807a3e6.jpg"]
         * type : 0
         * id : 9339661
         * ga_prefix : 040617
         * title : 一边集体当网红一边拿冠军，国乒队靠的是什么？
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-ce45d7f0bf5139e33a1c2198146d2984.jpg
         * type : 0
         * id : 9340102
         * ga_prefix : 040614
         * title : 人工智能要和人类打德州扑克，这场比赛注定很精彩
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
