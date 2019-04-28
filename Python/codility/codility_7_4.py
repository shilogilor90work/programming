def solution(H):
    stack = []
    counter = 0   
    for height in H:
        while len(stack) != 0 and height < stack[-1]:
            stack.pop()
            counter+=1
        if len(stack) == 0 or height > stack[-1]:
            stack.append(height)
    return counter + len(stack)
