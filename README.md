# Legendry

- Hi, we are TEAM TianXiaWuShuang,
- Hope we can learn from each other and make progress together.

---

# 特别感谢：

### 博学多才，耐心细致的赵永瑞赵导，没有赵导，本小组也不可能完成实践任务

### 小组成员：

[李骅萱](https://github.com/18611756652)

[倪松涛](https://github.com/sniffstherose)

[贾泽鑫](https://github.com/lierhouzi)

[丁书润](https://github.com/dingshurun)

[高翔宇](https://github.com/gaoxiangyu666)

### 项目实践主题：

> 				**学习人工智能算法，实现对手写汉字的识别**

> 在此次实践中，本组主要进行基于K-means算法实现对手写汉字的识别和基于KNN算法实现对手写汉字的识别



### KNN如何实现手写字体的识别：

1. 数据处理（图片处理为数字文本）
2. 待测图片与训练集每一张图片的向量做欧氏距离
3. 排序，选取最优K，使得结果最好



### K-means算法：

1. 随机设K个特征空间内的点作为初始的聚类中心；
2. 对于根据每个数据的特征向量，从K个聚类中心中寻找距离最近的一个，并把该数据标记为从属于这个类中心
3. 在所有的数据都被标记过聚类中心后，根据这些数据新分配的类簇，重新对K个聚类中心进行计算
4. 如果一轮下来，所有数据点从属对聚类中心与上一次的分配的类簇没有变化，停止迭代，否则回到步骤2继续执行

---

### 简介：

现阶段仅有简陋的手写汉字识别功能，有待进一步优化与开发

### LICENSE:

[LICENCE](https://github.com/Bistu-OSSDT-2022/Legendr/blob/dingshurun/LICENSE)



### 项目实践报告：

[欢迎下载项目实践报告](https://github.com/Bistu-OSSDT-2022/Legendr/blob/dingshurun/%E9%A1%B9%E7%9B%AE%E6%8A%A5%E5%91%8A(2).docx)

### 项目Wiki：

[Wiki](https://github.com/Bistu-OSSDT-2022/Legendr/wiki)

---

### 注意事项：

> 以下为几点注意事项

1. 本项目基于python语言开发，你可能需要配置环境

   - python下载：[python](https://www.python.org/)

2. 代码中的注意事项：

   - 代码中的文件路径需根据自身数据集路径做修改

   - 代码中的数据范围需要根据自身图片像素等做修改
