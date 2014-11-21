public class Opgave
{
    public static void main(String[] arg)
    {
    }

    private static int Dice()
    {
	Random generator = new Random();
	return generator.nextInt(6) + 1;
    }
}
