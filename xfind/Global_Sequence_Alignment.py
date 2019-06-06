class global_align(object):
  def __init__(self , seq_a , seq_b , match = 1 , mismatch = -1 , gap = -1):
    def similarity_function (a , b): #return the punish or prize for match
      if a==b:
        return match
      return mismatch
    # Mij is score of align of A[1..i] with B[1..j] ending in a match
    # Dij is score of align of A[1..i] with B[1..j] ending in a deletion (Ai,gap)
    # Iij is score of align of A[1..i] with B[1..j] ending in an insertion (gap,Bj)
    # E is direction matrix: -1=del, 0=match, +1=ins
    self.seq_a , self.seq_b = seq_a , seq_b
    A,B = seq_a, seq_b
    m,n = self.m,self.n = len(A),len(B)
    M = self.make_matrix(m+1,n+1)
    D = self.make_matrix(m+1,n+1)
    I = self.make_matrix(m+1,n+1)
    E = self.make_matrix(m+1,n+1)
    for i in range(1,m+1): M[i][0] = gap*i
    for i in range(1,n+1): M[0][i] = gap*i
    M[0][0] = 0
    # fill in the matrices using dynamic programming
    for i in range(1,m+1):
      for j in range(1,n+1):

        if i==1: D[i][j] = M[i-1][j]+gap
        else: D[i][j] = max(M[i-1][j]+gap,D[i-1][j]+gap)

        if j==1: I[i][j] = M[i][j-1]+gap
        else: I[i][j] = max(M[i][j-1]+gap,I[i][j-1]+gap)

        M[i][j] = max(M[i-1][j-1]+similarity_function(A[i-1],B[j-1]),D[i][j],I[i][j])

        if M[i][j]==D[i][j]: E[i][j] = 1    # deletion, i.e. of A[i]
        elif M[i][j]==I[i][j]: E[i][j] = -1 # insertion, i.e. of B[j]
        else: E[i][j] = 0

    (self.M,self.D,self.I,self.E) = (M,D,I,E)
    self.alignment = self.extract_alignment() # best alignment

  def make_matrix(self,m,n): # for initializing
    R = []
    for i in range(m):
        R.append([0]*n)
    return R

  def show_matrix(self, data, label=None): # no need for this, was using to make sure algorithm works
    if (label is not None):
        print(label)
    for i,row in enumerate(data):
      print ()
      for x in row:
          print(x , end=" ")

  def show_matrices(self, out=None): # printing data
    for label, data in [("D", self.D),
                        ("I", self.I),
                        ("M", self.M),
                        ("E", self.E)]:
      self.show_matrix(data=data, label=label)
    return self

  def score(self): #the score is at the end of the table since it is a global sequence
    return self.M[self.m][self.n]


  def extract_alignment(self): # finding alignment 'i' for seq_a getting "_", 'd' for seq_b getting "_", 'm' for match , '_' for mismatch
    (M,D,I,E) = (self.M,self.D,self.I,self.E)
    match_codes = []
    (i,j) = (self.m ,self.n)
    while i>0 and j>0:
        if E[i][j]==-1: match_codes.append('i'); j -= 1
        elif E[i][j]==1: match_codes.append('d'); i -= 1
        elif E[i][j]==0:
          if self.seq_a[i-1]==self.seq_b[j-1]:
            match_codes.append('m')
          else :
              match_codes.append('_')
          i -= 1
          j -= 1
    while i>0: match_codes.append('d'); i -= 1
    while j>0: match_codes.append('i'); j -= 1
    F,G = range(len(self.seq_a)), range(len(self.seq_b)) # for showing original location
    match_codes.reverse()
    match_codes = "".join(match_codes)
    sa,sb = [], []
    ia,ib = [], []
    u, v = 0, 0
    for a in match_codes:
      if a=='d':
        i = F[u]
        u += 1
        ia.append(i)
        sa.append(self.seq_a[i])
        ib.append(None)
        sb.append('-')
      elif a=='i':
        ia.append(None)
        sa.append('-')
        i = G[v]
        v += 1
        ib.append(i)
        sb.append(self.seq_b[i])
      elif a=='m' or a=='_':
        i = F[u]
        u += 1
        ia.append(i)
        sa.append(self.seq_a[i])
        i = G[v]
        v += 1
        ib.append(i)
        sb.append(self.seq_b[i])
    return alignment(
      a="".join(sa), b="".join(sb),
      i_seqs_a=ia, i_seqs_b=ib,
      match_codes=match_codes , score = self.score())

class alignment(object): # for easier handling

  def __init__(self,
        a, b,
        i_seqs_a, i_seqs_b,
        match_codes , score):
    self.a , self.b , self.i_seqs_a , self.i_seqs_b , self.match_codes , self.score = a, b, i_seqs_a, i_seqs_b, match_codes , score

  def print_seq(self):
      print (self.a)
      print (self.b)
      print (self.i_seqs_a)
      print (self.i_seqs_b)
      print (self.match_codes)
      print (self.score)
temp = global_align(['a','b','c' , 'd' , 'x' , 'y'] , ['a','b','x' , 'y' , 'a' , 'y'] ,3,-2,-1)
ali = temp.alignment
ali.print_seq()
