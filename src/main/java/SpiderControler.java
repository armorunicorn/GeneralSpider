import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by saber on 16/1/19.
 * 管理各个爬虫类
 */
public class SpiderControler
{
    private AppConfig appConfig;

    protected static Logger logger = LoggerFactory.getLogger(SpiderControler.class);

    public void init(String filename)
    {
        try
        {
            appConfig = new AppConfig();
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filename));
            List list = document.selectNodes("/config/spider_list/spider");
            Iterator iter = list.iterator();
            appConfig.spiderConfigList = new ArrayList<SpiderConfig>();
            while (iter.hasNext())
            {
                Element element = (Element)iter.next();
                SpiderConfig spiderConfig = new SpiderConfig();
                spiderConfig.interval_time = Integer.parseInt(element.element("interval_time").getText());
                spiderConfig.thread = Integer.parseInt(element.element("thread").getText());
                spiderConfig.spider_initializer = element.element("class").getText();
                List startURL = element.element("start_url").elements("url");
                spiderConfig.url_list = new HashMap<String, Boolean>();
                for(Iterator it = startURL.iterator(); it.hasNext();)
                {
                    Element url = (Element)it.next();
                    spiderConfig.url_list.put(url.getText(),
                            Boolean.parseBoolean(url.attribute("self").getText()));
                }
                appConfig.spiderConfigList.add(spiderConfig);
            }

            Element mongo = document.getRootElement().element("mongo");
            appConfig.mongo_url = mongo.element("url").getText();
            appConfig.mongo_collection = mongo.element("collection").getText();
        }
        catch (DocumentException e)
        {
            logger.error("xml analyse failed:", e);
        }

    }
}
