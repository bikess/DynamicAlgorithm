package maxcost;

//钢条切割问题，给一个最佳的切割方案，使效益最高！！

//最后输出最佳的切割方案，已经最佳的效益值
public class SteelCut {

	/**
	 * @param args
	 */
//	每个切割值对应的效益
	static int p[]= {0,1,5,8,9,10,17,17,20,24,30};
	
//		要求切割的长度
	static int n=30;	
	
//	存储切割方案数组
	static int s[] = new int[n+1];
	
//	存储效益数组,r[i]存储的是当切割长度为i时候的最佳切割效益值
	static int r[] = new int [n+1];
	
//	自底向上的切割方案
	static void BottomUPCutRod(int[] p, int n) {
		// TODO Auto-generated method stub
		int q;
		r[0]=0;
		for(int i=1;i<=n;i++){
//			依次遍历，寻找1-n的每个长度的最佳切割方案
			q = -1;
			for(int j=1;j<=i;j++){
				
//				寻找各种切割方案的最佳值
				if(j<p.length){
				if(q<p[j]+r[i-j]){
					q = p[j]+r[i-j];
//				记录长度为i的钢条，最佳切割方案是切j长度	
					s[i] = j;
				}
				}
			}
//			将长度为i的钢条的最佳切割效益值存入数组r。
			r[i] = q;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub	

//		采用自底向上的方案
		BottomUPCutRod(p,n);
		
//		采用自顶向下的带备忘录的方案
		
//		输出最佳效益值
		System.out.println("钢条长度为："+n+",最佳切割效益值为："+r[n]);
		
//		输出最佳切割方案
		int len = n;
		System.out.println("最佳切割方案为：");
		while(len>0){
			System.out.print(s[len]+" ");
			len = len-s[len];
		}
	}



}
