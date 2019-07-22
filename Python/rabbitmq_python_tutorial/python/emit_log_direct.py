#!/usr/bin/env python

'''
https://www.rabbitmq.com/tutorials/tutorial-four-python.html

emit message to all recivers with condition that the
first word is in the list of what we defined the worker as we start the program
include receive_logs_direct.py

'''

import pika
import sys

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.exchange_declare(exchange='direct_logs', exchange_type='direct')

severity = sys.argv[1] if len(sys.argv) > 1 else 'info'
message = ' '.join(sys.argv[2:]) or 'Hello World!'
channel.basic_publish(
    exchange='direct_logs', routing_key=severity, body=message)
print(" [x] Sent %r:%r" % (severity, message))
connection.close()
