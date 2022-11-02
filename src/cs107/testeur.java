package cs107;

public class testeur {
    public static void main(String[] args) {
        ArrayUtils.concat((byte) 1, (byte) 6, (byte) 3, (byte) -44);
        byte[] tab = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        tab=ArrayUtils.extract(tab, 2, 6);
        for(int i=0;i<tab.length;++i){
            System.out.print(tab[i]+" , ");
        }
        System.out.println("_________________debut test__________");




        //ArrayUtils.partition(tab, 3, 1, 3, 2);
        int [][] image = {{1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},
        {1,2,3,4,5},};

        System.out.println(image.length+""+image[0].length);
        byte q='q';
        System.out.println("_________________debut test RGBA ARGB__________"+q);




        byte[] testing = {1, 2, 3, 4,};
        byte[] testing2=ArrayUtils.ARGBtoRGBA(testing);
        System.out.println(testing2[0]+" , "+testing2[1]+" , "+testing2[2]+" , "+testing2[3]+" , ");
        testing2=ArrayUtils.RGBAtoARGB(testing2);
        System.out.println(testing2[0]+" , "+testing2[1]+" , "+testing2[2]+" , "+testing2[3]+" , ");



        byte[] test =ArrayUtils.fromInt(1);
        for(int j=0;j<4;++j){
            System.out.print(test[j]+" ");}


        int[][] input = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9 ,10},
                {11, 12, 13, 14, 15}};

        byte[][] output = ArrayUtils.imageToChannels(input);

        for(int i=0;i<15;++i){

            System.out.print(output[i][0]+" ");
            System.out.print(output[i][1]+" ");
            System.out.print(output[i][2]+" ");
            System.out.print(output[i][3]+" ");
            System.out.println();
        }

        System.out.println("-------------retour");
        byte[][] formatted_input = {{0,0, 1,0},{0,0, 2,0},{0,0, 3,0}, {0,0, 4,0},{0,0, 5,0},{0,0, 6,0}, {0,0, 7,0},{0,0, 8,0},{0,0, 9,0}, {0, 0, 10, 0}, {0, 0, 11, 0}, {0, 0, 12, 0}, {0, 0, 13, 0}, {0, 0, 14, 0}, {0, 0, 15, 0}};
        // output = [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15]]
        int[][] output2 = ArrayUtils.channelsToImage(formatted_input, 3, 5);
        Hexdump.hexdump(tab);


    }




    }


