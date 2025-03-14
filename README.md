# Match Three Game

![Game Banner](https://via.placeholder.com/800x200?text=Match+Three+Game)

## 📖 项目简介

Match Three Game是一个用Java开发的经典三消游戏。游戏中，玩家通过交换相邻的方块来形成三个或更多相同类型的连线，消除方块并获得分数。游戏包含丰富的视觉效果和流畅的游戏体验，适合所有年龄段的玩家。

## ✨ 特点功能

- 🎮 经典三消游戏玩法
- 🎯 直观的点击交换机制
- 🎨 支持自定义图片资源
- 🔄 自动检测和处理连锁反应
- 📊 实时分数统计
- 🎵 游戏音效支持（可选）
- 📱 在Windows系统上流畅运行

## 🔧 安装指南

### 方法1：直接运行JAR文件（推荐）

1. 确保你的电脑已安装Java运行环境(JRE) 8或更高版本
2. 下载`MatchThreeGame.jar`文件
3. 双击JAR文件或在命令行中运行：
   ```
   java -jar MatchThreeGame.jar
   ```

### 方法2：使用批处理文件启动（Windows）

1. 确保你的电脑已安装Java
2. 下载`MatchThreeGame.jar`和`StartGame.bat`文件
3. 双击`StartGame.bat`运行游戏

### 方法3：从源代码构建（开发者）

1. 克隆仓库：
   ```
   git clone https://github.com/yourusername/match-three-game.git
   ```
2. 使用IDE打开项目（推荐使用IntelliJ IDEA或VSCode）
3. 编译并运行`Game.java`文件

## 🎮 游戏操作说明

1. **游戏目标**：通过交换相邻方块形成三个或更多相连的同色方块，获取高分
2. **操作方式**：
   - 点击一个方块选中它
   - 点击相邻方块进行交换
   - 只有能形成匹配的交换才会生效
3. **游戏规则**：
   - 形成三连可消除方块并获得100分
   - 连锁反应会获得额外分数
   - 方块消除后，上方的方块会掉落填补空位
   - 新的方块会从顶部生成

## 🏆 得分系统

- 基础三连：100分
- 连锁反应：额外50分
- 游戏会保存最高分记录

## 📁 项目结构

```
MatchThreeGame/
│
├── src/main/
│   ├── Game.java         // 主游戏类和入口点
│   ├── GamePanel.java    // 游戏面板和渲染
│   ├── GameBoard.java    // 游戏棋盘逻辑
│   ├── Tile.java         // 方块类
│   │
│   └── resources/        // 资源文件
│       ├── tile1.png
│       ├── tile2.png
│       └── ...
│
├── bin/                  // 编译后的class文件
│
├── MatchThreeGame.jar    // 打包后的可执行JAR文件
├── StartGame.bat         // Windows启动脚本
├── manifest.txt          // JAR清单文件
└── README.md             // 项目说明文档
```

## 🔍 技术细节

### 开发环境

- Java Development Kit (JDK) 8+
- Swing GUI框架
- 无外部依赖

### 主要类说明

- **Game**: 主应用类，包含主方法和游戏循环
- **GamePanel**: 负责游戏渲染和鼠标输入处理
- **GameBoard**: 处理游戏核心逻辑，包括匹配检测和方块下落
- **Tile**: 表示单个游戏方块，处理绘制和状态

## 🛠 自定义和扩展

### 自定义方块图片

1. 准备6个PNG格式图片文件（推荐尺寸60x60像素）
2. 命名为`tile1.png`到`tile6.png`
3. 放置在`src/main/resources/`目录下
4. 重新编译运行游戏

### 添加音效（高级）

1. 准备WAV格式音效文件
2. 放置在`src/main/resources/sounds/`目录
3. 修改代码添加音效播放支持

## 🐛 常见问题解决

1. **游戏无法启动**
   - 确认已安装Java并可通过命令行访问
   - 检查JAR文件是否完整

2. **找不到类错误**
   - 重新下载完整的JAR文件
   - 或从源代码重新构建项目

3. **显示问题**
   - 确保显示分辨率至少为800x600
   - 检查Java版本是否为8或更高

## 📝 未来计划

- [ ] 添加游戏开始和结束界面
- [ ] 实现不同难度级别
- [ ] 添加特殊方块和能力
- [ ] 开发关卡系统
- [ ] 增加多平台支持

## 👥 贡献指南

欢迎贡献代码、报告问题或提出改进建议！请遵循以下步骤：

1. Fork本仓库
2. 创建你的特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交你的更改 (`git commit -m 'Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 打开一个Pull Request

## 📜 许可证

该项目采用MIT许可证 - 详情请查看[LICENSE](LICENSE)文件。

## 👏 致谢

- 感谢所有游戏测试者的宝贵反馈
- 特别感谢Java Swing文档和资源
- 灵感来源于经典的三消游戏

## 📞 联系方式

如有任何问题或建议，请联系：

- 电子邮件：[your.email@example.com](mailto:your.email@example.com)
- GitHub Issues: [https://github.com/yourusername/match-three-game/issues](https://github.com/yourusername/match-three-game/issues)

---

**祝您游戏愉快！**
