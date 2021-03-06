import os
import numpy as np
from PIL import Image
import random
from sklearn.cluster import KMeans
from sklearn import metrics
import shutil
import joblib
import matplotlib.pyplot as plt
mylist = []
path = "work/images"

mydict = {}
for file in os.listdir(path):
    name = file.split('_')[0]
    if not mydict.get(name):
        mydict.update({name:1})
        mylist.append(name)
print("标签："+str(mylist))
user_name = ['you','yue', 'jia', 'tian','shen','ri', 'mu']
path = 'work/images/'
newdict = {}
for file in os.listdir(path):
    name = file.split('_')[0]
    if name in user_name:
        im = Image.open(path+file)
        #print(im)
        if im.size[0] == 100 and im.size[1] == 100:
            if not newdict.get(name):
                newdict.update({name:1})
                print(name)
            else :
                newdict.update({name:newdict.get(name)+1})

for name in user_name:
    print(name + ' has: '+ str(newdict.get(name)))
    user_name = ['you','yue', 'jia', 'tian','shen','ri', 'mu']
path = 'work/images/'
def img2vector(im):
    im = im.convert("L")           #灰度图像信息
    img = np.array(im).flatten()   #展平为一维数组
    return img/255
def dataload():
    newdict = {}
    trainData = np.zeros((240*7,10000))    # （8/10标签样本数 * 标签数 ， 像素乘积）
    testData = np.zeros((60*7,10000))     # （2/10标签样本数 * 标签数 ， 像素乘积）
    trainLabels = []
    testLabels = []
    train_num = 0
    test_num = 0
    filepath = os.listdir(path)
    random.shuffle(filepath) 
    for file in filepath:
        name = file.split('_')[0]
        if name in user_name:
            im = Image.open(path+file)
            if im.size[0] == 100 and im.size[1] == 100:          # 像素数
                if not newdict.get(name):
                    newdict.update({name:1})
                    im.show()
                else:
                    newdict.update({name:newdict.get(name)+1})
                if newdict.get(name) <= 240:                      # 前8/10
                    trainData[train_num,:] = img2vector(im)
                    train_num += 1
                    trainLabels.append(name)
                elif newdict.get(name) <=300:                    # 后2/10
                    testData[test_num,:] = img2vector(im)
                    test_num += 1
                    testLabels.append(name)
                else:
                    pass
    return {'train':trainData, 'trainLabels':trainLabels, 'test':testData, 'testLabels':testLabels}
# 获取所有数据
DATA = dataload()
# 数据分类
train_images = DATA['train']
test_images = DATA['test']
train_labels = DATA['trainLabels']
test_labels = DATA['testLabels']
kmeans = KMeans(n_clusters=7, n_init=1, init='random',tol=0.00001)
kmeans.fit(train_images)
# 找到训练数据聚类众数分类
def find_label(kmeans):
    user_name = ['you','yue', 'jia', 'tian','shen','ri', 'mu']
    tdict = {'you' :0, 'yue':1, 'jia':2, 'tian':3, 'shen':4, 'ri':5, 'mu':6}
    t0 = []
    t1 = []
    t2 = []
    t3 = []
    t4 = []
    t5 = []
    t6 = []
    for i in range(7):
        t0.append(0)
        t1.append(0)
        t2.append(0)
        t3.append(0)
        t4.append(0)
        t5.append(0)
        t6.append(0)

    for i in range(1680):
        if kmeans.labels_[i] == 0:
            t0[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 1:
            t1[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 2:
            t2[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 3:
            t3[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 4:
            t4[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 5:
            t5[tdict[train_labels[i]]] += 1
        elif kmeans.labels_[i] == 6:
            t6[tdict[train_labels[i]]] += 1
        else :
            pass
  # 构造转换
    return [np.argmax(t0), np.argmax(t1), np.argmax(t2),np.argmax(t3),np.argmax(t4),np.argmax(t5),np.argmax(t6)]
# 合成代码
# 随机初始质心位置，或修正收敛条件
from sklearn.cluster import KMeans
from sklearn import metrics
k_means_acc = []
user_name = ['you','yue', 'jia', 'tian','shen','ri', 'mu']
tdict = {'you' :0, 'yue':1, 'jia':2, 'tian':3, 'shen':4, 'ri':5, 'mu':6}
m=0
for i in range(20):
    # --training
    kmeans = KMeans(n_clusters=7, n_init=5, init='random',tol=0.001)
    kmeans.fit(train_images)
    # 构造转换
    k_means_dict = find_label(kmeans)
    # predict
    y_pred = kmeans.predict(test_images)
    # 转换到对应标签上

    for i in range(420):
        test_labels[i] = tdict[test_labels[i]]
        y_pred[i] = k_means_dict[y_pred[i]]
    # --eval
    if m<metrics.adjusted_rand_score(test_labels, y_pred):
        m=metrics.adjusted_rand_score(test_labels, y_pred)
        model_path = './neigh_model'
        joblib.dump(neigh,model_path)
    print(metrics.adjusted_rand_score(test_labels, y_pred))
    k_means_acc.append(metrics.accuracy_score(test_labels, y_pred))
    # 回滚test
    for i in range(420):
        test_labels[i] = user_name[test_labels[i]]

import matplotlib.pyplot as plt
def plot_graphs_kmeans(k_means_acc):
    plt.plot(k_means_acc)
    plt.xticks(np.arange(1, 20, 2))
    plt.xlabel('Random_times')
    plt.ylabel('acc')
    plt.show()
plot_graphs_kmeans(k_means_acc)

print('原数据标签：'+str(test_labels[:13])+"\n预测数据标签：")
neigh_model = joblib.load('neigh_model')
neigh_model.predict(test_images[:13])
