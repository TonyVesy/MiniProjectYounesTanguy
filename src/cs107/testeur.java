package cs107;

public class testeur {
    public static void main(String[] args){
        byte[] array = {123, 8, 4, 7};
        int value = toInt(array);
        System.out.println (Integer.toBinaryString(value)); System.out.println (value);
        int val=16843009;
        //byte[] arrayy = fromInt(value);
        //System.out.print(arrayy[0]+" " +arrayy[1]+" " + arrayy[2]+" " +arrayy[3]);
    }
    //public static byte[] fromInt(int value){
        //byte [] array =new byte[4];
       // int somme=0;
        //for i in range

        //return array;
    //}
    public static int toInt(byte[] bytes){ int nombre = 0;
        for (int i = 0; i <= 3; ++i) {
            nombre += bytes[i] << 8 * (3 - i);
        }
        return nombre; }
}