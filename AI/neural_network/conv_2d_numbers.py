
import tflearn
from tflearn.layers.conv import conv_2d , max_pool_2d
from tflearn.layers.core import input_data , fully_connected , dropout
from tflearn.layers.estimator import regression
import tflearn.datasets.mnist as mnist
import cv2
import numpy as np
from os import system
import pickle


X ,Y ,test_x , test_y = mnist.load_data(one_hot = True)
X = X.reshape([-1,28,28,1])
test_x = test_x.reshape([-1,28,28,1])


convnet = input_data(shape = [None,28,28,1], name = 'input')

convnet = conv_2d(convnet , 32 ,2 , activation = 'relu')
convnet = max_pool_2d(convnet , 2)

convnet = conv_2d(convnet , 64 ,2 , activation = 'relu')
convnet = max_pool_2d(convnet , 2)


convnet = fully_connected(convnet , 1024 , activation = 'relu')


convnet = dropout(convnet , 0.8)

convnet = fully_connected(convnet , 10 , activation = 'softmax')

convnet = regression(convnet , optimizer = 'adam' , learning_rate = 0.01 , loss = 'categorical_crossentropy' , name = 'targets')

model = tflearn.DNN(convnet)
'''
model.fit({'input':X}, {'targets':Y} , n_epoch = 10 ,
                    validation_set = ({'input':test_x}, {'targets':test_y}) ,
                    snapshot_step = 500 , show_metric = True , run_id = 'mnist')


model.save('tflearn.model')
'''

def convert_image_to_ones_and_zeros(image):
    img = cv2.imread(image, 0)
    img = cv2.resize(img, dsize=(28, 28), interpolation=cv2.INTER_CUBIC)
    img_reverted= cv2.bitwise_not(img)
    new_img = img_reverted / 255.0
#    for row in range(28):
#        for pixel in range(28):
#            if(new_img[row][pixel]<0.5):
#                new_img[row][pixel] = 0
#            else:
#                new_img[row][pixel] = 1
    new_img=new_img.reshape([-1,28,28,1])
    return new_img


model.load('tflearn.model')
this_image = convert_image_to_ones_and_zeros('tzipiseven.jpeg')
this_array = np.array(model.predict(this_image))
print (this_array)
maxi = np.argmax(this_array)
system("say your number is "  + str(maxi) )
