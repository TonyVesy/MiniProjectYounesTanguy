package cs107;

public class testeur {
    public static void main(String[] args) {
        ArrayUtils.concat((byte) 1, (byte) 6, (byte) 3, (byte) -44);
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayUtils.extract(tab, 2, 5);
        System.out.println("_________________debut test__________");

        ArrayUtils.partition(tab, 3, 1, 3, 2);
    }



    public static byte[][] partition(byte[] input, int ... sizes) {


        assert(input == null);
        assert(sizes == null);

        int somme=0;
        for(int size : sizes){
            somme+=size;
        }
        assert((somme-1) == sizes.length);


        byte [][] array = new byte[sizes.length][];
        int j=0;
        int k=0;

        for(int size : sizes){
            array[j] = new byte [size];
            for(int i=0;i<size;++i){
                array[j][i]=input[k];

                System.out.print(array[j][i]);
                ++k;
            }
            System.out.println();
            ++j;

        }
        return array;




    }
}

