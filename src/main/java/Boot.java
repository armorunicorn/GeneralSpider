/**
 * Created by saber on 16/1/19.
 * 程序启动类
 */
public class Boot
{
    public static void main(String[] args)
    {
        SpiderControler spiderControler = new SpiderControler();
        spiderControler.init("config.xml");
    }
}
