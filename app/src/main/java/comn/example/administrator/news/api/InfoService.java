package comn.example.administrator.news.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface InfoService {
    @GET("TOP250")
    public Call<String>getMovie(@Query("start")String start,@Query("count")String count);
}
