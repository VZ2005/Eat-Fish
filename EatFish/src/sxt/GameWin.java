package sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class GameWin extends JFrame {
    // ----------------- 游戏状态控制 -----------------
    // 游戏状态：0未开始 1游戏中 2失败 3胜利 4暂停
    static int state = 0;

    // ----------------- 窗口设置 -----------------
    int width = 1440;   // 窗口宽度
    int height = 900;   // 窗口高度

    // ----------------- 游戏计时器 -----------------
    int time = 0;       // 用于控制敌方鱼生成频率

    // ----------------- 游戏元素 -----------------
    BG bg = new BG();            // 背景对象
    MyFish myfish = new MyFish();// 玩家控制的小鱼
    private GamePanel gamePanel = new GamePanel(); // 自定义绘制面板

    public void launch() {
        // ------------ 窗口基础设置 ------------
        this.setVisible(true);       // 设置窗口可见
        this.setSize(width, height); // 设置窗口尺寸
        this.setLocationRelativeTo(null); // 窗口居中显示
        this.setTitle("大鱼吃小鱼");   // 窗口标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 关闭按钮行为
        this.add(gamePanel);         // 添加自定义绘制面板
        this.setFocusable(true);     // 允许窗口接收键盘输入

        // ------------ 鼠标事件监听 ------------
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 左键点击开始游戏
                if (e.getButton() == 1 && state == 0) {
                    state = 1; // 切换为游戏进行状态
                }
            }
        });

        // ------------ 键盘事件监听 ------------
        this.addKeyListener(new KeyAdapter() {
            // 按键按下时触发
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                // 上键：上移
                if (key == KeyEvent.VK_UP) GameUtils.UP = true;
                // 下键：下移
                if (key == KeyEvent.VK_DOWN) GameUtils.DOWN = true;
                // 左键：左移
                if (key == KeyEvent.VK_LEFT) GameUtils.LEFT = true;
                // 右键：右移
                if (key == KeyEvent.VK_RIGHT) GameUtils.RIGHT = true;
            }

            // 按键释放时触发
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                // 松开上键时取消上移状态
                if (key == KeyEvent.VK_UP) GameUtils.UP = false;
                // 松开下键时取消下移状态
                if (key == KeyEvent.VK_DOWN) GameUtils.DOWN = false;
                // 松开左键时取消左移状态
                if (key == KeyEvent.VK_LEFT) GameUtils.LEFT = false;
                // 松开右键时取消右移状态
                if (key == KeyEvent.VK_RIGHT) GameUtils.RIGHT = false;
            }
        });

        // ------------ 游戏主循环 ------------
        while (true) {
            logic();            // 处理游戏逻辑（敌方鱼生成/移动）
            gamePanel.repaint();// 触发画面重绘
            time++;             // 增加时间计数器
            try {
                Thread.sleep(40); // 约25帧/秒（1000ms/40ms=25FPS）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= 自定义绘制面板 =================
    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 调用父类方法清空画布（启用双缓冲）

            switch (state) {
                case 0: // 开始界面
                    // 绘制全屏背景
                    g.drawImage(GameUtils.bgimg, 0, 0, getWidth(), getHeight(), this);
                    // 绘制开始文字
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("仿宋", Font.BOLD, 200));
                    g.drawString("开始", 700, 500); // 文字坐标需根据实际调整
                    break;
                case 1: // 游戏进行中
                    bg.paintSelf(g);          // 绘制背景
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("仿宋",Font.BOLD,50));
                    g.drawString("积分"+GameUtils.count,200,120);
                    myfish.paintSelf(g);     // 绘制玩家小鱼
                    // 遍历绘制所有敌方鱼
                    for (Enamy enemy : GameUtils.EnamyList) {
                        enemy.paintSelf(g);
                    }
                    break;
                case 2:
                    break;
                case 4:
                    break;
            }
        }
    }

    // ================= 游戏逻辑处理 =================
    void logic() {
        // ------------ 敌方鱼生成逻辑 ------------
        if (time % 20 == 0) { // 每20帧生成一条新鱼
            Enamy enemy = new Enamy_1_L();
            GameUtils.EnamyList.add(enemy);
        }

        // ------------ 敌方鱼移动与边界检测 ------------
        Iterator<Enamy> iterator = GameUtils.EnamyList.iterator();
        while (iterator.hasNext()) {
            Enamy enemy = iterator.next();
            enemy.x += enemy.dir * enemy.speed; // 根据方向移动
            // 移出右边界时移除
            if (enemy.x > width) {
                iterator.remove();
            }
            //我方小鱼和地方小鱼碰撞检测
            if(myfish.getRec().intersects(enemy.getRec())){
                System.out.println("碰撞");
                enemy.x = -200;
                enemy.y = -200;
                GameUtils.count+=enemy.count;
            }

        }
    }

    public static void main(String[] args) {
        new GameWin().launch();
    }
}