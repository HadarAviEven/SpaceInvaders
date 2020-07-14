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
public class ReadingSingleBlockFile {
    private List<String> listBlock;

    /**
    *
    * @param reader the given reader
    * @return list of strings
    * @throws IOException an exception.
    */
   public List<String> fromReader(java.io.Reader reader) throws IOException {
       this.listBlock = new ArrayList<String>();
       BufferedReader read = new BufferedReader(reader);
       String tempLine = read.readLine();
       while ((tempLine != null)) {
           if (!tempLine.isEmpty()) {
               this.listBlock.add(tempLine);
           }
           tempLine = read.readLine();
       }
       return this.listBlock;
   }
}