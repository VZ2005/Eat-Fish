package sxt;
import java.awt.*;

// ================= 敌方鱼基类 =================
public class Enamy {
    // ------------ 通用属性 ------------
    Image img;   // 显示图像
    int x, y;    // 坐标位置
    int width;   // 显示宽度
    int height;  // 显示高度
    int speed;   // 移动速度
    int dir = 1; // 移动方向（1=右，-1=左）
    int type;    // 鱼类类型（暂未使用）
    int count;   // 分值（暂未使用）

    // ================= 绘制方法 =================
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    // ================= 碰撞检测 =================
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}

// ================= 具体敌方鱼类型 =================
class Enamy_1_L extends Enamy {
    Enamy_1_L() {
        // ------------ 初始化属性 ------------
        this.x = -45; // 初始X坐标（左侧外）
        this.y = (int)(Math.random() * 700 + 100); // Y坐标随机（100-800）
        this.width = 45;   // 图像宽度
        this.height = 69;   // 图像高度
        this.speed = 10;    // 移动速度
        this.count = 1;     // 击败得分
        this.type = 1;      // 鱼类类型标识
        this.img = GameUtils.enamy1_img; // 设置图像资源
    }
}