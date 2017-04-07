package comn.example.administrator.news.api;

import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.jean.zhihu;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface InfoService {
  //https://api.tianapi.com/wxnew/?key=APIKEY&num=10
    @GET("{type}/")
    public Observable<weixinjinxuan>getInfo
    (@Path("type")String type,@Query("key")String key,
     @Query("num")String num,@Query("rand")String rand);
//http://daily.zhihu.com/story/4772126"
  //http://news-at.zhihu.com/api/4/news/3892357
@GET("{id}")
  public Observable<zhihu>getZhihuJson(@Path("id")String id);
   // public Call<String>getMovie(@Query("start")String start,@Query("count")String count);
}
