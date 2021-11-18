package News.site.logic;


import News.site.exeption.InputFileHandlerException;
import News.site.entity.Article;
import News.site.entity.ArticleLong;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipUtil {


    private static String temp;
    private static String title = "";
    private static String preViewArticle = "";
    private static ZipEntry entry = null;
    private static final String NAME_OF_FILE = "article.txt";
    private static String allArticle = null;


    public static Article getContextFromFile(String zipFilePath) throws InputFileHandlerException {


        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {


            if ((entry = zipInputStream.getNextEntry()) == null) {
                throw new InputFileHandlerException("archive is empty or file is not archive");
            }
            if (!entry.getName().equals(NAME_OF_FILE)) {
                throw new InputFileHandlerException(NAME_OF_FILE + "is missing or too many file in archive ");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream, "UTF-8"));
                   title = reader.readLine();
            if ((preViewArticle = reader.readLine()) == null) {
                throw new InputFileHandlerException(NAME_OF_FILE + " is empty");
            }
            StringBuilder sb = new StringBuilder();
            int count = 0;
            sb.append(preViewArticle);
            while ((temp = reader.readLine()) != null) {
                if (count < 2) {
                    preViewArticle = preViewArticle + System.lineSeparator() + "</br>" + temp;

                }
                count++;
                sb.append(temp + System.lineSeparator() + "<" + "br>");
            }
            allArticle = sb.toString();


        } catch (IOException e) {
            e.printStackTrace();
            throw new InputFileHandlerException(NAME_OF_FILE + "not  zip file");
        }
        Article article = new Article(title, preViewArticle, new ArticleLong(allArticle));


        return article;
    }
}

