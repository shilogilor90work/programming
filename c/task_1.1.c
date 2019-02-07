#include <stdio.h>

int main() {
    int input_number;
    int counter=0;
    double sum=0;
    int result=0;
    printf("Welcome, please enter the weights of each ingredient:\n");
    do
    {
        if(scanf("%d", &input_number))
        {
            if(input_number < 0)
            {
                break;
            }
            else
            {
                counter++;
                sum +=input_number;
            }
        }
        else
        {
            goto END;
        }
    }while (counter<11);
    if (counter==11)
    {
        printf("Too many ingredients.\n");
    }else if (counter<3)
    {
        printf("Not enough ingredients.\n");
    }
    else if (counter<11)
    {
        printf("The final product weighs %.3f pounds and is %d percent pure.", (sum/counter) , (counter*10));
    }
    return 0;
    END: printf("you did not put in a number, so im going to ignore this run\n");
}
