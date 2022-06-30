import os
from PIL import Image
import numpy as np
def fun(img,txt):
    if not os.path.exists(img):
        return
    if not os.path.exists(txt):
        os.mkdir(txt)
    list=os.listdir(img)
    length=len(list)
    for i in range(length):
        path=img+'/'+list[i]
        SavePath=dst+'/'+list[i][:-4]+".txt"
        read=Image.open(path).convert("1")          
        arr=np.asarray(read)
        np.savetxt(SavePath,arr,fmt="%d",delimiter='')
img="D:\33838\OneDrive - zppjx\桌面\images"
txt="D:\33838\OneDrive - zppjx\桌面\txt"
fun(img,txt)
