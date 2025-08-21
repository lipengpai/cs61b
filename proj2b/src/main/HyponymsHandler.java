package main;
import WordNet.Graph;
import WordNet.Wordnet;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler {
    public Wordnet net;
   public HyponymsHandler(Wordnet wn) {
       net = wn;
   }
    @Override
    public String handle(NgordnetQuery q) {
        return "Hello!";
    }
    }



