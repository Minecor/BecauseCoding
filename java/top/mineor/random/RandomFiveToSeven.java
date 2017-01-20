package top.mineor.random;

import java.util.Random;

/**
 * Created by mineor on 2016/12/21.
 * 如何利用随机数1-5生成新的随机数1-7，根据映射关系，将两次rand5()生成的
 * 随机数分布在如下的二维数组，这样就有了rand5()和rand7()的映射关系但是有
 * 个要注意的地方，一般的时候都是根据当前时间来设置种子，然而时间是有规律的，
 * 所以随机数种子只能设置一次，不然的话，每次都设置随机数种子，会导致rand5()
 * 的分布很不随机，偏向由当前时间决定。
 * [1, 2, 3, 4, 5]
 * [6, 7, 1, 2, 3]
 * [4, 5, 6, 7, 1]
 * [2, 3, 4, 5, 6]
 * [7, 0, 0, 0, 0]
 */
public class RandomFiveToSeven {
    public static Random random = new Random(System.nanoTime());
    public static int[][] data = new int[5][5];
    static{
        int count = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(count < 21){
                    data[i][j] = count % 7 + 1;
                    count++;
                }
                else
                    break;
            }
        }
    }

    public void printData(){
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data.length; j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[] result  = new int[7];
        RandomFiveToSeven randomFiveToSeven = new RandomFiveToSeven();
        randomFiveToSeven.rand7Two();
        for(int i = 0; i < 100000000; i++){
            result[randomFiveToSeven.rand7Four()-1]++;
        }
        for(int i = 0; i < result.length; i++){
            System.out.println(i+1 + "的次数为:" + result[i]);
        }

        //System.out.println(randomFiveToSeven.rand5() + " " +randomFiveToSeven.rand5());
    }

    public int rand5(){
        //Random random = new Random(System.currentTimeMillis());
        //Random random = new Random(System.nanoTime());
        return random.nextInt(5)+1;
    }

    /**
     * 第一种根据rand5求rand7的方法
     * 利用两次随机在数组中的分布关系
     * 由于两次rand5()的结果是随机的
     * 所以落在二维数组的位置也是随机的
     * 只需要1-7在二维数组中出现的概率
     * 相等，那么结果就是随机的。
     * 1的次数为:14286565
     * 2的次数为:14283373
     * 3的次数为:14286081
     * 4的次数为:14283092
     * 5的次数为:14285120
     * 6的次数为:14290216
     * 7的次数为:14285553
     * @return
     */
    public int rand7One(){
        while(true){
            int i = rand5()-1;
            int j = rand5()-1;
            if(data[i][j] != 0)
                return data[i][j];
        }
    }

    /**
     * 第二种根据rand5求rand7的方法
     * 利用5*(x-1)+y=z，当z唯一确定的时候
     * x和y存在线性关系，所以result取1-25
     * 的概率相等，所以结果是随机的，只需要将
     * <=21的取出来，然后取余即可得到正确的分布
     * 实验结果也验证了这一点。
     * 1的次数为:14288158
     * 2的次数为:14279848
     * 3的次数为:14289131
     * 4的次数为:14286775
     * 5的次数为:14289755
     * 6的次数为:14285720
     * 7的次数为:14280613
     * @return
     */
    public int rand7Two(){

        while(true) {
            int result = 5 * (rand5() - 1) + rand5();
            if (result <= 21)
                return (result-1) % 7 + 1;
        }
    }

    /**
     * 第三种根据rand5求rand7的方法
     * 每次随机rand5(),如果>3则取1，
     * 如果<3则取0，三次取得数以二进制
     * 计算，由于只需要1-7，所以只取映射
     * 0-6即可，当result=7的时候重置,
     * 结果同样均匀分布.
     * 1的次数为:14280582
     * 2的次数为:14286961
     * 3的次数为:14283250
     * 4的次数为:14286941
     * 5的次数为:14286274
     * 6的次数为:14288361
     * 7的次数为:14287631
     * @return
     */
    public int rand7Three(){
        int result = 0;
        int count = 0;
        while(count < 3){
            int r = rand5();
            if(r < 3){
                result = result * 2 + 1;
                count++;
            }
            else if(r > 3){
                result = result * 2 + 0;
                count++;
            }
            if(result == 7){
                result = 0;
                count = 0;
            }
        }
        return result+1;
    }

    /**
     * 第四种根据rand5求rand7的方法
     * 同样是一个进制转换的思路，将五进制
     * 转为七进制，rand5()-1取第一次的值
     * 然后在取的时候*5在加上rand5()-1，
     * 和第三种类似，只不过没有使用二进制，
     * 而是使用了五进制，这样同样可以将rand5()
     * 的映射范围转移到rand7()上。实验结果
     * 1的次数为:14283201
     * 2的次数为:14287398
     * 3的次数为:14284909
     * 4的次数为:14285159
     * 5的次数为:14285030
     * 6的次数为:14285921
     * 7的次数为:14288382
     * @return
     */
    public int rand7Four(){
        while (true){
            int result = (rand5()-1) * 5 + (rand5()-1);
            if(result < 21)
                return  result % 7 + 1;
        }

    }

}
