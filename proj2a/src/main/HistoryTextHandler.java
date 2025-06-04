package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngm;

    public HistoryTextHandler(NGramMap ngm) {
        this.ngm = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        // 获取查询参数
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        // 构建结果字符串
        StringBuilder result = new StringBuilder();

        // 处理每个单词
        for (String word : words) {
            // 获取单词的相对频率历史
            TimeSeries wordHistory = ngm.weightHistory(word, startYear, endYear);

            // 添加到结果字符串
            result.append(word).append(": ").append(wordHistory.toString()).append("\n");
        }

        return result.toString();
    }
}
