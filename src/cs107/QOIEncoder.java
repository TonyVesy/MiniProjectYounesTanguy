package cs107;

/**
 * "Quite Ok Image" Encoder
 * @apiNote Second task of the 2022 Mini Project
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 * @version 1.3
 * @since 1.0
 */

import java.util.ArrayList;

public final class QOIEncoder {

    /**
     * DO NOT CHANGE THIS, MORE ON THAT IN WEEK 7.
     */
    private QOIEncoder(){}

    // ==================================================================================
    // ============================ QUITE OK IMAGE HEADER ===============================
    // ==================================================================================

    /**
     * Generate a "Quite Ok Image" header using the following parameters
     * @param image (Helper.Image) - Image to use
     * @throws AssertionError if the colorspace or the number of channels is corrupted or if the image is null.
     *  (See the "Quite Ok Image" Specification or the handouts of the project for more information)
     * @return (byte[]) - Corresponding "Quite Ok Image" Header
     */
    public static byte[] qoiHeader(Helper.Image image){

        assert image==null;
        assert (image.channels()!=QOISpecification.RGB)||(image.channels()!=QOISpecification.RGBA);
        assert (image.color_space()!=QOISpecification.sRGB)||(image.color_space()!=QOISpecification.ALL);


        int[][] dim= image.data();
        byte[] hauteur = ArrayUtils.fromInt(dim.length);
        byte[] largeur= ArrayUtils.fromInt(dim[0].length);

        byte [] header =ArrayUtils.concat(
                QOISpecification.QOI_MAGIC,
                largeur,
                hauteur,
                ArrayUtils.wrap(image.channels()),
                ArrayUtils.wrap(image.color_space()));

        return header;

    }

    // ==================================================================================
    // ============================ ATOMIC ENCODING METHODS =============================
    // ==================================================================================

    /**
     * Encode the given pixel using the QOI_OP_RGB schema
     * @param pixel (byte[]) - The Pixel to encode
     * @throws AssertionError if the pixel's length is not 4
     * @return (byte[]) - Encoding of the pixel using the QOI_OP_RGB schema
     */
    public static byte[] qoiOpRGB(byte[] pixel){
        assert pixel.length!=4;

        byte[] encode  = ArrayUtils.concat(
                ArrayUtils.wrap(QOISpecification.QOI_OP_RGB_TAG),
                ArrayUtils.extract(pixel,0,3)
                );
        return encode;
    }

    /**
     * Encode the given pixel using the QOI_OP_RGBA schema
     * @param pixel (byte[]) - The pixel to encode
     * @throws AssertionError if the pixel's length is not 4
     * @return (byte[]) Encoding of the pixel using the QOI_OP_RGBA schema
     */
    public static byte[] qoiOpRGBA(byte[] pixel){
        assert pixel.length!=4;

        byte[] encode  = ArrayUtils.concat(
                ArrayUtils.wrap(QOISpecification.QOI_OP_RGBA_TAG),
                pixel
        );
        return encode;
    }

    /**
     * Encode the index using the QOI_OP_INDEX schema
     * @param index (byte) - Index of the pixel
     * @throws AssertionError if the index is outside the range of all possible indices
     * @return (byte[]) - Encoding of the index using the QOI_OP_INDEX schema
     */
    public static byte[] qoiOpIndex(byte index){

        assert index<0;
        assert index>63;

        return ArrayUtils.wrap((byte) (QOISpecification.QOI_OP_INDEX_TAG|index));
    }

    /**
     * Encode the difference between 2 pixels using the QOI_OP_DIFF schema
     * @param diff (byte[]) - The difference between 2 pixels
     * @throws AssertionError if diff doesn't respect the constraints or diff's length is not 3
     * (See the handout for the constraints)
     * @return (byte[]) - Encoding of the given difference
     */
    public static byte[] qoiOpDiff(byte[] diff){

        assert diff==null;

        assert diff.length!=3;

        for (int i=0;i<3;++i){
            if(diff[i]>1||diff[i]<-2) throw new AssertionError();
        }

        byte dr= (byte) ((diff[0]+2)<<4);
        // décale de 4
        byte dg= (byte) (((diff[1]+2)<<2));
        // décale de 2
        byte db= (byte) ((diff[2]+2));


        return ArrayUtils.wrap((byte) (QOISpecification.QOI_OP_DIFF_TAG|dr|dg|db));
    }

    /**
     * Encode the difference between 2 pixels using the QOI_OP_LUMA schema
     * @param diff (byte[]) - The difference between 2 pixels
     * @throws AssertionError if diff doesn't respect the constraints
     * or diff's length is not 3
     * (See the handout for the constraints)
     * @return (byte[]) - Encoding of the given difference
     */
    public static byte[] qoiOpLuma(byte[] diff){
        return Helper.fail("Not Implemented");
    }

    /**
     * Encode the number of similar pixels using the QOI_OP_RUN schema
     * @param count (byte) - Number of similar pixels
     * @throws AssertionError if count is not between 0 (exclusive) and 63 (exclusive)
     * @return (byte[]) - Encoding of count
     */
    public static byte[] qoiOpRun(byte count){

        assert count<=0;

        assert count>62;


        return ArrayUtils.wrap((byte) (QOISpecification.QOI_OP_RUN_TAG|(count-1)));

    }

    // ==================================================================================
    // ============================== GLOBAL ENCODING METHODS  ==========================
    // ==================================================================================

    /**
     * Encode the given image using the "Quite Ok Image" Protocol
     * (See handout for more information about the "Quite Ok Image" protocol)
     * @param image (byte[][]) - Formatted image to encode
     * @return (byte[]) - "Quite Ok Image" representation of the image
     */
    public static byte[] encodeData(byte[][] image){

        assert image == null;

        for(int i= 0;i<image[0].length* image.length;++i){

            if (image[i] ==null)throw new AssertionError();
            if (image[i].length != 4) throw new AssertionError();
        }


        byte [][]hachtable =new byte [64][4];
        byte  [] previouspixel = QOISpecification.START_PIXEL ;
        int LastPixel=image.length;


        ArrayList< byte[]> list =new ArrayList();
        //ajout d'un array list contenant des tableaux de byte pour chaque pixel ou suite de pixel selon l'algorythme



        for(int i= 0;i<LastPixel;++i){

            //1. Si pixel== pixel précédent, augmenter le compteur et tester si un QOI_OP_RUN peut être construit
            // (compteur atteignant la limite de 62 ou dernier pixel atteint), puis passer au pixel suivant,
            // sinon tester si un QOI_OP_RUN peut être construit et aller à l’étape 2.

            byte count=0;

                while(ArrayUtils.equals(image[i],previouspixel)
                        &&count<=62
                        &&i+count<(LastPixel)){

                              ++count;
                              ++i;
            }
                if(count>0){
                    list.add(qoiOpRun(count));
                    //reset du count
                    count=0;

                }


            //2. Si pixel est dans la table de hachage (il a la même valeur que le pixel stocké à la position qui lui revient selon sa clé de hachage)
            // , créer un bloc QOI_OP_INDEX et passer au pixel suivant, sinon ajouter le pixel à la table de hachage et aller à l’étape 3.





            //3. Si le canal alpha est le même entre le pixel courant et le précédent,
            // et que la différence entre ces pixels est faible (selon les critères spécifiques aux blocs QOI_OP_DIFFa)
            // alors créer un bloc QOI_OP_DIFF et passer au pixel suivant, sinon aller à l’étape 4.
            byte diffr= (byte) (image[i][0]-previouspixel[0]);
            byte diffg= (byte) (image[i][1]-previouspixel[1]);
            byte diffb= (byte) (image[i][2]-previouspixel[2]);
            byte diffa= (byte) (image[i][3]-previouspixel[3]);


            if (diffa == 0) {

                if ((diffr >= -2 && diffr <= 1) && (diffg >= -2 && diffg <= 1) && (diffb >= -2 && diffb <= 1)) {
                    byte [] diff={diffr,diffg,diffb};
                    list.add(qoiOpDiff(diff));

                }
                //4. Si le canal alpha est le même entre le pixel courant et le précédent,
                // et que la différence entre entre ces pixels correpond aux critères des blocs QOI_OP_LUMA,
                // alors créer un bloc de ce type et passer au pixel suivant, sinon aller à l’étape 5.

                else if ((diffg >= -32 && diffg <= 31) && (diffr-diffg>= -8 && diffr-diffg <= 7) && (diffb-diffg >= -8 && diffb-diffg  <= 7)) {
                    byte [] diff={diffr,diffg,diffb};
                    list.add(qoiOpLuma(diff));

                }
                //5. Si le canal alpha est le même entre le pixel courant et le précédent,
                // créer un bloc QOI_OP_RGB (la valeur du canal alpha étant celle commune aux deux pixels)
                // et passer au pixel suivant, sinon aller à l’étape 6.
                else{
                    byte[ ] RGB= {image[i][0],image[i][1],image[i][2]};
                    list.add(qoiOpRGB(RGB));
                }

            } // fin de A vaut A du previous

            //6. créer un bloc QOI_OP_RGBA et passer au pixel suivant
            byte[ ] RGBA= {image[i][0],image[i][1],image[i][2],image[i][3]};
            list.add(qoiOpRGB(RGBA));

        }
        //fin de la creation de l'array list maintenant il faut tout concatener

        int concatsize=0;
        for (int i=0;i<list.size();++i){
            concatsize+= (list.get(i)).length;
        }
        byte [] encode = new byte [concatsize];

        for (int i=0;i<list.size();++i){
            encode = ArrayUtils.concat(encode,list.get(i));
        }



        return encode;

    }

    /**
     * Creates the representation in memory of the "Quite Ok Image" file.
     * @apiNote THE FILE IS NOT CREATED YET, THIS IS JUST ITS REPRESENTATION.
     * TO CREATE THE FILE, YOU'LL NEED TO CALL Helper::write
     * @param image (Helper.Image) - Image to encode
     * @return (byte[]) - Binary representation of the "Quite Ok File" of the image
     * @throws AssertionError if the image is null
     */
    public static byte[] qoiFile(Helper.Image image){
        return Helper.fail("Not Implemented");
    }

}