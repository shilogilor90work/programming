#include <stdio.h>
#include <stdlib.h>
 void multiplyMatrices(int *firstMatrix, int *secondMatrix, int *mult, int rowFirst, int columnFirst, int rowSecond, int columnSecond)
{
	int i, j, k; //counters for looping
  if(columnFirst != rowSecond) // check if we can multiply the matrices
  {
          printf("failiure \n"); //print a failiure message
  }
  else
  {
  	for(i = 0; i < rowFirst; ++i)
  	{
  		for(j = 0; j < columnSecond; ++j)
  		{
  			mult[i*rowFirst + j] = 0; //reset the result matrix
  		}
  	}
  	for(i = 0; i < rowFirst; ++i)
  	{
  		for(j = 0; j < columnSecond; ++j)
  		{
  			for(k=0; k<columnFirst; ++k)
  			{
  				mult[i* columnSecond +j] += firstMatrix[i* columnFirst +k] * secondMatrix[k* columnSecond +j]; //simple calculation for matrix multiplying
  			}
  		}
  	}
  }
}
int main() {
    int k ,m ;
   int firstMatrix[2][4] = {
    {10, 11, 12,3},
    {14, 15, 16,1},
};
int secondMatrix [4][3]= {
    {10, 11, 12},
    {14, 15, 16},
    {10, 11, 12},
    {14, 15, 16}
};
    int rowFirst = sizeof(firstMatrix) / sizeof(firstMatrix[0]);
    int columnFirst = sizeof(firstMatrix[0]) / sizeof(firstMatrix[0][0]);
    int rowSecond = sizeof(secondMatrix) / sizeof(secondMatrix[0]);
    int columnSecond = sizeof(secondMatrix[0]) / sizeof(secondMatrix[0][0]);
    int mult[rowFirst][columnSecond]; // create a matrix memory for the multiply matrix
    multiplyMatrices(&firstMatrix[0][0], &secondMatrix[0][0], &mult[0][0], rowFirst, columnFirst, rowSecond, columnSecond);
   for (k=0;k<rowFirst && columnFirst == rowSecond ;++k) //print matrix
    {
         for (m=0;m<columnSecond;++m)
        {
            printf("%d ",mult[k][m]);
        }
        printf("\n");
    }
    return 0;
}
