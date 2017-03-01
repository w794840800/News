package comn.example.administrator.news.inter;

import java.util.List;

import comn.example.administrator.news.jean.douban;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/28.
 */

public interface BlueService {
    @GET("book/search")
    Observable<douban> getSearchBooks(@Query("q") String name,
             @Query("tag") String tag, @Query("start") int start,
             @Query("count") int count);

}
