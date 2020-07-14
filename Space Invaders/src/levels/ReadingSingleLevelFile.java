package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hadar
 *
 */
public class ReadingSingleLevelFile {
    private List<String> listLevel;

    /**
    *
    * @param reader the given reader
    * @return list of strings
    * @throws IOException an exception.
    */
   public List<String> fromReader(java.io.Reader reader) throws IOException {
       this.listLevel = new ArrayList<String>();
       BufferedReader read = new BufferedReader(reader);
       String tempLine = read.readLine();
       while ((tempLine != null)) {
           if (tempLine != "START_LEVEL" && tempLine != "END_LEVEL" && !tempLine.isEmpty()
                   && !tempLine.startsWith("#")) {
               this.listLevel.add(tempLine);
           }
           if (tempLine.equals("END_LEVEL")) {
               this.listLevel.add("new level");
           }
           tempLine = read.readLine();
       }
       return this.listLevel;
   }
}
