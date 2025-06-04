package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private TimeSeries totalCounts;
    private TreeMap<String, TimeSeries> wordCounts;
    // TODO: Add any necessary static/instance variables.
    /*public class WordDataBase{
        private TreeMap<String, TimeSeries> wordCounts;
        public WordDataBase(){
            wordCounts=new TreeMap<>();
        }

    }*/
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        totalCounts= new TimeSeries();
        wordCounts= new TreeMap<>();
        In in1= new In(wordsFilename);
        In in2= new In(countsFilename);
        while(in2.hasNextLine()){
            String line=in2.readLine();
            String[] columns=line.split(",");
            int year=Integer.parseInt(columns[0]);
            double data=Double.parseDouble(columns[1]);
            totalCounts.put(year,data);
        }
        while(in1.hasNextLine()){
            String line=in1.readLine();
            String[] columns=line.split("\t");
            String word=columns[0];
            int year=Integer.parseInt(columns[1]);
            double data=Double.parseDouble(columns[2]);
            if (!wordCounts.containsKey(word)) {
                wordCounts.put(word, new TimeSeries());
            }
            wordCounts.get(word).put(year, data);
        }
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (!wordCounts.containsKey(word)) {
            return new TimeSeries();
        }
        else{
            TimeSeries result = new TimeSeries();
            for(int i=startYear;i<=endYear;i++){
              if(wordCounts.get(word).containsKey(i)){
                  result.put(i, wordCounts.get(word).get(i));
              }
            }
            return result;
        }
    }
    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.

       return countHistory(word,MIN_YEAR,MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        if(totalCounts.isEmpty()){
            return new TimeSeries();
        }
        else{
            return new TimeSeries(totalCounts,MIN_YEAR,MAX_YEAR);
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if(!wordCounts.containsKey(word)){
            return new TimeSeries();
        }
        else {
            TimeSeries res = new TimeSeries();
            for (int i = startYear; i <= endYear; ++i) {
                if (totalCounts.containsKey(i)) {
                    double fre = wordCounts.get(word).get(i) / totalCounts.get(i);
                    res.put(i, fre);
                }
            }
            return res;
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if(!wordCounts.containsKey(word)){
            return new TimeSeries();
        }
        else{
            TimeSeries res= new TimeSeries();
            for(int i=MIN_YEAR;i<=MAX_YEAR;i++){
                if(totalCounts.containsKey(i)){
                    double fre = wordCounts.get(word).get(i) / totalCounts.get(i);
                    res.put(i, fre);
                }
            }
            return res;
        }
        // TODO: Fill in this method.
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries res= new TimeSeries();
        for(int i=startYear;i<=endYear;i++){
            Iterator<String> it= words.iterator();
            double fre=0.0;
            while(it.hasNext()){
                String word=it.next();
                if(totalCounts.containsKey(i)) {
                    if (wordCounts.containsKey(word)) {
                        fre += wordCounts.get(word).get(i);
                    }
                }
            }
            fre/=totalCounts.get(i);
            res.put(i,fre);
        }
        return res;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.

        return summedWeightHistory(words,MIN_YEAR,MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
