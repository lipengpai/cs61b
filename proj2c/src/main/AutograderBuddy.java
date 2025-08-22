package main;

import WordNet.Wordnet;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        Wordnet wn = new Wordnet(synsetFile,hyponymFile);
        NGramMap np = new NGramMap(wordFile,countFile);
        return new HyponymsHandler(wn,np);
    }
}
