package client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HistoryList {

    private static PrintWriter fileWriter;

    private static String getHistoryFilenameByLogin(String login) {
        return "history/history_" + login + ".txt";
    }



    public static void write(String msg) {

        fileWriter.println(msg);
    }

    public static void create(String login){
        try {

            fileWriter=new PrintWriter(new FileOutputStream(getHistoryFilenameByLogin(login),true),true) ;

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void read(String login) {

        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream((getHistoryFilenameByLogin(login))), StandardCharsets.UTF_8)){
            int y;
            while ((y = fileReader.read())  !=-1){
                System.out.print((char)y);

            }


        }catch (IOException e) {
            e.printStackTrace();

        }

    }



}

