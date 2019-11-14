package cn.com.myproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by liyang-macbook on 2017/10/9.
 */
public class ChartGraphics {

    private static final Logger logger = LoggerFactory.getLogger(ChartGraphics.class);


    private BufferedImage image;
    private int imageWidth = 750;   //图片的宽度
    private int imageHeight = 1334; //图片的高度
    //生成图片文件
    @SuppressWarnings("restriction")
    public void createImage(String fileLocation) {
        String formatName = fileLocation.substring(fileLocation.lastIndexOf(".") + 1);
        try {
            ImageIO.write(image,formatName,new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合成图片
     * @param imgurl  海报背景图片
     * @param qrurl  二维码图片
     * @param name   用户名称
     * @param createImageUrl 临时存放目录
     * @param imgNumber 图片序号 对应系统参数 1：	邀请好友-海报图片1； 2：	邀请好友-海报图片2； 3：	邀请好友-海报图片3；
     */
    public void graphicsGeneration( String imgurl,String qrurl,String name,String createImageUrl,int imgNumber) {
        int type = BufferedImage.TYPE_INT_RGB;


        BufferedImage bimg = null;
        try {
            if(imgurl.indexOf("https://")!=-1){
                URL url = new URL(imgurl);
                bimg = ImageIO.read(url);
            }else{
                bimg = ImageIO.read(new File(imgurl));
            }
            type = bimg.getType();
        } catch (Exception e) {

        }


        image = new BufferedImage(imageWidth, imageHeight, type);
        //设置图片的背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, imageWidth, imageHeight);



        //***********************插入中间广告图
        Graphics mainPic = image.getGraphics();

        if(bimg!=null){
            mainPic.drawImage(bimg, 0, 0, imageWidth, imageHeight, null);
            mainPic.dispose();
        }




        try {

            mainPic = image.getGraphics();

            URL url = new URL(qrurl);

            BufferedImage bufferedImage = ImageIO.read(url);

            if(bufferedImage!=null){
                if(imgNumber ==1){
                    mainPic.drawImage(bufferedImage, 35, 1032, 198, 207, null);
                }else if(imgNumber == 2){
                    mainPic.drawImage(bufferedImage, 262, 636, 224, 224, null);
                }else if(imgNumber ==3){
                    mainPic.drawImage(bufferedImage, 283, 743, 184, 184, null);
                }
                mainPic.dispose();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //***********************设置下面的提示框

        Graphics2D tip = image.createGraphics();
        //设置字体颜色，先设置颜色，再填充内容
        tip.setColor(new Color(255, 164, 47)); //橙色
        //设置字体
        Font tipFont = new Font("宋体", Font.PLAIN, 34);
        tip.setFont(tipFont);
        if(imgNumber ==1){
            logger.info("name======="+name);
            tip.drawString(name, 347, 1097);
        }else if(imgNumber ==2){
            //tip.drawString(name, 347, 1097);
        }else if(imgNumber ==3){
            //tip.drawString(name, 347, 1097);
        }

      //  createImage("C:\\Users\\Administrator\\Downloads\\图片\\now\\2.jpeg");
        createImage(createImageUrl);

    }

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        String url = "http://sjjimg.oss-cn-hongkong.aliyuncs.com/qrcode/00002d9696ef40d48c98ddc781eb2853.jpeg";
        String createImageUrl ="C:\\Users\\Administrator\\Downloads\\图片\\now\\1.png";
        try {
            cg.graphicsGeneration("https://appphoto.doers.cn/goods/2017/10/16/728fc76d-b92e-4cc8-afb8-d1f8c410c77d.png",url,"bylei",createImageUrl,1);
        //  cg.graphicsGeneration("https://img.myproject.com.cn/goods/2017/10/17/f4c83d10-ba15-4418-9aa7-86e567280458.png",url,"sdfgsd",createImageUrl,2);
      //   cg.graphicsGeneration("https://img.myproject.com.cn/goods/2017/10/17/de2611be-6e65-4bd5-b773-c61a4cc2181c.png",url,"sdfgsd",createImageUrl,3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
