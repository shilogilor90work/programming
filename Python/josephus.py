import math
def josephus(num):
  return (num-2**int(math.log(num,2)))*2+1
