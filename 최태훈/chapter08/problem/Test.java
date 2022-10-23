public class Test {

	public static void main(String[] args) {
		String str = "abdcd";
		int n=str.length();
		for( int i =0; i< n; i++){
			System.out.println("i = " + i);
			System.out.println("n = " + n);
			System.out.println("str.substring(i+1,n) = " + str.substring(i+1,n));
			System.out.println("str.substring(0,i) = " + str.substring(0,i));
			System.out.println("str.substring(i + 1, n) + str.substring(0, i)) = " + str.substring(i + 1, n) + str.substring(0, i));

			
		}
	}
}

