package sxt;

import java.awt.*;

public class MyFish {
    // ------------ 图像属性 ------------
    Image img = GameUtils.MyFishing_L; // 初始显示左向图片

    // ------------ 位置属性 ------------
    int x = 700;    // 初始X坐标（居中）
    int y = 500;    // 初始Y坐标（居中）
    int width = 50; // 显示宽度
    int height = 50;// 显示高度

    // ------------ 移动属性 ------------
    int speed = 10; // 移动速度（像素/帧）
    int level = 1;  // 等级（暂未使用）

    // ================= 绘制自身 =================
    public void paintSelf(Graphics g) {
        logic(); // 先处理移动逻辑
        g.drawImage(img, x, y, width+GameUtils.count, height+GameUtils.count, null);
    }

    // ================= 碰撞检测 =================
    public Rectangle getRec() {
        return new Rectangle(x, y, width+GameUtils.count, height+GameUtils.count);
    }

    // ================= 移动逻辑 =================
    void logic() {
        // 边界检测：确保不超出窗口范围
        if (GameUtils.UP)
            y = Math.max(0, y - speed);          // 上边界限制
        if (GameUtils.DOWN)
            y = Math.min(900 - height, y + speed);// 下边界限制（假设窗口高度900）
        if (GameUtils.LEFT) {
            x = Math.max(0, x - speed);          // 左边界限制
            img = GameUtils.MyFishing_L;         // 切换左向图片
        }
        if (GameUtils.RIGHT) {
            x = Math.min(1440 - width, x + speed);// 右边界限制（假设窗口宽度1440）
            img = GameUtils.MyFishing_R;         // 切换右向图片
        }
    }
}