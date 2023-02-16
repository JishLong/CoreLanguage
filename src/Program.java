import tokenizer.Scanner;

public class Program
{
    public static void main (String[] args)
    {
        Scanner tokenizer = new Scanner(args[0]);

        while (tokenizer.getToken() != 33 && tokenizer.getToken() != 34)
        {
            System.out.println(tokenizer.getToken());
            tokenizer.skipToken();
        }
        System.out.println(tokenizer.getToken());
    }
}
