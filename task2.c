
int main()
{
    char str[] = "Hello World";
    char str1[12]; // Update array size to 12 to accommodate the null terminator
    int i, len;
    
    // Calculate the length of the string 'str'
    len = strlen(str);
    
    // Loop through each character of the string 'str'
    for (i = 0; i < len; i++)
    {
        // Perform bitwise XOR operation with 0 (which essentially keeps the character unchanged)
        str1[i] = str[i] ^ 0;
        
        // Print the character
        printf("%c", str1[i]);
    }
    
    // Add null terminator to the end of str1
    str1[len] = '\0';
    
    // Print a newline character
    printf("\n");
    
    return 0; // Return 0 to indicate successful execution
}
