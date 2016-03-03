import java.util.Map;

/**
 * Created by saber on 16/1/18.
 * spider配置数据类
 */
public class SpiderConfig
{
    public String spider_initializer;
    public int thread;
    public int interval_time;
    public Map<String, Boolean> url_list;
}
