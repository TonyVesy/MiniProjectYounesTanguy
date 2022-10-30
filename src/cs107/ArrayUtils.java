package cs107;

/**
 * Utility class to manipulate arrays.
 * @apiNote First Task of the 2022 Mini Project
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.3
 * @since 1.0
 */
public final class ArrayUtils {

    /**
     * DO NOT CHANGE THIS, MORE ON THAT IN WEEK 7.
     */
    private ArrayUtils(){}

    // ==================================================================================
    // =========================== ARRAY EQUALITY METHODS ===============================
    // ==================================================================================

    /**
     * Check if the content of both arrays is the same
     * @param a1 (byte[]) - First array
     * @param a2 (byte[]) - Second array
     * @return (boolean) - true if both arrays have the same content (or both null), false otherwise
     * @throws AssertionError if one of the parameters is null
     */
    public static boolean equals(byte[] a1, byte[] a2){
        if (( a1 == null) && (a2 == null)){
            return true;
        }
        assert (a1 != null);
        assert (a2 != null);
        if (a1.length != a2.length){
            return false;
        }
        for (int i = 0; i <= a1.length; ++i ){
            boolean test = a1[i] == a2[i];
            if (test == false){
                return false;
            }
        } return true;

    }

    /**
     * Check if the content of both arrays is the same
     * @param a1 (byte[][]) - First array
     * @param a2 (byte[][]) - Second array
     * @return (boolean) - true if both arrays have the same content (or both null), false otherwise
     * @throws AssertionError if one of the parameters is null
     */
    public static boolean equals(byte[][] a1, byte[][] a2){
        return Helper.fail("Not Implemented");
    }

    // ==================================================================================
    // ============================ ARRAY WRAPPING METHODS ==============================
    // ==================================================================================

    /**
     * Wrap the given value in an array
     * @param value (byte) - value to wrap
     * @return (byte[]) - array with one element (value)
     */
    public static byte[] wrap(byte value){
        byte[] array = {value};
        return array;
    }


    /**
     * Create an Integer using the given array. The input needs to be considered
     * as "Big Endian"
     * (See handout for the definition of "Big Endian")
     * @param bytes (byte[]) - Array of 4 bytes
     * @return (int) - Integer representation of the array
     * @throws AssertionError if the input is null or the input's length is different from 4
     */
    public static int toInt(byte[] bytes){

        assert bytes == null;
        assert bytes.length!=4;

        int nombre = 0;

        for (int i = 0; i <= 3; ++i) {
            nombre += bytes[i] << 8 * (3 - i);
        }
        return nombre; }
     //on prend le premier nombre du tableau et on le deplace de 8 bit et on repet pour les nombre suivants

    /**
     * Separate the Integer (word) to 4 bytes. The Memory layout of this integer is "Big Endian"
     * (See handout for the definition of "Big Endian")
     * @param value (int) - The integer
     * @return (byte[]) - Big Endian representation of the integer
     */
    public static byte[] fromInt(int value){

        byte [] array =new byte[4];
        int somme=0;

        for (int i=0;i<4;++i){
            array[i]=(byte)(value >> 8*(3-i));
        }
        return array;
    }


    /**
     * Concatenate a given sequence of bytes and stores them in an array
     * @param bytes (byte ...) - Sequence of bytes to store in the array
     * @return (byte[]) - Array representation of the sequence
     * @throws AssertionError if the input is null
     */
    public static byte[] concat(byte ... bytes){

        assert bytes==null;

        byte [] array =new byte[bytes.length];
        int i =0;
        for (byte byt : bytes){
            array [i] =byt;
            System.out.println(array[i]);
            ++i;
        }

        return array;
    }

    /**
     * Concatenate a given sequence of arrays into one array
     * @param tabs (byte[] ...) - Sequence of arrays
     * @return (byte[]) - Array representation of the sequence
     * @throws AssertionError if the input is null
     * or one of the inner arrays of input is null.
     */
    public static byte[] concat(byte[] ... tabs){
        return Helper.fail("Not Implemented");
    }

    // ==================================================================================
    // =========================== ARRAY EXTRACTION METHODS =============================
    // ==================================================================================

    /**
     * Extract an array from another array
     * @param input (byte[]) - Array to extract from
     * @param start (int) - Index in the input array to start the extract from
     * @param length (int) - The number of bytes to extract
     * @return (byte[]) - The extracted array
     * @throws AssertionError if the input is null or start and length are invalid.
     * start + length should also be smaller than the input's length
     */
    public static byte[] extract(byte[] input, int start, int length){

        assert input==null;
        assert (length<0)|(start<0)|(length<start)|((length+1)<input.length);


        byte [] array =new byte[length-start+1];
        int j=0;

        for(int i=start;i<=length;++i){
            array[j]=input[i];
            ++j;
        }


        return array;
    }

    /**
     * Create a partition of the input array.
     * (See handout for more information on how this method works)
     * @param input (byte[]) - The original array
     * @param sizes (int ...) - Sizes of the partitions
     * @return (byte[][]) - Array of input's partitions.
     * The order of the partition is the same as the order in sizes
     * @throws AssertionError if one of the parameters is null
     * or the sum of the elements in sizes is different from the input's length
     */
    public static byte[][] partition(byte[] input, int ... sizes) {

        assert(input == null);
        assert(sizes == null);

        int somme=0;
        for(int size : sizes){
            somme+=size;
        }
        assert((somme) == sizes.length);


        byte [][] array = new byte[sizes.length][];
        int j=0;
        int k=0;

        for(int size : sizes){
            array[j] = new byte [size];
            for(int i=0;i<size;++i){
                array[j][i]=input[k];

                //System.out.print(array[j][i]);
                ++k;
            }
            //System.out.println();
            ++j;

        }
        return array;



    }
    /**
     * Format a 2-dim integer array
     * where each dimension is a direction in the image to
     * a 2-dim byte array where the first dimension is the pixel
     * and the second dimension is the channel.
     * See handouts for more information on the format.
     * @param input (int[][]) - image data
     * @return (byte [][]) - formatted image data
     * @throws AssertionError if the input is null
     * or one of the inner arrays of input is null
     */
    public static byte[][] imageToChannels(int[][] input){

        assert input == null;
        boolean nul = false;
            for (int i = 0; i < input.length; i++) {
                    if(input[i]==null){
                        nul=true;
                    }

            }
        // voir si une des lignes est null dans ce cas on assert
        assert nul;

        /**
         * IL MANQUE QUE LA TAILLEE DES COLONES SOIENT LES MEMES
         */


        byte [][] array = new byte[(input.length)*(input[0].length)][4];
        // nombre de lignes du tableau ligne*colonnes de l'image et 4 colonne pour le rgba


        for (int i=0 ;i<(input.length)*(input[0].length);){
            for (int ligne=0 ;ligne<(input.length);++ligne){

                for (int col=0 ;col<(input[0].length);++col){

                    array [i]= fromInt(input[ligne][col]);
                    //System.out.print(array [i][0]+" "+array [i][1]+" "+array [i][2]+" "+array [i][3]+" ");
                    ++i;
                }
            }
        }


        return array;

    }

    /**
     * Format a 2-dim byte array where the first dimension is the pixel
     * and the second is the channel to a 2-dim int array where the first
     * dimension is the height and the second is the width
     * @param input (byte[][]) : linear representation of the image
     * @param height (int) - Height of the resulting image
     * @param width (int) - Width of the resulting image
     * @return (int[][]) - the image data
     * @throws AssertionError if the input is null
     * or one of the inner arrays of input is null
     * or input's length differs from width * height
     * or height is invalid
     * or width is invalid
     */
    public static int[][] channelsToImage(byte[][] input, int height, int width){

        assert input==null;

        for (int i =0;i<height;++i){
            assert input[i]==null;
            if (input[i].length == 4) throw new AssertionError();
        }
        assert height*width==input.length;

        assert input==null;


        int [][] image = new int[height][width];

        for (int i=0;i<height*width;++i) {
            for (int j = 0; j < height; ++j) {
                for (int k = 0; k < width; ++k) {

                    image[j][k] =  toInt(input[i]);
                    System.out.print(image[j][k]+" ");
                    ++i;

                }
                System.out.println();
            }
        }

        return image;
    }

}