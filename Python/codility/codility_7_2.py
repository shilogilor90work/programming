def solution(A, B):
    stack = []
    for fish in range(len(A)):
        live_fish = fish
        while B[live_fish] == 0 and stack and B[stack[-1]] == 1:
            other_fish = stack.pop()
            if A[other_fish] > A[live_fish]:
                live_fish = other_fish
        stack.append(live_fish)
    return len(stack)
