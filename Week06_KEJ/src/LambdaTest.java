import java.util.Arrays;

public class LambdaTest {
    public static ArrayProcessing maxer = array -> {
        if (array.length == 0) {
            return Double.NaN;
        }
        double max = array[0];
        for (double num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    };

    public static final ArrayProcessing miner = array -> {
        if (array.length == 0) {
            return Double.NaN;
        }
        double min = array[0];
        for (double num : array) {  
            if (num < min) {
                min = num;
            }
        }
        return min;
    };

    public static final ArrayProcessing sumer = array -> {
        if (array.length == 0) {
            return Double.NaN;
        }
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum / array.length;
    };

    public static void main(String[] args) {
        double[] numbers = {10.5, 2.7, 8.1, 5.3, 12.4};
        
        double max = maxer.processArray(numbers);
        double min = miner.processArray(numbers);
        double average = sumer.processArray(numbers);
        
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        System.out.println("Average: " + average);
    }
}

@FunctionalInterface
interface ArrayProcessing {
    double processArray(double[] array);
}
