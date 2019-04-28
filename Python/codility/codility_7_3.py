def solution(S):
    stack = []
    for brac in S:
        if brac == '(':
            stack.append(brac)
        elif brac == ')' :
            if len(stack)==0:
                return 0
            temp = stack.pop() + brac
            if temp != "()":
                return 0
    if len(stack)==0:
        return 1
    else :
        return 0
