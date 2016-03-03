import us.codecraft.webmagic.Spider;

/**
 * Created by saber on 16/1/18.
 * spider类初始化接口
 */
public interface SpiderInitializer
{
    Spider init(SpiderConfig spiderConfig, AppConfig appConfig);
}
