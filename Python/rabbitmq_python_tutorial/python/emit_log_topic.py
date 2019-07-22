#!/usr/bin/env python
'''
https://www.rabbitmq.com/tutorials/tutorial-five-python.html

emit message to all recivers with condition that the
first word (like regex within a text) is in the list of what we defined the worker as we start the program
include receive_logs_topic.py

'''


import pika
import sys

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.exchange_declare(exchange='topic_logs', exchange_type='topic')

routing_key = sys.argv[1] if len(sys.argv) > 2 else 'anonymous.info'
message = ' '.join(sys.argv[2:]) or 'Hello World!'
channel.basic_publish(
    exchange='topic_logs', routing_key=routing_key, body=message)
print(" [x] Sent %r:%r" % (routing_key, message))
connection.close()
