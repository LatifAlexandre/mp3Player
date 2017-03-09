import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Byte;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

public class PlayerI extends MP3Player._PlayerDisp {
    static int n = 0;
    public void getInvocationCount(String s, Ice.Current current){
        n++;
        System.out.println("Number of calls : " + n + " \n" + System.getProperty("user.dir"));
    }

    public void deleteFile(String name, Ice.Current current){
      File file = null;

      try{
         // create new file
         file = new File(name); //TODO TXT to WAV

         // tries to delete a non-existing file
         if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation failed.");
    		}

      }catch(Exception e){
        e.printStackTrace();
      }
    }

    public void addFile(byte[] f, String name, Ice.Current current){
      n++;
      System.out.println(name);

      File file = new File(name);

      try{
        if (file.createNewFile()){
          System.out.println("File is created!");
        }
      } catch(Exception e ){
        e.printStackTrace();
      }
      FileOutputStream stream;
      try {
        stream = new FileOutputStream(name);
        stream.write(f);
        stream.close();
      } catch (Exception e){
        e.printStackTrace();
      }
      System.out.println("Done");
    }

    public int getFileLength(String name, Ice.Current current){
      File file = new File(name);

      int duration = 0;

      try {
        AudioFile audioFile = AudioFileIO.read(file);
        duration = audioFile.getAudioHeader().getTrackLength();
        System.out.println("TIME --------------------------------- : " +  duration);
      } catch (Exception e) {
        e.printStackTrace();

      }
      return duration;
    }

    public void deleteDirectory(String name, Ice.Current current){

    }

    public String getFilesByRegex(String name, Ice.Current current){
      File file = new File("files");
      String res = "";
      boolean b = false;
      File[] list = file.listFiles();
      if(list!=null){
        for (File fil : list)
        {
            b = Pattern.matches(name + "*", file.getName());
            if (b)
            {
                res += file.getName() + "\n";
                System.out.println(fil.getParentFile());
            }
        }
      }
      return res;
    }

    public byte[] getFile(String name, Ice.Current current){
      File file = new File(name);

      n++;
      System.out.println("files/" + name + ".wav");
      byte[] byteArray = null;

      try {
        byteArray = Files.readAllBytes(new File(name).toPath());

    }
    catch (Exception e){
      e.printStackTrace();
    }
      System.out.println(byteArray.length);
      return (byteArray != null ? byteArray : null);
    }
}
