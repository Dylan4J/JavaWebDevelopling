import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class JsoupDemo1 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");

        Elements name = document.getElementsByTag("name");
        Elements id = document.getElementsByAttributeValue("id","itcast");
        System.out.println(id);
        System.out.println("=================");

        JXDocument jxDocument = new JXDocument(document );
        List<JXNode> jxNodes = jxDocument.selN("//student/name[@id='itcast']");
        Iterator iterator = jxNodes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            System.out.println("===");
        }


    }
}
