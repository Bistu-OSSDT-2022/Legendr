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
        #np.savetxt(SavePath, arr,fmt="%d")
img="work/images"
txt="work/dst"
fun(img,txt)
