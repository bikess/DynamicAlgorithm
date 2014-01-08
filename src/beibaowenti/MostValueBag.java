package beibaowenti;

// 此程序是0，1背包问题的动态规划求解，由于某个物品要么放、要么不放，只有这两种选择
public class MostValueBag {

	/**
	 * @param args
	 */
	private static int  N= 3;//物品数量
	private static int  V= 5;//背包的容量
	
	//此方法对空间负责度进行优化，采取的是一维数组进行辅助存储
	public static void computePackageOne(int [] W,int [] C){
//		定义一维数组，用来存储每一次循环，每个存储容量所能存储的最大价值
		int f[] = new int[V+1];
//		一维数组初始化，初始化化为0，则不需要恰好装满背包，这里只需要最终物品总容量小于等于包的总容量即可。
		for(int i = 0;i<=V;i++){
			f[i] = 0;
		}
		
		for(int i=1;i<=N;i++){
//			注意这里第二层循环改为逆序，因为否则会覆盖的！！
			for(int j=V;j>=1;j--){
				if(C[i]<=j){
					if(f[j-C[i]]+W[i]>f[j]){
						f[j] = f[j-C[i]]+W[i];
					}
					else{
						f[j] = f[j];
					}
				}else{
					f[j] = f[j];
				}
			}
		}
		System.out.println("背包中所能存放的最大值为："+f[V]);
		
//		输出物品
		int i = N;
		int j = V;
		while(i>0){
//			注意这里j有可能比C[i]小的。
			if(j-C[i]>=0){
			if(f[j]==f[j-C[i]]+W[i]){
				System.out.println("存入物品为："+i+":"+"物品大小为："+C[i]+",物品价值为："+W[i]);
				j=j-C[i];
			}
			}
			i--;
		}
	}
	
	
//	此方法采用二维数组进行辅助存储
	public static void computePackageTwo(int [] W,int [] C){
		
//		定义二维数组，用来存储前 i个物品背包容量为v的最大价值
		int f[][] = new int[N+1][V+1];
//		二维数组初始化，初始化为0，则不需要恰好装满背包，这里只需要最终物品总容量小于等于包的总容量即可。
		for(int i =0;i<=N;i++){
			for(int j=0;j<=V;j++){
				f[i][j] = 0;
			}
		}
		for(int i =1;i<=N;i++){
			
			for(int j=1;j<=V;j++){
//				当前的背包容量为j
				if(C[i]<=j)//如果当前物品的大小小于当前背包的容量，可以放入，w[i]表示i物品的价值	
				{
					//f[i-1][j-w[i]]为上一次放入物品，背包容量为当前背包容量减去当前物品的大小时的最大价值值
					//f[i-1][j]上一次放物品背包容量为j时的最大价值值
//					比较放入物品i与上一次放入物品背包容量为当前背包容量减去当前物品的大小时的最大价值值 与 
//					上一次放物品背包容量为j时的最大价值值 比较
					
					if((W[i]+f[i-1][j-C[i]])>f[i-1][j]){
						f[i][j] = W[i]+f[i-1][j-C[i]]; 
					}else{
						f[i][j] = f[i-1][j];
					}
				}else{//若当前物品的大小大于当前背包的容量，则不放入，背包的总价值保持上一次
					f[i][j] = f[i-1][j];
				}
			}
		}
		
		System.out.println("背包中所能存放的最大值为："+f[N][V]);
//		输出存入的物品编号
		int value = f[N][V];
		if(value>0){
			int i = N,j=V;
			while(i>0){
				if(j-C[i]>=0){
				if(f[i][j]==f[i-1][j-C[i]]+W[i]){
					System.out.println("存入物品为："+i+":"+"物品大小为："+C[i]+",物品价值为："+W[i]);
					j=j-C[i];
				}
				}
				i--;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int W[]={0,7,5,13};      //物品价值量,第一个默认为0，从而使数组与物品序列对应
		int C[]={0,2,3,4};      //物品大小
		

		
//		采用二维数组进行存储，计算背包最大价值
//		computePackageTwo(W,C);
		
//		采用一维数组进行存储，计算背包最大价值
		computePackageOne(W,C);
		
//		若要求恰好装满背包时，计算背包的最大价值
		computePackageMeet(W,C);
	}


	private static void computePackageMeet(int[] W, int[] C) {
		// TODO Auto-generated method stub
		/**
		 * 在初始化时除了f[0]为0其它f[1..V]均设为-∞，这样就可以保证最终得到的f[N]是一种恰好装满背包的最优解。
		 * 如果不能恰好满足背包容量，即不能得到f[V]的最优值，则此时f[V]=-∞，这样就能表示没有找到恰好满足背包容量的最优值。
		 * 因为此时只有f[0]=0,也就是只有当物品的容量之和恰好为当前包的容量是，才会是有效的
		 */
//		定义一维数组，用来存储每一次循环，每个存储容量所能存储的最大价值
		int f[] = new int[V+1];
//		一维数组初始化，只有f[0]初始化化为0，其他初始化为负无穷。则要求恰好装满背包，这里必须最终物品总容量等于包的总容量
		for(int i = 0;i<=V;i++){
			if(i==0)
				f[i] = 0;
			else
				f[i] = Integer.MIN_VALUE;
		}
		
		for(int i=1;i<=N;i++){
//			注意这里第二层循环改为逆序，因为否则会覆盖的！！
			for(int j=V;j>=1;j--){
				if(C[i]<=j){
					if(f[j-C[i]]+W[i]>f[j]){
						f[j] = f[j-C[i]]+W[i];
					}
					else{
						f[j] = f[j];
					}
				}else{
					f[j] = f[j];
				}
			}
		}
		System.out.println("背包中所能存放的最大值为："+f[V]);
		
//		输出物品
		int i = N;
		int j = V;
		while(i>0){
			if(f[j]==f[j-C[i]]+W[i]){
				System.out.println("存入物品为："+i+":"+"物品大小为："+C[i]+",物品价值为："+W[i]);
				j=j-C[i];
			}
			i--;
		}
	}

}
