package com.alex.video;

import java.io.FileInputStream;
import java.io.IOException;

//import org.apache.commons.io.IOUtils;

class ConvertToByteArray
{
    public static void main(String args[])
    {
        FileInputStream is = null;
        try
        {
            is = new FileInputStream("file.mp4");

            //byte [] byteArr = IOUtils.toByteArray(is);
        }
        catch (IOException ioe)
        {}
        finally
        {
            // close things
        }
    }
}
//    String i = firebaseAuth.getCurrentUser().getEmail();
//                mensaje = Obtener_hora() + "|" + i + "|" + NoContribuyentee.user + "|" + NoContribuyentee.telefono + "|" + NoContribuyentee.coordenadas;
//                        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), correo, "REPORTEALERTA", mensaje);
//                        javaMailAPI.execute();