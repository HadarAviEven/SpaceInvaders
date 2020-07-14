package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hadar
 *
 */
public class LineNumberReader extends BufferedReader {
    private Map<String, Integer> lineNumberMap;

    /**
     * constructor.
     *
     * @param reader the given reader
     * @throws IOException an exception.
     */
    public LineNumberReader(Reader reader) throws IOException {
        super(reader);
        int i = 0;
        this.lineNumberMap = new HashMap<String, Integer>();
        BufferedReader read = new BufferedReader(reader);
        String tempLine = read.readLine();
        while ((tempLine != null)) {
            i++;
            this.lineNumberMap.put(tempLine, i);
        }
    }

    /**
     * @param line the given line
     * @return the line number.
     */
    public int getLineNumber(String line) {
        return this.lineNumberMap.get(line);
    }
}
