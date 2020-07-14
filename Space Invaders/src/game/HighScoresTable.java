package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
*
* @author hadar
*
*/
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> highScoresTable;

    /**
     * constructor.
     *
     * @param size the number of elements in the table
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScoresTable = new LinkedList<ScoreInfo>();

        for (int i = 0; i <= this.size; i++) {
            this.highScoresTable.add(i, null);
        }
    }

    /**
     * constructor with a default size.
     *
     */
    public HighScoresTable() {
        this.size = 10;
        this.highScoresTable = new LinkedList<ScoreInfo>();

        for (int i = 0; i <= this.size; i++) {
            this.highScoresTable.add(i, null);
        }
    }

    /**
     *
     * @param score the given info score
     */
    public void add(ScoreInfo score) {
        int place = this.getRank(score.getScore()) - 1;

        if (place != -1) {
            this.highScoresTable.add(place, score);
        }

        if (this.highScoresTable.get(this.size) != null) {
            this.highScoresTable.add(this.size, null);
        }
    }

    /**
     *
     * @return the size
     */
    public int size() {
        return this.size;
    }

    /**
    *
    * @param newSize the new size
    */
   public void setSize(int newSize) {
       this.size = newSize;
   }

    /**
     *
     * @return list of the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }

    /**
     *
     * @param score the given score
     * @return the place of the score in the table
     */
    public int getRank(int score) {
        for (int i = 0; i < this.size; i++) {
            if ((this.highScoresTable.get(i) == null) || (score > this.highScoresTable.get(i).getScore())) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * clears the table.
     */
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.highScoresTable.add(i, null);
        }
    }

    /**
     *
     * @param filename the given file
     * @throws IOException an exception.
     */
    public void load(File filename) throws IOException {
        BufferedReader text = null;
        text = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String reader;
        List<ScoreInfo> players = new ArrayList<ScoreInfo>();
        int countLines = 0;
        while ((reader = text.readLine()) != null) {
            countLines++;
            String[] parts = reader.split(" ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            ScoreInfo newPlayer = new ScoreInfo(name, score);
            players.add(newPlayer);
        }
        this.clear();
        for (int i = 0; i < countLines; i++) {
            this.add(players.get(i));
        }
        if (text != null) {
            try {
                text.close();
            } catch (IOException e) {
                System.out.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     *
     * @param filename the given file
     * @throws IOException an exception.
     */
    public void save(File filename) throws IOException {
        PrintWriter writer = null;
        try {
            if (!filename.exists()) {
                filename.createNewFile();
            } else {
                filename.delete();
                filename.createNewFile();
            }
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (int i = 0; i < this.size; i++) {
                if (this.highScoresTable.get(i) != null) {
                    String name = this.highScoresTable.get(i).getName();
                    int score = this.highScoresTable.get(i).getScore();
                    writer.printf(name);
                    writer.printf(" ");
                    writer.println(score);
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while writing");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     *
     * @param filename the given file
     * @return table of the high scores
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable newTable = new HighScoresTable();
        try {
            newTable.load(filename);
        } catch (IOException e) {
            newTable = new HighScoresTable();
        }
        return newTable;
    }
 }