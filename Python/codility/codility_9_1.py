def maxSubArraySum(A):
    max_so_far =0
    curr_max = 0
    for i in range(len(A)):
        curr_max = max(A[i], curr_max + A[i])
        max_so_far = max(max_so_far,curr_max)
    return max_so_far


def solution(A):
    if not A :
        return 0
    gaps = []
    for loc in range(len(A))[1:]:
        gaps.append(A[loc] - A[loc-1])
    return maxSubArraySum(gaps) 
