import os
import numpy as np
from PIL import Image
import random
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier as KNN 
import shutil
import joblib
import matplotlib.pyplot as plt

path = input("请输入你要识别汉字的路径")
user_name = ['jia', 'yue', 'mu', 'ri', 'tian', 'shen', 'you']

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
    random.shuffle(filepath)              # 打乱标签顺序
    for file in filepath:
        name = file.split('_')[0]
        if name in user_name:
            im = Image.open(path+'/'+file)
            if im.size[0] == 100 and im.size[1] == 100:          # 像素数
                if not newdict.get(name):
                    newdict.update({name:1})
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

DATA = dataload()
train_images = DATA['train']
test_images = DATA['test']
train_labels = DATA['trainLabels']
test_labels = DATA['testLabels']

#模型训练
knn_acc = []
m=0
for i in range(1,20):
    neigh = KNN(n_neighbors=i,algorithm='auto',weights='distance')
    neigh.fit(train_images,train_labels)
    y_pred = neigh.predict(test_images)
    print(metrics.adjusted_rand_score(test_labels,y_pred))
    if metrics.adjusted_rand_score(test_labels,y_pred)>m:
        m=metrics.adjusted_rand_score(test_labels,y_pred)
        model_path = './neigh_model'
        joblib.dump(neigh,model_path)
    knn_acc.append(metrics.accuracy_score(test_labels,y_pred))

#图表展示不同K值训练处的模型的评估效果
def plot_graphs_knn(knn_acc):
    plt.bar(list(range(1,20)),knn_acc)
    plt.xticks(np.arange(1,20,2))
    plt.ylim(0,1)
    plt.xlabel('K')
    plt.ylabel('acc')
    plt.show()
plot_graphs_knn(knn_acc)

#识别
print('原数据标签：'+str(test_labels[:13])+"\n预测数据标签：")
neigh_model = joblib.load('neigh_model')
neigh_model.predict(test_images[:13])
