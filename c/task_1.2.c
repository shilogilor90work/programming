#include <stdio.h>

int main() {
    printf("Enter char to count:\n");
    char chosen_char , seq_char;
    int counter = 0;
    scanf("%c", &chosen_char);
    if (chosen_char == 's' || chosen_char == 'S' || chosen_char == 'm' || chosen_char == 'M')
    {
        printf("Enter the chars sequence:\n");
        do
        {
            scanf("%c", &seq_char);
            if (seq_char == chosen_char)
            {
               counter = counter +1;
            }
        }while(seq_char!='$');
        if (counter>0)
        {
            printf("The char with ASCII code %d appeared %d times.", chosen_char , (counter));
        }
    }
    return 0;
}
