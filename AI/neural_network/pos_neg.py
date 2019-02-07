import tensorflow as tf
import numpy as np
#from tensorflow.examples.tutorials.mnist import input_data

#mnist = input_data.read_data_sets("/tmp/data/" , one_hot = True)
from neuralnetwork_03 import create_feature_sets_and_labels

train_x,train_y,test_x,test_y = create_feature_sets_and_labels('pos.txt' , 'neg.txt' )

n_nodes_hl1 = 700
n_nodes_hl2 = 700
n_nodes_hl3 = 700
n_nodes_hl4 = 700


n_classes = 2

batch_size = 100

x = tf.placeholder('float' , [None , len(train_x[0])])
y = tf.placeholder('float')


def neural_network_model(data):
    hidden_layer_1 = { 'weights' : tf.Variable(tf.random_normal([len(train_x[0]), n_nodes_hl1]))
                     , 'biases' : tf.Variable(tf.random_normal([n_nodes_hl1]))}
    hidden_layer_2 = { 'weights' : tf.Variable(tf.random_normal([n_nodes_hl1, n_nodes_hl2]))
                     , 'biases' : tf.Variable(tf.random_normal([n_nodes_hl2]))}
    hidden_layer_3 = { 'weights' : tf.Variable(tf.random_normal([n_nodes_hl2, n_nodes_hl3]))
                     , 'biases' : tf.Variable(tf.random_normal([n_nodes_hl3]))}
    hidden_layer_4 = { 'weights' : tf.Variable(tf.random_normal([n_nodes_hl3, n_nodes_hl4]))
                     , 'biases' : tf.Variable(tf.random_normal([n_nodes_hl4]))}
    output_layer = { 'weights' : tf.Variable(tf.random_normal([n_nodes_hl4, n_classes]))
                     , 'biases' : tf.Variable(tf.random_normal([n_classes]))}

    layer_1 = tf.add((tf.matmul(data , hidden_layer_1['weights'])) , hidden_layer_1['biases'])
    layer_1 = tf.nn.relu(layer_1)

    layer_2 = tf.add((tf.matmul(layer_1 , hidden_layer_2['weights'])) , hidden_layer_2['biases'])
    layer_2 = tf.nn.relu(layer_2)

    layer_3 = tf.add((tf.matmul(layer_2 , hidden_layer_3['weights'])) , hidden_layer_3['biases'])
    layer_3 = tf.nn.relu(layer_3)

    layer_4 = tf.add((tf.matmul(layer_3 , hidden_layer_4['weights'])) , hidden_layer_4['biases'])
    layer_4 = tf.nn.relu(layer_4)

    output_layer = tf.add(tf.matmul(layer_4 , output_layer['weights']) , output_layer['biases'])

    return output_layer


def train_neural_network(x):
    prediction = neural_network_model(x)
    cost = tf.reduce_mean( tf.nn.softmax_cross_entropy_with_logits(logits=prediction, labels=y) )
# defalt learning rate is 0.001
    optimizer = tf.train.AdamOptimizer().minimize(cost)
    number_of_epochs = 30
    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())


        for epoch in range(number_of_epochs):
            epoch_loss = 0

            i = 0
            while i < len(train_x):
                start = i
                end = i + batch_size
                batch_x = np.array(train_x[start:end])
                batch_y = np.array(train_y[start:end])


    #        for _ in range(int(mnist.train.num_examples/batch_size)):
    #            epoch_x,epoch_y = mnist.train.next_batch(batch_size)

                _,c = sess.run([optimizer , cost], feed_dict = {x:batch_x,y:batch_y})
                epoch_loss += c
                i+= batch_size
            print('epoch ' , epoch , ' completed out of ' , number_of_epochs , ' loss: ', epoch_loss)

        correct = tf.equal(tf.argmax(prediction , 1) , tf.argmax(y , 1))
        accuracy = tf.reduce_mean(tf.cast(correct , 'float'))
        print ('accuracy: ' , accuracy.eval({x:test_x, y:test_y}))


def use_neural_network(input_data):
    prediction = neural_network_model(x)
    with open('lexicon.pickle','rb') as f:
        lexicon = pickle.load(f)

    with tf.Session() as sess:
        sess.run(tf.initialize_all_variables())
        saver.restore(sess,"model.ckpt")
        current_words = word_tokenize(input_data.lower())
        current_words = [lemmatizer.lemmatize(i) for i in current_words]
        features = np.zeros(len(lexicon))

        for word in current_words:
            if word.lower() in lexicon:
                index_value = lexicon.index(word.lower())
                # OR DO +=1, test both
                features[index_value] += 1

        features = np.array(list(features))
        # pos: [1,0] , argmax: 0
        # neg: [0,1] , argmax: 1
        result = (sess.run(tf.argmax(prediction.eval(feed_dict={x:[features]}),1)))
        if result[0] == 0:
            print('Positive:',input_data)
        elif result[0] == 1:
            print('Negative:',input_data)

train_neural_network(x)
