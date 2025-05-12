package sxt;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {
    // ------------ 图像资源 ------------
    public static Image bgimg;       // 背景图片
    public static Image enamy1_img;  // 敌方鱼图片
    public static Image MyFishing_L; // 玩家鱼左向图片
    public static Image MyFishing_R; // 玩家鱼右向图片

    // ------------ 游戏数据 ------------
    public static List<Enamy> EnamyList = new ArrayList<>(); // 敌方鱼集合

    // ------------ 控制状态 ------------
    public static boolean UP = false;    // 上移标志
    public static boolean DOWN = false;  // 下移标志
    public static boolean LEFT = false;  // 左移标志
    public static boolean RIGHT = false; // 右移标志

    //分数
    static int count = 0;

    // ------------ 静态初始化块 ------------
    static {
        // 使用媒体追踪器确保图片加载完成
        MediaTracker tracker = new MediaTracker(new JPanel());
        try {
            // 加载背景图
            bgimg = Toolkit.getDefaultToolkit().createImage("images/sea.jpg");
            // 加载敌方鱼图片
            enamy1_img = Toolkit.getDefaultToolkit().createImage("images/enemyFish/fish1_r.jpg");
            // 加载玩家鱼图片（注意路径修正）
            MyFishing_L = Toolkit.getDefaultToolkit().createImage("images/myFish/myfish_left.jpg");
            MyFishing_R = Toolkit.getDefaultToolkit().createImage("images/myFish/myfish_right.jpg");

            // 添加图片到追踪器
            tracker.addImage(bgimg, 0);
            tracker.addImage(enamy1_img, 0);
            tracker.addImage(MyFishing_L, 0);
            tracker.addImage(MyFishing_R, 0);

            // 等待所有图片加载完成
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}